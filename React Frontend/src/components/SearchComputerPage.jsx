import React from 'react'
import NavBar from '../partials/NavBar'
import ComputerCard from '../partials/ComputerCard'
import { useState } from 'react'
import { useEffect } from 'react'
import callApi from '../api/callApi'

const getComputers = (setComputers) => {
    const config = {
        method: "get",
        endpoint: "computers"
    }

    callApi(setComputers, null, config);
}

export const SearchComputerPage = () => {
    const [computers, setComputers] = useState([]);

    useEffect(() => {
        getComputers(setComputers);
    }, []);

    return (
        <>
            <div className='dashBoardPadding'>
                <h1>Under Construction</h1>
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
