import DoneIcon from '@mui/icons-material/Done';
import EditIcon from '@mui/icons-material/Edit';
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import callApi from '../api/callApi';

const getStaff = (userId, setStaff, setHasPermission) => {
  const config = {
    method: 'get',
    endpoint: `staff/${userId}`
  };
  callApi((response) => {
    const adminLevels = ['Room', 'Location', 'Business']
    const roomAdmin = response.find(staff => adminLevels.some(level => staff.adminLevel.name === level));
    setStaff(roomAdmin);
    setHasPermission(!!roomAdmin)
  }, null, config);
}

const getRoomMandates = (roomId, setRoomMandates) => {
  const config = {
    method: 'get',
    endpoint: `rooms/${roomId}/mandates`
  };
  callApi(setRoomMandates, null, config)
}

const createRoomMandate = (staff, room, description, deadline, setRoomMandates) => {
  const newMandate = { 
    roomAdmin: staff,
    room: room,
    description: description,
    
    // Convert timestamp from UTC to AEST
    dateCreated: new Date(new Date().getTime() + 36000000).toISOString().split('T')[0],
    deadline: deadline }

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
  const [hasPermission, setHasPermission] = useState(false)
  const [roomMandates, setRoomMandates] = useState([])
  const [mandate, setMandate] = useState('')
  const [mandateDescription, setMandateDescription] = useState('')
  const [mandateDeadline, setMandateDeadline] = useState(new Date().toISOString().split('T')[0])
  const [createMandateDialogue, setCreateMandateDialogue] = useState(false)
  const [editMandateDialogue, setEditMandateDialogue] = useState(false)

  useEffect(() => {
    getRoomMandates(room.roomId, setRoomMandates);
    getStaff(userId, setStaff, setHasPermission);
  }, [])

  const handleCreate = () => {
    createRoomMandate(staff, room, mandateDescription, mandateDeadline, setRoomMandates)
    setCreateMandateDialogue(false)
    setMandateDescription('')
  }

  const editMandate = (mandate) => {
    setMandate(mandate)
    setMandateDescription(mandate.description)
    setMandateDeadline(mandate.deadline)
    setEditMandateDialogue(true)
  }

  const handleUpdate = () => {
    const updatedMandate = { ...mandate, description: mandateDescription, deadline: mandateDeadline}
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
                  <TableRow sx={{backgroundColor: '#1976d2'}}>
                    <TableCell sx={{width: '50%', color: 'white'}}>Description</TableCell>
                    <TableCell sx={{width: '10%', color: 'white'}}>Date Created</TableCell>
                    <TableCell sx={{width: '10%', color: 'white'}}>Deadline</TableCell>
                    <TableCell sx={{width: '15%', color: 'white'}}>Created By</TableCell>
                    <TableCell sx={{width: '15%', color: 'white'}}>Actions</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {roomMandates.length > 0 ? (
                    roomMandates.map((mandate) => (
                      <TableRow key={mandate.mandateId}>
                        <TableCell>{mandate.description}</TableCell>
                        <TableCell>{new Date(mandate.dateCreated).toLocaleDateString('en-GB')}</TableCell>
                        <TableCell>{new Date(mandate.deadline).toLocaleDateString('en-GB')}</TableCell>
                        <TableCell>{mandate.roomAdmin.user.firstName} {mandate.roomAdmin.user.lastName}</TableCell>
                        <TableCell>
                          <IconButton color="success" onClick={() => deleteRoomMandate(room.roomId, mandate.mandateId, setRoomMandates)}>
                            <DoneIcon />
                          </IconButton>
                          { hasPermission &&
                            <IconButton onClick={() => editMandate(mandate)}>
                              <EditIcon />
                            </IconButton>}
                        </TableCell>
                      </TableRow>
                    ))) : (
                      <TableRow>
                        <TableCell colSpan={5} align="center">No mandates available for {room.name} </TableCell>
                      </TableRow>
                    )}
                </TableBody>
              </Table>
            </TableContainer>
      </div>

      

      {hasPermission && 
      <Button variant="contained" onClick={()=>setCreateMandateDialogue(true)} sx={{marginLeft: 4.2, marginTop: 2}}>
        Create mandate</Button>}

      <Dialog open={editMandateDialogue} onClose={()=>setEditMandateDialogue(false)} fullWidth maxWidth='lg'>

        <DialogTitle>Editing mandate</DialogTitle>
        <DialogContent>
          <TextField
            value={mandateDescription}
            onChange={(e)=> setMandateDescription(e.target.value)}
            multiline
            fullWidth
            />
          <TextField
            type='date'
            label='Deadline'
            value={mandateDeadline}
            onChange={(e) => setMandateDeadline(e.target.value)}
            sx={{marginTop: 2}}
            />
        </DialogContent>
        <DialogActions>
          <Button onClick={()=>{setMandateDescription('');setEditMandateDialogue(false)}}>Cancel</Button>
          <Button onClick={handleUpdate}>Save</Button>
        </DialogActions>

      </Dialog>

      <Dialog open={createMandateDialogue} onClose={()=>setCreateMandateDialogue(false)} fullWidth maxWidth='lg'>

        <DialogTitle>Create a new mandate for {room.name}</DialogTitle>
        <DialogContent>
          <TextField
            value={mandateDescription}
            onChange={(e) => setMandateDescription(e.target.value)}
            multiline
            fullWidth
          />
          <TextField
            type='date'
            label='Deadline'
            value={mandateDeadline}
            onChange={(e) => setMandateDeadline(e.target.value)}
            sx={{marginTop: 2}}
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
