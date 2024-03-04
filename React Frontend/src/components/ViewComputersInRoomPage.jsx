import React from 'react'
import NavBar from '../partials/NavBar'
import { useLocation } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import { Box, Button, Dialog, DialogContent } from '@mui/material';
import callApi from '../api/callApi';
import ComputerCard from '../partials/ComputerCard';
import { Typography } from '@mui/material';
import { Grid } from '@mui/material';
import { Divider } from '@mui/material';
import RoomMandates from './RoomMandates';




const ViewComputersInRoomPage = (props) => {
    const {state} = useLocation();
    const [room, setRoom] = useState({});
    const [computers, setComputers] = useState([])
    const [mandatesModal, setMandatesModal] = useState(false);

    const handleMandatesModal = (open) => {
      setMandatesModal(open)
    }

    useEffect(() => {
      setRoom(state)

      const config = {
        method: "get",
        endpoint: "getcomputers/"+state["roomId"],
  
      }

      callApi(setComputers, null, config);
	  }, []);

    
  return (
    <Box>
      <NavBar admin={true}/>
      <Box sx={{ display: "flex", justifyContent: "center" }}>
      

        <Box style={{ position: 'flex' }}>
        <h1> You are now viewing room {room["name"]}</h1>
        <Button variant="contained" onClick={()=> handleMandatesModal(true)}>View Mandates</Button>
        </Box>

      </Box>

      <Dialog open={mandatesModal} onClose={() => handleMandatesModal(false)} fullWidth maxWidth="xl">
        <DialogContent>
            <RoomMandates room={room} />
        </DialogContent>
      </Dialog>


      <Box style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}} marginTop={10}>
        
        
          <Grid sx={{ flexGrow: 1 }} container spacing={2} sm={5}>

              <Grid item xs={12} >
                <Grid container justifyContent="center" spacing={5} >
                  {
                  computers.map(computer =>
                    
                    <ComputerCard key={computer.computerId} computer={computer} />
                  
                  )
              }
                </Grid>
              </Grid>
              
          </Grid>
      </Box>
    </Box>





       
    

  )
}

export default ViewComputersInRoomPage