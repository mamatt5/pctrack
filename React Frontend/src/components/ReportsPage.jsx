import React from 'react'
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import NavBar from "../partials/NavBar";
import { useState } from "react";
import ReportsTable from "../partials/ReportsTable";
import { useEffect } from "react";
import callApi from "../api/callApi";

export const ReportsPage = () => {
    const [reports, setReports] = useState([]);

    useEffect (() => {
        getReports(setReports)
    }, []);

    const getReports = (setReports) => {
      const config = {
        method: "get",
        endpoint: "reports",
      };
      callApi(setReports, null, config);
    };


    const ReportTab = () => {
      return <></>;
    };

    return (

      <>
        <Box className="dashBoardPadding">
          <h1>Reports</h1>
          <Button variant="outlined" sx={{margin: '0px 0px 15px 0px'}}onClick={()=>{getReports(setReports)}}>Refresh</Button>
          <Box sx={{ display: "flex", flexDirection: "row" }}>
                    <ReportTab />
                  </Box>
                  <ReportsTable array={reports} getReports={getReports} setReports={setReports}/>
        </Box>

      </>
    )
}
//export default ReportsPage;
