import { Box, Button, InputLabel, MenuItem, Modal, Select, TextField } from "@mui/material"
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";

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

const getRooms = (setRooms) => {
    const config = {
        method: "get",
        endpoint: "rooms"
    }

    callApi(setRooms, null, config);
}

const AddComputer = (props) => {
    const [updated, setUpdated] = props.updated;
    const [open, setModal] = useState(false);
    const [code, setCode] = useState("");
    const [rooms, setRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState({});
    const [error, setError] = useState(false);

    useEffect(() => {
        getRooms(setRooms);
    }, [])

    useEffect(() => {
        setSelectedRoom(rooms[0]);
    }, [rooms])

    const openModal = () => {
        setModal(true);
    };

    const closeModal = () => {
        setUpdated(false);
        setModal(false);
    };

    const submitComputer = () => {
        if (code == "") {
            setError(true);
            return;
        }
        const computer = {
            "computerCode": parseInt(code),
            "room": selectedRoom,
            "programList": []
        }

        const config = {
            method: "post",
            endpoint: "computers",
            data: computer
        }
        callApi(closeModal, null, config);
    }

    return (
        <>
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
                    {error && <p style={{color: 'red'}}>Must be filled</p>}
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
                    <Button
                        variant="contained"
                        onClick={submitComputer}
                    >
                        Add Computer
                    </Button>
                </Box>
            </Modal>
            <Button
                variant="contained"
                disableElevation
                sx={{
                    position: "fixed",
                    bottom: "10%",
                    right: "10%",
                    zIndex: 1000,
                }}
                onClick={openModal}
            >
                Add New Computer
            </Button>
        </>
    )
}

export default AddComputer;