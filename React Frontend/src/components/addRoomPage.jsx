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

    const [locationName, setLocationName] = useState("")
    const [city, setCity] = useState("")
    const { id } = useParams();
    const [open, setOpen] = useState(false);


    const successNotification = () => {
        setOpen(true);

    }

    const handleClose = () => {
    
        setOpen(false);
      };

    const onCreation = (e) => {
        createStaff(successNotification, id, e.locationId, 1)

    }


    const createLocation = (e) => {
        e.preventDefault();
        console.log(locationName)
        console.log(city)

        const config = {
            method: "post",
            endpoint: "locations",
            data: {
                "name": locationName, 
                "city": city
            }
        }
        callApi(onCreation, null, config);
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
			<form onSubmit={createLocation} className="flexCol">
				<TextField
					size="medium"
					name="Location"
					label="Location"
					// error={Boolean(firstNameErr)}
					// helperText={firstNameErr ? firstNameErr : ""}
					// onChange={(e) => checkInput("firstName", e.target.value)}
                    onChange={(e) => setLocationName(e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>
				<TextField
					name="Room Name"
					label="Room Name"
					// error={Boolean(lastNameErr)}
					// helperText={lastNameErr ? lastNameErr : ""}
					// onChange={(e) => checkInput("lastName", e.target.value)}
                    onChange={(e) => setCity(e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>

				{/* <TextField
					name="email"
					label="Email"
					// error={Boolean(emailErr)}
					// helperText={emailErr ? emailErr : ""}
					// onChange={(e) => checkInput("email", e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>
				<TextField
					name="password"
					label="Password"
					// error={Boolean(passwordErr)}
					// helperText={passwordErr ? passwordErr : ""}
					// onChange={(e) => checkInput("password", e.target.value)}
					sx={{ margin: "0.5rem" }}
				/> */}

				{/* <Box sx={{ margin: "0.5rem" }} >
					{MultipleSelect(
						locations,
						"city",
						"Locations",
						"Add Locations",
						true,
						handleCheck
					)}
				</Box> */}
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