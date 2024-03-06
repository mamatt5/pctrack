import React from 'react'
import NavBar from '../partials/NavBar'
import { Box } from '@mui/material'
import { Typography } from '@mui/material'
import { Divider } from '@mui/material'
import { TextField } from '@mui/material'
import { Button } from '@mui/material'
import { useState } from "react";
import callApi from '../api/callApi'


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


const addLocationPage = (props) => {
    const [locationName, setLocationName] = useState("")
    const [city, setCity] = useState("")

    const onCreation = () => {

        console.log("created that shit")
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

export default addLocationPage