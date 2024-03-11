import { Box, Button, InputLabel, MenuItem, Modal, Select, TextField } from "@mui/material"
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";
import ProgramTransferList from "./ProgramTransferList";

import { Snackbar } from '@mui/material'
import { Alert } from '@mui/material'


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
            
    const [updated, setUpdated] = props.updated;
    const [open, setModal] = useState(false);
    const [code, setCode] = useState("");
    const [selectedRoom, setSelectedRoom] = useState({});
    const [error, setError] = useState(false);
    const [programList, setProgramList] = useState([]);
    const { computer, staff, rooms } = props;
    const [newComputer, onNewComputer] = useState(false);
    
    
    useEffect(() => {
        reset();
    }, []);

    const reset = () => {
        if (computer != null) {
            setCode(computer.computerCode);
            setProgramList(computer.programList);
        }
    }

    useEffect(() => {
        if (computer != null) {
            setSelectedRoom(rooms.find((x) => x.roomId == computer.room.roomId))
        } else {
            setSelectedRoom(rooms[0]);
        }

    }, [rooms]);

    const openModal = () => {
        setModal(true);
    };

    const closeModal = () => {
        setUpdated(false);
        setModal(false);
        reset();
    };

    const submitComputer = (update) => {
        if (code == "") {
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
        setUpdated(false);

        if (update) {
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
                    <h1>Add New Computer</h1>
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