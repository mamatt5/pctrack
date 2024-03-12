import React from 'react'
import callApi from "../api/callApi";
import { useState } from 'react';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import CustomizedTables from '../partials/roomTable'
import { Box } from '@mui/material';
import { CheckAdmin } from "../components/LoggedInHomePage";

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

    CheckAdmin(setAdmin, setStaff, id);
    staff.forEach(staffMember => {

			let precedence = staffMember.adminLevel.precedence;

			if (precedence === 1) {
				setBusinessAdmin(true);
      }
		});

    let config = {};
   
    if (businessAdmin) {
      config = {
        method: "get",
        endpoint: "staff/getrooms/"+id,

      }
    } else {

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
