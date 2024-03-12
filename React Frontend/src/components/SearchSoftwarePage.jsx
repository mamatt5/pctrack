import Box from "@mui/material/Box";
import React, { useEffect, useState } from 'react';
import callApi from "../api/callApi";
import SoftwareTable from "../partials/SoftwareTable";


/**
 * Functional component for searching and displaying software information.
 * Allows users to view a list of all available software programs.
 * @returns - Displays this SearchSoftwarePage
 */
export const SearchSoftwarePage = () => {
  const [software, setSoftware] = useState([]);

  /**
   * Fetches software data from the API on component mount.
   */
  useEffect(() => {
    getSoftware(setSoftware)
  }, []);

  /**
   * Fetches software data using a GET request to the programs endpoint.
   * Updates the software state with the retrieved data.
   * @param {*} setSoftware - Function to update the software state.
   */
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

  /**
   * Rending this componenet and UI elements
   * Display heading, SoftwareTable to display fetched software data
   */
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
