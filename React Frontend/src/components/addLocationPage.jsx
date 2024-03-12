import React from 'react'
import { Box } from '@mui/material'
import { Typography } from '@mui/material'
import { Divider } from '@mui/material'
import { TextField } from '@mui/material'
import { Button } from '@mui/material'
import { useState } from "react";
import callApi from '../api/callApi'
import { CreateStaff } from '../partials/ManagePermission'
import { useParams } from 'react-router-dom';
import { Snackbar } from '@mui/material'
import { Alert } from '@mui/material'
import Fade from '@mui/material/Fade';


const style = {
    border: "1px solid black",
    width: 700,
    bgcolor: 'background.paper',
    padding: 6,
    borderRadius: 8,
};


const AddLocationPage = (props) => {

    const [locationName, setLocationName] = useState("")
    const [cityName, setCityName] = useState("")
    const { id } = useParams();
    const [open, setOpen] = useState(false);
    const [locationError, setLocationError] = useState(false);
    const [cityError, setCityErrorr] = useState(false);

    const successNotification = () => {
        setOpen(true);
    }

    const handleClose = () => {
        setOpen(false);
    };

    const onCreation = (e) => {
        CreateStaff(successNotification, id, e.locationId, 1)
        props.setRender(c => !c)
    }

    const locationHandle = (name) => {
        setLocationName(name)
        setLocationError(false);

    }

    const cityHandle = (name) => {
        setCityName(name)
        setCityErrorr(false);
    }
    const createLocation = (e) => {
        e.preventDefault();

        let locallocationError = !/^[a-zA-Z\s]*$/.test(locationName.trim());
        let localcityError = !/^[a-zA-Z\s]*$/.test(cityName.trim());

        if (locationName.trim().length == 0) {
            locallocationError = true;
            setLocationError(true)
        }

        if (cityName.trim().length == 0) {
            localcityError = true;
            setCityErrorr(true)
        }

        if (locallocationError) {
            setLocationError(true)
        }

        if (localcityError) {
            setCityErrorr(true)
        }


        if (!locallocationError && !localcityError) {
            const config = {
                method: "post",
                endpoint: "locations",
                data: {
                    "name": locationName,
                    "city": cityName
                }
            }
            callApi(onCreation, null, config);
        }

    }

    return (
        <>
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
                            name="City"
                            label="City"
                            error={cityError}
                            helperText={cityError ? "Invalid City Name" : ""}
                            onChange={(e) => cityHandle(e.target.value)}
                            sx={{ margin: "0.5rem" }}
                        />

                        <TextField
                            size="medium"
                            name="Location Name"
                            label="Location Name"
                            error={locationError}
                            helperText={locationError ? "Invalid Location Name" : ""}
                            onChange={(e) => locationHandle(e.target.value)}
                            sx={{ margin: "0.5rem" }}
                        />

                        <Box className="centerHorizonal">
                            <Button type="submit" variant="contained" sx={{ marginTop: "2rem" }}>
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