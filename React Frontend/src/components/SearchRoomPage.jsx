import { Box } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import callApi from "../api/callApi";
import { CheckAdmin } from "../components/LoggedInHomePage";
import CustomizedTables from '../partials/roomTable';


/**
 * Functional component that allows for viewing rooms a user is registered in
 * Allows users to view rooms
 * @returns - Displays this SearchRoomPage
 */
export const SearchRoomPage = () => {

  const [rooms, setRooms] = useState([]);
  const [businessAdmin, setBusinessAdmin] = useState(false);
  const [admin, setAdmin] = useState(false);
  const [staff, setStaff] = useState([]);

  const setRoom = (input) => {
    setRooms(input)
  }

  const { id } = useParams()

  // If user is a business Admin gets all rooms
  // otherwise gets rooms at locations where the user
  // is registered in 
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

    callApi(setRoom, null, config);

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
