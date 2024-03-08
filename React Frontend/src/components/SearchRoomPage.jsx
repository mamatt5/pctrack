import React from 'react'
import NavBar from '../partials/NavBar'
import ComputerCard from '../partials/ComputerCard'
import callApi from "../api/callApi";
import { useState } from 'react';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import CustomizedTables from '../partials/roomTable'
import { Box } from '@mui/material';
import { checkAdmin } from "../components/LoggedInHomePage";

export const SearchRoomPage = (props) => {

  const [rooms, setRooms] = useState([]);
  const [businessAdmin, setBusinessAdmin] = useState(false);
  const [admin, setAdmin] = useState(false);
  const [staff, setStaff] = useState([]);

  const test = (input) => {
    setRooms(input)
  }

  const { id } = useParams()

  useEffect(()=>{
    checkAdmin(setAdmin, setStaff, id);
 
    staff.forEach(staffMember => {
 
			let precedence = staffMember.adminLevel.precedence;
      console.log("BUBU")
      console.log(staffMember)
			if (precedence === 1) {  
				setBusinessAdmin(true);
      }
		});

    let config = {};
    console.log("well fuck")
    if (businessAdmin) {
      config = {
        method: "get",
        endpoint: "staff/getrooms/"+id,
  
      }
    } else {
      // get rooms where user is register at
     
      config = {
        method: "get",
        endpoint: "staff/getregisteredrooms/"+ id,
  
      }

    }
    
    callApi(test, null, config);

  }, []);
  const RoomTab = () => {
    return <></>;
  };

  return (
    <>
      <Box className="dashBoardPadding">
      <h1>Rooms</h1>
       

        <Box sx={{ display: "flex", flexDirection: "row" }}>
                    <RoomTab />
                  </Box>
            <CustomizedTables array={rooms} />
      </Box>


    </>
  )
}
