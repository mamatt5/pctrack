import React from 'react'
import NavBar from '../partials/NavBar'
import { Box } from '@mui/material'
import { Typography } from '@mui/material'
import { Divider } from '@mui/material'
import { TextField } from '@mui/material'
import { Button } from '@mui/material'
import { useState } from "react";
import callApi from '../api/callApi'
import { createStaff } from '../partials/ManagePermission'
import { useParams } from 'react-router-dom';
import { Alert } from '@mui/material'
import { Snackbar } from '@mui/material'
import { MultipleSelect } from "../partials/CheckBoxDropDowns";

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 700,
    bgcolor: 'background.paper',
    boxShadow: 24,
    padding: 6,
    borderRadius: 8,
};



const addRoomPage = (props) => {

    const [location, setLocation] = useState(null)
    const [roomName, setRoomName] = useState("")

    const [open, setOpen] = useState(false);
	const registerableLocations = props.currStaff.map((item) => {

		if (item.adminLevel.precedence < 3) {
			return item.location
		} else {
			return null
		}
	}).filter((location) => location !== null);


    const successNotification = () => {
        setOpen(true);

    }

    const handleClose = () => {
    
        setOpen(false);
      };



	const handleCheck = (value) => {
		setLocation(value)
	
	};

    const createRoom = (e) => {
        e.preventDefault();
	
        const config = {
            method: "post",
            endpoint: "rooms",
            data: {
                "name": roomName, 
                "location": location[0]
            }
        }
        callApi(successNotification, null, config);
    }
  return (

    <>
        <NavBar admin={props.admin} />
        
        <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <Alert
            onClose={handleClose}
            severity="success"
            variant="filled"
            sx={{ width: '100%' }}
        >
            A Room has been added!
        </Alert>

        </Snackbar>


    <Box sx={style}>
			<Box>
				<Typography variant="h4">Create a New Room</Typography>
			</Box>
			<Divider sx={{ margin: "1rem 0 1rem 0" }} />
			<form onSubmit={createRoom} className="flexCol">

				<Box sx={{ margin: "0.5rem" }} >
					{MultipleSelect(
						registerableLocations,
						"city",
						"Locations",
						"Add Locations",
						true,
						handleCheck
					)}
				</Box>

				<TextField
					name="Room Name"
					label="Room Name"
					// error={Boolean(lastNameErr)}
					// helperText={lastNameErr ? lastNameErr : ""}
					// onChange={(e) => checkInput("lastName", e.target.value)}
                    onChange={(e) => setRoomName(e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>

				<Box className="centerHorizonal">
					<Button type="submit" variant="contained"  sx={{marginTop:"2rem"}}>
						Create
					</Button>
				</Box>
			</form>
		</Box>
    </>
  )
}

export default addRoomPage

// 