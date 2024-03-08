import { Box, Button, InputLabel, MenuItem, Modal, Select, TextField } from "@mui/material"
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";
import ProgramTransferList from "./ProgramTransferList";

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

const checkStaff = (staff) => {
    var bool = false;
    staff.map(role => {
        if (role.adminLevel.precedence != 100) {
            bool = true;
        }
    })
    return bool;
}

const AddComputer = (props) => {
    const [updated, setUpdated] = props.updated;
    const [open, setModal] = useState(false);
    const [code, setCode] = useState("");
    const [rooms, setRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState({});
    const [error, setError] = useState(false);
    const [programList, setProgramList] = useState([]);
    const { computer, staff } = props;

    useEffect(() => {
        if (computer != null) {
            setCode(computer.computerCode);
            setProgramList(computer.programList);
        }
    }, []);

    useEffect(() => {
        if (staff.length != 0) {
            getRooms();
        }
    }, [staff]);

    useEffect(() => {
        if (computer != null) {
            setSelectedRoom(rooms.find((x) => x.roomId == computer.room.roomId))
        } else {
            setSelectedRoom(rooms[0]);
        }

    }, [rooms]);
    
    const getRooms = () => {
        const config = {
            method: "get",
            endpoint: "rooms"
        }
    
        callApi((e) => {
            var rooms = [];
            console.log(staff);
            staff.map(role => {
                switch(role.adminLevel.precedence) {
                    case 2:
                    case 1:
                        var arr = e.filter(x => x.location.locationId == role.location.locationId);
                        rooms = rooms.concat(arr);
                        break;
                    case 3:
                        if (role.roomAssigned.length != 0) {
                            rooms = rooms.concat(role.roomAssigned);
                        } else {
                            var arr = e.filter(x => x.location.locationId == role.location.locationId);
                            rooms = rooms.concat(arr);
                        }
                        break;
                }
            });
            console.log(rooms);
            setRooms(rooms);
        }, null, config);
    }

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
                            onClick={submitComputer}
                        >
                            Add Computer
                        </Button>
                        :
                        <Button
                            variant="contained"
                            onClick={submitComputer}
                        >
                            Update Computer
                        </Button>
                    }
                </Box>
            </Modal>
            {(() => {
                if (checkStaff(staff)) {
                    if (computer == null) {
                        return (
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