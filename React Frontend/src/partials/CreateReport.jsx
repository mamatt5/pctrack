import { Autocomplete, Box, Button, Modal, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import callApi from "../api/callApi";

// Styling for creating reports modal
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

// Function to update a computers using the provided API call function
const getComputers = (setComputers) => {
    const config = {
        method: "get",
        endpoint: "computers"
    }

    callApi(setComputers, null, config);
}

// Function to get the current user object using the provided API call function
const getUser = (id, setUser) => {
    const config = {
        method: "get",
        endpoint: `users/${id}`,
    };
    callApi(setUser, null, config);
};

// Functional component for creating new reports
const CreateReport = (props) => {
    const { id } = useParams();
    const [reportUpdated, setReportUpdated] = props.reportUpdated;
    const [open, setModal] = useState(false);
    const [computers, setComputers] = useState([]);
    const [selectedComputer, setSelectedComputer] = useState({});
    const [issueDescription, setIssueDescription] = useState("");
    const [error, setError] = useState(false);
    const [user, setUser] = useState([]);

    // Fetch computers and user information on component render
    useEffect(() => {
        getComputers(setComputers);
    }, [])

    useEffect(() => {
        getUser(id, setUser);
    }, [])

    // Set default selected computer after computers are fetched
    useEffect(() => {
        setSelectedComputer(computers[0]);
    }, [computers])

    // Functions to open and close the Create Report modal
    const openModal = () => {
        setModal(true);
    };

    const closeModal = () => {
        setReportUpdated(false);
        setModal(false);
    };

    // Function to create and submit a new report
    const submitReport = () => {
        // Validate required fields
        if (selectedComputer == "") {
            setError(true);
            return;
        }
        // Construct report data
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
        // Make API call to create the report
        callApi(() => {
            closeModal();
            setReportUpdated(true);
            props.getUpdatedReports();
        }, null, config);
    }
    
    // Rending this componenet and UI elements
    // Modal for report creation that can open and close using a button, eror message if required fields are empty
    // Autocomplete for selecting a computer, input fields for descriptions and users, submit button to create the report
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
                            if (newValue != null) {
                                console.log(newValue)
                                setSelectedComputer(newValue)
                            } else {
                                setSelectedComputer(null)
                            }
                        }}
                    />
                    {error && <p style={{ color: 'red' }}>Must be filled</p>}
                    <h3>
                        <TextField
                            required id="description"
                            name="description"
                            label="Description"
                            onChange={(e) => setIssueDescription(e.target.value)}
                            sx={{ width: '100%' }}
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