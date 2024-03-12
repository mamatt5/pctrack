import React from 'react'
import Box from "@mui/material/Box";
import { useState } from "react";
import SoftwareTable from "../partials/SoftwareTable";
import { useEffect } from "react";
import callApi from "../api/callApi";

// Functional component for searching and displaying software information
export const SearchSoftwarePage = () => {
  const [software, setSoftware] = useState([]);

  // Fetch software data on component render
  useEffect(() => {
    getSoftware(setSoftware)
  }, []);

  // Fetch software data from the API
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

  // Rending this componenet and UI elements
  // Display heading, SoftwareTable to display fetched software data
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
