import React from 'react'
import NavBar from '../partials/NavBar'
import ComputerCard from '../partials/ComputerCard'
import { useState } from 'react'
import { useEffect } from 'react'
import callApi from '../api/callApi'
import { TextField } from '@mui/material'

const getComputers = (setComputers) => {
    const config = {
        method: "get",
        endpoint: "computers"
    }

    callApi(setComputers, null, config);
}

const onSearchChange = (setComputers, value) => {
    if (value == "") {
        getComputers(setComputers);
    } else {
        const config = {
            method: "get",
            endpoint: "computers/search/" + value
        }

        callApi(setComputers, null, config);
    }
}

export const SearchComputerPage = () => {
    const [computers, setComputers] = useState([]);
    const [search, setSearch] = useState("");

    useEffect(() => {
        getComputers(setComputers);
    }, []);

    return (
        <>
            <div className='dashBoardPadding'>
                <h1>Computers</h1>
                <TextField 
                    label = "Search By Code"
                    value = {search}
                    onChange={(e) => {
                        if (/^\d+$/.test(e.target.value) || e.target.value == "") {
                            setSearch(e.target.value);
                            onSearchChange(setComputers, e.target.value)
                        }
                        console.log(e.target.value)
                        
                    }}
                />
                <div style={{display: 'flex', flexWrap: 'wrap'}}>
                    {
                        computers.map(computer =>
                            <ComputerCard computer = {computer} key = {computer.computerId}/>    
                        )
                    }
                </div>
            </div>
        </>
    )
}
