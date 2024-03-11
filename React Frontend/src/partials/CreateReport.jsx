import { Box, Button, InputLabel, MenuItem, Modal, Select, TextField } from "@mui/material"
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";
import { Autocomplete } from '@mui/material';
import { useParams } from "react-router-dom";
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

const getComputers = (setComputers) => {
    const config = {
        method: "get",
        endpoint: "computers"
    }

    callApi(setComputers, null, config);
}

const getUser = (id, setUser) => {
	const config = {
		method: "get",
		endpoint: `users/${id}`,
	};
	callApi(setUser, null, config);
};

const CreateReport  = (props) => {
    const { id } = useParams();
    const [reportUpdated, setReportUpdated] = props.reportUpdated;
    const [open, setModal] = useState(false);
    const [computers, setComputers] = useState([]);
    const [selectedComputer, setSelectedComputer] = useState({});
    const [issueDescription, setIssueDescription] = useState("");
    const [error, setError] = useState(false);
    const [user, setUser] = useState([]);
  

    useEffect(() => {
        getComputers(setComputers);
    }, [])

    useEffect(() => {
        getUser(id, setUser);
    },[])

    useEffect(() => {
        setSelectedComputer(computers[0]);
    }, [computers])


    const openModal = () => {
        setModal(true);
    };

    const closeModal = () => {
        setReportUpdated(false);
        setModal(false);
    };

    const submitReport = () => {
        console.log("user when submitting" + user)
        if (selectedComputer == "") {
            setError(true);
            return;
        }
        const currentDate = new Date();
        const report = {
            "computer": selectedComputer,
            "dateCreated": currentDate.toISOString().split("T")[0],
            "user": user,
            "description": issueDescription
        }
        
        const config = {
            method: "post",
            endpoint: "reports",
            data: report
        }
        callApi(() => {
            closeModal();
            setReportUpdated(true);
            props.getUpdatedReports();
        }, null, config);
    }

    return (
        <div>
           
        

            <Modal
                open={open}
                onClose={closeModal}
            >
                <Box sx={style}>
                    <h1>Add New Report</h1>
                    <Autocomplete 
                        required id="computer"
                        getOptionLabel={(selectedComputer) => `${selectedComputer.computerCode} - ${selectedComputer.room.name} ${selectedComputer.room.location.city}`}
                        options={computers}
                        isOptionEqualToValue={(option, value) => option.computerId === value.computerId}
                        noOptionsText={"No computers"}
                        renderInput={(params) => <TextField {...params} label={`Computer: `} />} //
                        onChange={(e, newValue) => {
                            if(newValue != null){
                                console.log(newValue)
                                setSelectedComputer(newValue)
                            }else{
                                setSelectedComputer(null)
                            }
                        }}
                    />
                    {error && <p style={{color: 'red'}}>Must be filled</p>}
                    <h3>
                    <TextField
						//error={Boolean(usernameError)}
						//helperText={usernameError ? usernameError : ""}
                        required id="description"
						name="description"
						label="Description"
						onChange={(e) => setIssueDescription(e.target.value)}
						sx={{ width: '100%'}}
					/>
                    </h3>
                    <Button
                        variant="contained"
                        onClick={submitReport}
                    >
                        Create Report
                    </Button>
                </Box>
            </Modal>
            <Button
                variant="contained"
                disableElevation
                sx={{
                    position: "fixed",
                    top: "23%",
                    right: "1.5%",
                    zIndex: 1000,
                }}
                onClick={openModal}
            >
                Create Report
            </Button>
        </div>
    )
}

export default CreateReport;