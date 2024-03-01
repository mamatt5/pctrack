import React from 'react'
import ComputerCard from '../partials/ComputerCard'
import { useState } from 'react'
import { useEffect } from 'react'
import callApi from '../api/callApi'
import { Button, InputAdornment, InputLabel, MenuItem, OutlinedInput, Select } from '@mui/material'
import SearchIcon from "@mui/icons-material/Search";
import AddComputer from '../partials/AddComputer'

const getComputers = (setComputers) => {
    const config = {
        method: "get",
        endpoint: "computers"
    }

    callApi(setComputers, null, config);
}

const getRooms = (setRooms) => {
    const config = {
        method: "get",
        endpoint: "rooms"
    }

    callApi(setRooms, null, config);
}

const onSearchChange = (setComputers, computerCode, roomId) => {
    const searchConfig = {
        "computerCode": computerCode,
        "roomId": roomId
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
    const [roomId, setRoomId] = useState("%%");

    useEffect(() => {
        getComputers(setComputers);
        getRooms(setRooms);
    }, []);

    useEffect(() => {
        getComputers(setComputers);
        setUpdated(true);
    }, [updated])

    return (
        <>
            <div className='dashBoardPadding'>
                <h1>Computers</h1>
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
                            onSearchChange(setComputers, e.target.value, roomId);
                        }
                    }}
                />
                <InputLabel id="search-by-room">Search By Room</InputLabel>
                <Select
                    labelId='search-by-room'
                    value={roomId}
                    label="Search By Room"
                    onChange={(e) => {
                        setRoomId(e.target.value);
                        onSearchChange(setComputers, search, e.target.value);
                    }}
                >
                    <MenuItem value="%%">None</MenuItem>
                    {
                        rooms.map(room =>
                            <MenuItem value={room.roomId} key={room.roomId}>{room.name}, {room.location.name}</MenuItem>
                        )
                    }
                </Select>
                <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                    {
                        computers.map(computer =>
                            <ComputerCard computer={computer} key={computer.computerId} />
                        )
                    }
                </div>
            </div>
            <AddComputer updated={[updated, setUpdated]} />
        </>
    )
}
