import React from 'react'
import NavBar from '../partials/NavBar'
import { Box } from '@mui/material'
import { Typography } from '@mui/material'
import { Divider } from '@mui/material'
import { TextField } from '@mui/material'
import { Button } from '@mui/material'
import { useState } from "react";
import callApi from '../api/callApi'
import { CreateStaff } from '../partials/ManagePermission'
import { useParams } from 'react-router-dom';
import CheckIcon from '@mui/icons-material/Check';
import { Snackbar } from '@mui/material'
import { Alert } from '@mui/material'
import Fade from '@mui/material/Fade';
import Collapse from '@mui/material/Collapse';


const style = {
    // position: 'absolute',
    // top: '50%',
    // left: '50%',
    // transform: 'translate(-50%, -50%)',
	border: "1px solid black",
    width: 700,
    bgcolor: 'background.paper',
    // boxShadow: 24,
    padding: 6,
    borderRadius: 8,
};


const AddLocationPage = (props) => {
    const [locationName, setLocationName] = useState("")
    const [city, setCity] = useState("")
    const { id } = useParams();
    const [open, setOpen] = useState(false);
    const render = props.handle;


    const successNotification = () => {
        setOpen(true);

    }

    const handleClose = () => {

        setOpen(false);
    };

    const onCreation = (e) => {
        CreateStaff(successNotification, id, e.locationId, 1)
        render();

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
            Location has been created!
        </Alert>
        </Snackbar>



	<Fade in={true}>
    	<Box sx={style}>




			<Box>
				<Typography variant="h4">Create a New Location</Typography>
			</Box>
			<Divider sx={{ margin: "1rem 0 1rem 0" }} />
			<form onSubmit={createLocation} className="flexCol">
				<TextField
					size="medium"
					name="Location Name"
					label="Location Name"
					// error={Boolean(firstNameErr)}
					// helperText={firstNameErr ? firstNameErr : ""}
					// onChange={(e) => checkInput("firstName", e.target.value)}
                    onChange={(e) => setLocationName(e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>
				<TextField
					name="City"
					label="City"
					// error={Boolean(lastNameErr)}
					// helperText={lastNameErr ? lastNameErr : ""}
					// onChange={(e) => checkInput("lastName", e.target.value)}
                    onChange={(e) => setCity(e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>


				<Box className="centerHorizonal">
					<Button type="submit" variant="contained"  sx={{marginTop:"2rem"}}>
						Create
					</Button>
				</Box>
			</form>

		</Box>
		</Fade>

    </>
  )
}

export default AddLocationPage