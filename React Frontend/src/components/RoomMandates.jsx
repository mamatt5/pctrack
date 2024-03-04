import { Button, Dialog, DialogTitle, DialogContent, DialogContentText, DialogActions, TextField, IconButton, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper } from '@mui/material'
import React, { useEffect, useState } from 'react'
import callApi from '../api/callApi'
import DoneIcon from '@mui/icons-material/Done';
import EditIcon from '@mui/icons-material/Edit';
import { useParams } from 'react-router-dom';

const getStaff = (userId, setStaff) => {
  const config = {
    method: 'get',
    endpoint: `staff/${userId}`
  };
  callApi((response) => {
    const roomAdmin = response.find(staff => staff.adminLevel.name === 'Room');
    setStaff(roomAdmin);
  }, null, config);
}

const getRoomMandates = (roomId, setRoomMandates) => {
  const config = {
    method: 'get',
    endpoint: `rooms/${roomId}/mandates`
  };
  callApi(setRoomMandates, null, config)
}

const createRoomMandate = (staff, room, description, setRoomMandates) => {
  const newMandate = { 
    roomAdmin: staff,
    room: room,
    description: description,
    dateCreated: new Date(new Date().getTime() + 36000000).toISOString().split('T')[0] }
  
  console.log('New mandate:', newMandate)

  const config = {
    method: 'post',
    endpoint: 'mandates',
    data: newMandate
  };
  callApi(()=> getRoomMandates(room.roomId, setRoomMandates), null, config)
}

const updateRoomMandate = (mandate, setRoomMandates) => {
  const config = {
    method: 'put',
    endpoint: 'mandates',
    data : mandate
  };

  callApi(()=> getRoomMandates(mandate.room.roomId, setRoomMandates), null, config)
}

const deleteRoomMandate = (roomId, mandateId, setRoomMandates) => {
  const config = {
    method: 'delete',
    endpoint: `mandates/${mandateId}`
  };
  callApi(()=> getRoomMandates(roomId, setRoomMandates), null, config)
  
}

const RoomMandates = ({ room }) => {
  const { id } = useParams()
  const userId = id
  const [staff, setStaff] = useState('')
  const [roomMandates, setRoomMandates] = useState([])
  const [mandate, setMandate] = useState('')
  const [mandateDescription, setMandateDescription] = useState('')
  const [createMandateDialogue, setCreateMandateDialogue] = useState(false)
  const [editMandateDialogue, setEditMandateDialogue] = useState(false)

  useEffect(() => {
    getRoomMandates(room.roomId, setRoomMandates);
    getStaff(userId, setStaff);
  }, [])

  const handleCreate = () => {
    createRoomMandate(staff, room, mandateDescription, setRoomMandates)
    setCreateMandateDialogue(false)
    setMandateDescription('')
  }

  const editMandate = (mandate) => {
    setMandate(mandate)
    setMandateDescription(mandate.description)
    setEditMandateDialogue(true)
  }

  const handleUpdate = () => {
    const updatedMandate = { ...mandate, description: mandateDescription}
    updateRoomMandate(updatedMandate, setRoomMandates);
    setEditMandateDialogue(false)
    setMandateDescription('')
    setMandate(null)
  }

  return (
    <>
      <div>
        <h2>Mandates for {room.name} Room</h2>
            <TableContainer component={Paper}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell sx={{width: '70%'}}>Description</TableCell>
                    <TableCell sx={{width: '10%'}}>Date Created</TableCell>
                    <TableCell sx={{width: '10%'}}>Created By</TableCell>
                    <TableCell sx={{width: '10%'}}>Actions</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {roomMandates.map((mandate) => (
                    <TableRow key={mandate.mandateId}>
                      <TableCell>{mandate.description}</TableCell>
                      <TableCell>{new Date(mandate.dateCreated).toLocaleDateString()}</TableCell>
                      <TableCell>{mandate.roomAdmin.user.firstName} {mandate.roomAdmin.user.lastName}</TableCell>
                      <TableCell>
                        <IconButton color="success" onClick={() => deleteRoomMandate(room.roomId, mandate.mandateId, setRoomMandates)}>
                          <DoneIcon />
                        </IconButton>
                        <IconButton onClick={() => editMandate(mandate)}>
                          <EditIcon />
                        </IconButton>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
      </div>

      

      <Button variant="contained" onClick={()=>setCreateMandateDialogue(true)} sx={{marginLeft: 4.2, marginTop: 2}}>Create mandate</Button>

      <Dialog open={editMandateDialogue} onClose={()=>setEditMandateDialogue(false)} fullWidth>

        <DialogTitle>Editing mandate</DialogTitle>
        <DialogContent>
          <TextField
            value={mandateDescription}
            onChange={(e)=> setMandateDescription(e.target.value)}
            multiline
            fullWidth
            />
        </DialogContent>
        <DialogActions>
          <Button onClick={()=>{setMandateDescription('');setEditMandateDialogue(false)}}>Cancel</Button>
          <Button onClick={handleUpdate}>Save</Button>
        </DialogActions>

      </Dialog>

      <Dialog open={createMandateDialogue} onClose={()=>setCreateMandateDialogue(false)} fullWidth>

        <DialogTitle>Create a new mandate for {room.name}</DialogTitle>
        <DialogContent>
          <TextField
            value={mandateDescription}
            onChange={(e) => setMandateDescription(e.target.value)}
            multiline
            fullWidth
          />
        </DialogContent>

        <DialogActions>
          <Button onClick={()=>{setMandateDescription('');setCreateMandateDialogue(false)}}>Cancel</Button>
          <Button onClick={handleCreate}>Create</Button>
        </DialogActions>

      </Dialog>
    </>
  )
}

export default RoomMandates
