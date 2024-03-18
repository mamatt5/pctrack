import { Box, Button, InputLabel, MenuItem, Modal, Select, TextField } from "@mui/material";
import { useEffect } from "react";
import { useState } from "react";
import callApi from "../api/callApi";

const getSoftwares = (setSoftwares) => {
    const config = {
        method: "get",
        endpoint: "softwares",
    };
    callApi(setSoftwares, null, config);
}

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
    textAlign: 'center',
};

const AddProgram = (props) => {
    const [open, setOpen] = props.open;
    const [ softwares, setSoftwares ] = useState([]);
    const [ software, setSoftware ] = useState({});
    const [ disableTF, setDisableTF ] = useState(false);
    const [ newSoftware, setNewSoftware ] = useState('');
    const [ version, setVersion ] = useState('');
    const [ error, setError ] = useState('');

    useEffect(() => {
        getSoftwares(setSoftwares);
        setSoftware('new');
    }, []);

    useEffect(() => {
        if (software === 'new') {
            setDisableTF(false);
        } else {
            setDisableTF(true);
        }
    }, [software]);

    const openModal = () => {
        setOpen(true);
    }

    const closeModal = () => {
        setOpen(false);
    }

    const onSubmit = () => {
        if (software === 'new' && newSoftware === '') {
            setError('Please enter software name');
            return;
        }
        if (!/^[1-9]\d*(\.[1-9]\d*)*$/.test(version)) {
            setError('Please enter a valid version number');
            return;
        }

        if (software === 'new') {
            const softwareClass = {
                "name": newSoftware
            }

            const config = {
                method: "post",
                endpoint: "softwares",
                data: softwareClass
            }

            callApi((e) => {
                const newProgram = {
                    "software": e,
                    "version": version
                }

                const config = {
                    method: "post",
                    endpoint: "programs",
                    data: newProgram
                }

                callApi(closeModal, null, config);
            }, null, config);
        } else {
            const newProgram = {
                "software": software,
                "version": version
            }

            const config = {
                method: "post",
                endpoint: "programs",
                data: newProgram
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
                    <h1 style={{textAlign: "center"}}>Add Computer</h1>
                    <div>
                        <InputLabel id="software-label">Software</InputLabel>
                        <Select
                            labelId="software-label"
                            id="software"
                            variant="outlined"
                            label="Room"
                            value={software}
                            onChange={(e) => setSoftware(e.target.value)}
                        >
                            <MenuItem value="new">New Software</MenuItem>
                            { 
                                softwares.map(item => 
                                    <MenuItem key={item.softwareId} value={item}>{item.name}</MenuItem>
                                )
                            }
                        </Select>
                    </div>
                    <Box
                        component={"form"}
                        sx={{
                            '& > :not(style)': { m: 1, width: '25ch' },
                        }}
                    >
                        <TextField
                            variant="outlined"
                            label="Software Name"
                            disabled={disableTF}
                            value={newSoftware}
                            onChange={(e) => setNewSoftware(e.target.value)}
                        />
                        <TextField
                            variant="outlined"
                            label="Version"
                            value={version}
                            onChange={(e) => {
                                if (/^\d(\d|\.)*$/.test(e.target.value) || e.target.value === "") {
                                    setVersion(e.target.value)
                                }
                            }}
                        />
                    </Box>
                    { error && <p style={{ color: 'red' }}>{error}</p> }
                    <Button variant="contained" onClick={onSubmit}>Add Program</Button>
                </Box> 
            </Modal>
            <Button
                variant="contained"
                disableElevation
                sx={{
                    position: "fixed",
                    top: "28%",
                    right: "15%",
                    zIndex: 1000,
                }}
                onClick={openModal}
            >
                Add Program
            </Button>
        </>
    )
}

export default AddProgram;