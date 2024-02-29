import React from 'react'
import NavBar from '../partials/NavBar'
import { useLocation } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import { Box } from '@mui/material';


export const ViewComputersInRoomPage = (props) => {
    const {state} = useLocation();
    const [room, setRoom] = useState({});
    const [computers, setComputers] = useState([]);
    

    

    useEffect(() => {
		setRoom(state)

	}, []);

    console.log("please appear")
    console.log(room)
  return (

    <>
        <NavBar />

        <Box sx={{ display: "flex", justifyContent: "center" }}>
        <h1>You are now viewing room {room["name"]} </h1>


        </Box>
        

    </>
    

  )
}
