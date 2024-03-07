import React from 'react'
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import NavBar from "../partials/NavBar";
import { useState } from "react";
import SoftwareTable from "../partials/SoftwareTable";
import { useEffect } from "react";
import callApi from "../api/callApi";

export const SearchSoftwarePage = () => {
    const [software, setSoftware] = useState([]);

    useEffect (() => {
        getSoftware(setSoftware)
    }, []);

    const getSoftware = (setSoftware) => {
      const config = {
        method: "get",
        endpoint: "programs",
      };
      callApi(setSoftware, null, config);
    };


    const SoftwareTab = () => {
      return <></>;
    };

    return (

      <>
        <Box className="dashBoardPadding">
          <h1>Software</h1>
          <Box sx={{ display: "flex", flexDirection: "row" }}>
                    <SoftwareTab />
                  </Box>
                  <SoftwareTable array={software} />
        </Box>

      </>
    )
}
//export default SearchSoftwarePage;
