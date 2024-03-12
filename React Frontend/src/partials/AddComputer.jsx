import { Box, Button, InputLabel, MenuItem, Modal, Select, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import callApi from "../api/callApi";
import ProgramTransferList from "./ProgramTransferList";

import { Alert, Snackbar } from '@mui/material';

/**
 * Styling
 */
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

const AddComputer = (props) => {
            
    const [updated, setUpdated] = props.updated;            // Set update variable when returning on SearchComputerPage to force database refresh
    const [open, setModal] = useState(false);               // Variable indicates modal is currently open or nto
    const [code, setCode] = useState("");                   // Computer Code Text Field
    const [selectedRoom, setSelectedRoom] = useState({});   // Selected Room in 'select-room' drop-down menu
    const [error, setError] = useState(false);              // Indicator to display error message
    const [programList, setProgramList] = useState([]);     // List of programs to be added in computer
    const { computer, rooms } = props;                      // computer - current computer user is editing (only upon 'Edit Computer' button prompt)
                                                            // rooms - List of rooms user have admin privileges, limiting room choices for assignation
    const [newComputer, onNewComputer] = useState(false);   // Alert prompt when new computer added
    
    
    useEffect(() => {
        reset();
    }, []);

    /**
     * Reset function, will be ran everytime AddComputer component initialised, or its modal component opens.
     */
    const reset = () => {
        if (computer != null) {
            setCode(computer.computerCode);
            setProgramList(computer.programList);
        }
    }

    /**
     * When 'rooms' variable are fetched
     */
    useEffect(() => {
        if (computer != null) {
            setSelectedRoom(rooms.find((x) => x.roomId == computer.room.roomId))
        } else {
            setSelectedRoom(rooms[0]);
        }

    }, [rooms]);

    /**
     * Opens AddComputer modal
     */
    const openModal = () => {
        setModal(true);
    };

    /**
     * Closes AddComputer modal
     */
    const closeModal = () => {
        setUpdated(false);
        setModal(false);
        reset();
    };

    /**
     * Functionality upon click 'Add Computer' or 'Update Computer'
     * @param {boolean} update - Determine running POST or PUT method
     */
    const submitComputer = (update) => {
        if (code == "") {   // Error checking
            setError(true);
            return;
        }

        if (computer == null) {
            const computer = {
                "computerCode": parseInt(code),
                "room": selectedRoom,
                "programList": programList
            }

            const config = {
                method: "post",
                endpoint: "computers",
                data: computer
            }
            callApi(closeModal, null, config);
        } else {
            const computerClass = {
                "computerId": computer.computerId,
                "computerCode": parseInt(code),
                "room": selectedRoom,
                "programList": programList
            }

            const config = {
                method: "put",
                endpoint: "computers",
                data: computerClass
            }
            callApi(closeModal, null, config);

        }
        setUpdated(false);  // Database no longer updated, need refresh

        if (update) {       // Alert Prompt
            props.onUpdate();
        } else {
            onNewComputer(true);
        }
    }

    return (
        <>

            <Snackbar open={newComputer} autoHideDuration={6000} onClose={()=>onNewComputer(false)}>
                    <Alert
                        onClose={()=>onNewComputer(false)}
                        severity="success"
                        variant="filled"
                        sx={{ width: '100%' }}
                    >
                        A new Computer has been Added!
                    </Alert>
            </Snackbar>

            

            

            <Modal
                open={open}
                onClose={closeModal}
            >
                <Box sx={style}>
                   
                    {computer == null ?
                        <h1>Add New Computer</h1>
                        :
                        <h1>Edit Computer</h1>
                    }
                    <TextField
                        value={code}
                        label={'Computer Code'}
                        onChange={(e) => {
                            if (/^\d+$/.test(e.target.value) || e.target.value == "") {
                                setCode(e.target.value);
                            }
                        }}
                    />
                    {error && <p style={{ color: 'red' }}>Must be filled</p>}
                    <h3>Room:
                        <InputLabel id="select-room">Select Room</InputLabel>
                        <Select
                            labelId="select-room"
                            value={selectedRoom}
                            onChange={(e) => setSelectedRoom(e.target.value)}
                        >
                            {
                                rooms.map(room =>
                                    <MenuItem value={room} key={room.roomId}>{room.name}, {room.location.name}</MenuItem>
                                )
                            }
                        </Select>
                    </h3>
                    <ProgramTransferList programs={[programList, setProgramList]} />
                    <br></br>
                    {computer == null ?
                        <Button
                            variant="contained"
                            onClick={() => submitComputer(false)}
                           
                        >
                            Add Computer
                        </Button>
                        :
                        <Button
                            variant="contained"
                            onClick={() => submitComputer(true)}
                        >
                            Update Computer
                        </Button>
                    }
                </Box>
            </Modal>
            {(() => {
                if (rooms.length > 0) {
                    if (computer == null) {
                        return (
                            <Button
                                variant="contained"
                                disableElevation
                                sx={{
                                    position: "fixed",
                                    top: "28%",
                                    right: "1.5%",
                                    zIndex: 1000,
                                }}
                                onClick={openModal}
                            >
                                Add Computer
                            </Button>
                        )
                    } else {
                        return (
                            <Button
                                variant="contained"
                                color="success"
                                sx={{ margin: "50px" }}
                                onClick={openModal}
                            >
                                Edit Computer
                            </Button>
                        )
                    }
                }
            }
            )()}
        </>
    )
}

export default AddComputer;