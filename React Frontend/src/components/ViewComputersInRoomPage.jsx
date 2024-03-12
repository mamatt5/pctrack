import React from 'react'
import NavBar from '../partials/NavBar'
import { useLocation } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import { Box, Button, Dialog, DialogContent } from '@mui/material';
import callApi from '../api/callApi';
import ComputerCard from '../partials/ComputerCard';
import { Grid } from '@mui/material';
import RoomMandates from './RoomMandates';
import ComputerIcon from '@mui/icons-material/Computer';


const ViewComputersInRoomPage = () => {
    const { state } = useLocation();
    const [room, setRoom] = useState({});
    const [computers, setComputers] = useState([])
    const [mandatesModal, setMandatesModal] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const [updated, setUpdated] = useState(true);

    const handleMandatesModal = (open) => {
        setMandatesModal(open)
    }

    useEffect(() => {
        setRoom(state)

        const config = {
            method: "get",
            endpoint: "getcomputers/" + state["roomId"],

        }

        callApi(setComputers, null, config);
    }, []);


    return (
        <Box>
            <NavBar admin={true} />
            <Box sx={{ display: "flex", justifyContent: "center" }}>


                <Box style={{ position: 'flex' }}>
                    <h1> You are now viewing room {room["name"]}</h1>
                    <Button variant="contained" onClick={() => handleMandatesModal(true)}>View Mandates</Button>
                </Box>

            </Box>

            <Dialog open={mandatesModal} onClose={() => handleMandatesModal(false)} fullWidth maxWidth="xl">
                <DialogContent>
                    <RoomMandates room={room} />
                </DialogContent>
            </Dialog>


            <Box style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }} marginTop={10}>


                <Grid sx={{ flexGrow: 1 }} container spacing={2} sm={5}>

                    <Grid item xs={12} >
                        <Grid container justifyContent="center" spacing={5} >
                            {
                                computers.map(computer =>

                                    <ComputerCard key={computer.computerId} computer={computer} rooms={[]} updated={[updated, setUpdated]}/>

                                )
                            }
                        </Grid>
                    </Grid>

                </Grid>
            </Box>
            <Box
                sx={{
                    position: 'fixed',
                    top: 80,
                    right: 20,
                    backgroundColor: 'lightgray',
                    padding: '10px',
                    borderRadius: '10px',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'left'
                }}
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}>

        {isHovered && (
            <>
                <div style={{ display: 'flex', alignItems: 'center', marginBottom: '5px' }}>
                    <ComputerIcon style={{ color: '#77DD77'}} /> Both roles
                </div>
                <div style={{ display: 'flex', alignItems: 'center', marginBottom: '5px' }}>
                    <ComputerIcon style={{ color: '#ffff66'}} /> Dev/BI-ready
                </div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <ComputerIcon style={{ color: '#FF6961'}} /> None
                </div>
                <div style={{fontSize: '11px'}}><em>
                  *Check Help for Dev/BI software list
                  </em></div>
            </>
        )}
        {!isHovered && (
            <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <ComputerIcon style={{ color: '#77DD77'}} />
                <ComputerIcon style={{ color: '#ffff66'}} />
                <ComputerIcon style={{ color: '#FF6961'}} />
            </div>
        )}
      </Box>

        </Box>
    )
}

export default ViewComputersInRoomPage