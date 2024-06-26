import { Alert, Box, Button, Divider, Snackbar, TextField, Typography } from '@mui/material'
import Fade from '@mui/material/Fade'
import React, { useState } from 'react'
import callApi from '../api/callApi'
import { MultipleSelect } from "../partials/CheckBoxDropDowns"

const style = {
    width: 700,
    bgcolor: 'background.paper',
    border: "1px solid black",
    padding: 6,
    borderRadius: 8,
};

/**
 * Functional component for adding a new room
 * Allows users to create a new room
 * @returns - Displays this AddRoomPage
 */
const AddRoomPage = (props) => {

    const [location, setLocation] = useState(null)
    const [open, setOpen] = useState(false);
    const [roomName, setRoomName] = useState("")
    const [roomError, setRoomError] = useState(false);

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

    const roomHandle = (name) => {
        setRoomName(name)
        setRoomError(false);
    }

    const handleCheck = (value) => {
        setLocation(value)

    };

    const createRoom = (e) => {
        e.preventDefault();
        let localRoomError = !/^[a-zA-Z\s]*$/.test(roomName.trim());

        if (roomName.trim().length == 0) {
            setRoomError(true)
            return;
        }

        if (localRoomError) {
            setRoomError(true)
            localRoomError = true;
        }

        if (!localRoomError) {
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
                    A Room has been added!
                </Alert>
                
            </Snackbar>
            <Fade in={true}>
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
                            error={roomError}
                            helperText={roomError ? "Invalid Room Name" : ""}

                            onChange={(e) => roomHandle(e.target.value)}
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

export default AddRoomPage

