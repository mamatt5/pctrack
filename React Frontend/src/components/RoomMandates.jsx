import { Button, Dialog, DialogTitle, DialogContent, DialogContentText, DialogActions, TextField, IconButton } from '@mui/material'
import React, { useEffect, useState } from 'react'
import callApi from '../api/callApi'
import DoneIcon from '@mui/icons-material/Done';
import EditIcon from '@mui/icons-material/Edit';

const getRoomMandates = (roomId, setRoomMandates) => {
  const config = {
    method: 'get',
    endpoint: `rooms/${roomId}/mandates`
  };
  callApi(setRoomMandates, null, config)
}

const createRoomMandate = (room, description, setRoomMandates) => {
  const newMandate = { 
    room: room,
    description: description }

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
  const [roomMandates, setRoomMandates] = useState([])
  const [mandate, setMandate] = useState('')
  const [mandateDescription, setMandateDescription] = useState('')
  const [createMandateDialogue, setCreateMandateDialogue] = useState(false)
  const [editMandateDialogue, setEditMandateDialogue] = useState(false)

  useEffect(() => {
    getRoomMandates(room.roomId, setRoomMandates);
  }, [])

  const handleCreate = () => {
    createRoomMandate(room, mandateDescription, setRoomMandates)
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
        <ul style={{listStyle: 'none'}}>
          <h2>Mandates for {room.name} room</h2>
          {roomMandates.map(mandate =>
            <li key={mandate.mandateId} style={{ paddingBottom: '10px' }} >{mandate.description}
      
              {/* <Button variant="outlined" onClick={()=> deleteRoomMandate(room.roomId,mandate.mandateId,setRoomMandates)} sx={{marginLeft: 3}}>
                Satisfied</Button> */}
              <IconButton color='success' onClick={()=> deleteRoomMandate(room.roomId,mandate.mandateId,setRoomMandates)} sx={{marginLeft: 3}}>
                <DoneIcon />
              </IconButton>

              {/* <Button variant="outlined" onClick={()=> editMandate(mandate)} sx={{marginLeft: 3}}>Edit</Button> */}
              <IconButton onClick={()=> editMandate(mandate)} sx={{marginLeft: 3}}>
                <EditIcon />

              </IconButton>
                </li>
                
                )}
        </ul>
      </div>

      <Button variant="contained" onClick={()=>setCreateMandateDialogue(true)} sx={{marginLeft: 4.2}}>Create mandate</Button>

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
