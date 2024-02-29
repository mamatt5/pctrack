import React from 'react'
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import NavBar from "../partials/NavBar";
import { useState } from "react";
import SoftwareTables from "../partials/SoftwareTable";
import { useEffect } from "react";
import callApi from "../api/callApi";

const SearchSoftwarePage = () => {
    const [software, setSoftware] = useState([]);

    useEffect (() => {
        getSoftware(setSoftware)
    }, []);

    const getSoftware = (setSoftware) => {
      // Functionality to get users
      const config = {
        method: "get",
        endpoint: "programs",
      };
    
      // if we cant find the user, its a username issue
      // if we can, its a password issue.
      callApi(setSoftware, null, config);
    };
    
    
    const SoftwareTab = () => {
      return <></>;
    };

    return (

      <>
        {/* <div className='dashBoardPadding'>
          <h1>Under Construction</h1>
          <div>SearchSoftwarePage</div>
        </div> */}
        <Box className="dashBoardPadding">
          <Box sx={{ display: "flex", flexDirection: "row" }}>
                    <SoftwareTab />
                  </Box>
                  <SoftwareTables array={software} />
        </Box>

      </>
    )
}
export default SearchSoftwarePage;
