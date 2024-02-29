import React from 'react'
import NavBar from '../partials/NavBar'
import ComputerCard from '../partials/ComputerCard'
import callApi from "../api/callApi";
import { useState } from 'react';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Grid } from '@mui/material';
import { Divider } from '@mui/material';
import CustomizedTables from '../partials/roomTable'
import { Box } from '@mui/material';

export const SearchRoomPage = () => {


    
  const [rooms, setRooms] = useState([]);
 

  const test = (input) => {
    console.log("cancer")
    console.log(input)
    setRooms(input)
  }


  const { id } = useParams()

  useEffect(()=>{

    const config = {
      method: "get",
      endpoint: "staff/getrooms/"+id,

    }

    callApi(test, null, config);
  }, []);

   




  return (
    <>
      <Box sx={{ display: "flex", justifyContent: "center" }}>
        <CustomizedTables array={rooms} />
      </Box>


    </>
  )
}
