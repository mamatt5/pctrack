import ComputerIcon from '@mui/icons-material/Computer'
import SearchIcon from "@mui/icons-material/Search"
import { Alert, Box, Fade, InputAdornment, InputLabel, MenuItem, OutlinedInput, Select, Snackbar } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import callApi from '../api/callApi'
import AddComputer from '../partials/AddComputer'
import ComputerCard from '../partials/ComputerCard'
import CreateReport from '../partials/CreateReport'


const getComputers = (setComputers) => {
    const config = {
        method: "get",
        endpoint: "computers"
    }

    callApi(setComputers, null, config);
}

const getRegRooms = (setRegRooms, id) => {
    const config = {
        method: "get",
        endpoint: `staff/getrooms/${id}`
    }

    callApi(setRegRooms, null, config);
}

const getRooms = (setRooms) => {
    const config = {
        method: "get",
        endpoint: "rooms"
    }

    callApi(setRooms, null, config);
}

const getReport = (setReport) => {
    const config = {
        method: "get",
        endpoint: "reports/date"
    }

    callApi(setReport, null, config);
}

const onSearchChange = (setComputers, computerCode, roomId, role) => {
    //const onSearchChange = (setComputers, computerCode, roomId, role) => {
    const searchConfig = {
        "computerCode": computerCode,
        "roomId": roomId,
        "role": role
    }

    const config = {
        method: "post",
        endpoint: "computers/search",
        data: searchConfig
    }

    callApi(setComputers, null, config);
}

export const SearchComputerPage = () => {
    const [computers, setComputers] = useState([]);
    const [rooms, setRooms] = useState([]);
    const [search, setSearch] = useState("");
    const [updated, setUpdated] = useState(true);
    const [report, setReport] = useState([])
    const [reportUpdated, setReportUpdated] = useState(true);
    const [roomId, setRoomId] = useState("%%");
    const [role, setRole] = useState("%%");
    const [regRooms, setRegRooms] = useState([]);
    const { id } = useParams();
    const [isHovered, setIsHovered] = useState(false)

    const [updatedComputer, onUpdatedComputer] = useState(false);
    const [deleteComputer, onDeletedComputer] = useState(false);
    const [openAlert, setOpen] = useState(false);

    useEffect(() => {
        getComputers(setComputers);
        getRooms(setRooms);
        getRegRooms(setRegRooms, id);
    }, []);

    useEffect(() => {
        getComputers(setComputers);
        setUpdated(true);
    }, [updated == false]);

    useEffect(() => {
        getReport(setReport);
        setReportUpdated(true);
    }, [reportUpdated])

    return (
        <>
            <Snackbar open={openAlert} autoHideDuration={6000} onClose={()=>setOpen(false)} 
           
           >
                    <Alert
                        onClose={()=>setOpen(false)}
                        severity="success"
                        variant="filled"
                        sx={{ width: '100%' }}
                    >
                        A new Report has been created!
                    </Alert>
            </Snackbar>


            <Snackbar
                    open={updatedComputer}
                    autoHideDuration={6000}
                    onClose={()=>onUpdatedComputer(false)}
            >
                    <Alert
                        onClose={()=>onUpdatedComputer(false)}
                        severity="success"
                        variant="filled"
                        sx={{ width: '100%' }}
                    >
                        Computer has been Updated!
                    </Alert>
            </Snackbar>

            <Snackbar
                    open={deleteComputer}
                    autoHideDuration={6000}
                    onClose={()=>onDeletedComputer(false)}
            >
                    <Alert
                        onClose={()=>onDeletedComputer(false)}
                        severity="success"
                        variant="filled"
                        sx={{ width: '100%' }}
                    >
                        Computer has been Deleted!
                    </Alert>
            </Snackbar>

            <div className='dashBoardPadding'>
                <h1>Computers</h1>
                <div style={{ display: 'flex', gap: '10px', alignItems: 'center', justifyContent: 'center', marginBottom: '20px' }}>
                    <OutlinedInput
                        id="search"
                        size="small"
                        placeholder={'Search By Code'}
                        value={search}
                        startAdornment={
                            <InputAdornment position="start">
                                <SearchIcon />
                            </InputAdornment>
                        }
                        sx={{ borderRadius: 5 }}
                        onChange={(e) => {
                            if (/^\d+$/.test(e.target.value) || e.target.value == "") {
                                setSearch(e.target.value);
                                onSearchChange(setComputers, e.target.value, roomId, role);
                            }
                        }}
                    />

                    <InputLabel id="search-by-room">Filter By Room</InputLabel>
                    <Select
                        labelId='search-by-room'
                        value={roomId}

                        onChange={(e) => {
                            setRoomId(e.target.value);
                            onSearchChange(setComputers, search, e.target.value, role);
                        }}
                    >
                        <MenuItem value="%%">None</MenuItem>
                        {
                            rooms.map(room =>
                                <MenuItem value={room.roomId} key={room.roomId}>{room.name}, {room.location.name}</MenuItem>
                            )
                        }
                    </Select>
                    <InputLabel id="search-by-roles">Filter By Roles</InputLabel>
                    <Select
                        labelId='search-by-roles'
                        value={role}

                        onChange={(e) => {
                            setRole(e.target.value);
                            onSearchChange(setComputers, search, roomId, e.target.value);
                        }}
                    >
                        <MenuItem value="%%">Unfiltered</MenuItem>
                        <MenuItem value="NONE">None</MenuItem>
                        <MenuItem value="DEV">Dev</MenuItem>
                        <MenuItem value="BI">BI</MenuItem>
                        <MenuItem value="BOTH">Both</MenuItem>
                    </Select>
                </div>
                <Fade in={true}>
                    <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                        {
                            computers.map(computer =>
                                <ComputerCard computer={computer} key={computer.computerId} updated={[updated, setUpdated]} 
                                rooms={regRooms}
                                onUpdate={()=>onUpdatedComputer(true)}
                                onDelete={()=>onDeletedComputer(true)}
                                />
                           
                            )
                        }
                    </div>
                </Fade>
            </div>
            <AddComputer updated={[updated, setUpdated]} computer={null} rooms={regRooms}/>
            <Box sx={{
                position: 'fixed',
                top: 80,
                right: 20,
                backgroundColor: 'lightgray',
                padding: '10px',
                borderRadius: '10px'
            }}>
                <CreateReport  reportUpdated={[reportUpdated, setReportUpdated]} created={()=>setOpen(true)} />
                <Box
                    sx={{
                        position: 'fixed',
                        top: 80,
                        right: 20,
                        backgroundColor: 'lightgray',
                        padding: '10px',
                        borderRadius: '10px',
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'left'
                    }}
                    onMouseEnter={() => setIsHovered(true)}
                    onMouseLeave={() => setIsHovered(false)}>

                    {isHovered && (
                        <>
                            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '5px' }}>
                                <ComputerIcon style={{ color: '#77DD77' }} /> Both roles
                            </div>
                            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '5px' }}>
                                <ComputerIcon style={{ color: '#ffff66' }} /> Dev/BI-ready
                            </div>
                            <div style={{ display: 'flex', alignItems: 'center' }}>
                                <ComputerIcon style={{ color: '#FF6961' }} /> None
                            </div>
                            <div style={{ fontSize: '11px' }}><em>
                                *Check Help for Dev/BI software list
                            </em></div>
                        </>
                    )}
                    {!isHovered && (
                        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                            <ComputerIcon style={{ color: '#77DD77' }} />
                            <ComputerIcon style={{ color: '#ffff66' }} />
                            <ComputerIcon style={{ color: '#FF6961' }} />
                        </div>
                    )}
                </Box>
            </Box>
        </>
    )
}
