import React from 'react'
import ComputerCard from '../partials/ComputerCard'
import { useState } from 'react'
import { useEffect } from 'react'
import callApi from '../api/callApi'
import { Button, InputAdornment, OutlinedInput} from '@mui/material'
import SearchIcon from "@mui/icons-material/Search";
import AddComputer from '../partials/AddComputer'

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
    const [updated, setUpdated] = useState(true);

    useEffect(() => {
        getComputers(setComputers);
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
                            onSearchChange(setComputers, e.target.value)
                        }
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
            <AddComputer updated={[updated, setUpdated]}/>
        </>
    )
}
