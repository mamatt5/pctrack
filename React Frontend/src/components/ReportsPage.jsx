import React from 'react'
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import NavBar from "../partials/NavBar";
import { useState } from "react";
import ReportsTable from "../partials/ReportsTable";
import { InputLabel, MenuItem, Select } from '@mui/material'
import { useEffect } from "react";
import callApi from "../api/callApi";
import CreateReport from '../partials/CreateReport'
import { Snackbar } from '@mui/material'
import { Alert } from '@mui/material'

export const ReportsPage = () => {
    const [reports, setReports] = useState([]);
    const [report, setReport] = useState([])
    const [filter, setFilter] = useState("date")
    const [reportUpdated, setReportUpdated] = useState(true);
    const [open, setOpen] = useState(false);

    useEffect (() => {
        getReports(setReports)
    }, []);

    const getReports = (setReports) => {
      const config = {
        method: "get",
        endpoint: "reports/date",
      };
      callApi(setReports, null, config);
    };

    useEffect(() => {
      getReports(setReport);
      setReportUpdated(true);
    }, [reportUpdated])


    const handleClose = () => {
      setOpen(false);
    };

    const onSearchChange = (setReports, filter) => {
      const config = {
          method: "get",
          endpoint: `reports/${filter}`,
      }
  
      callApi(setReports, null, config);
  }

  const refresh = (setReports, filter) => {
    const config = {
        method: "get",
        endpoint: `reports/${filter}`,
    }

    callApi(setReports, null, config);
    setOpen(true)
}

    const ReportTab = () => {
      return <></>;
    };

    return (

      <>
        <Box className="dashBoardPadding">
          <h1>Reports</h1>

          <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
          <Alert
              onClose={handleClose}
              severity="success"
              variant="filled"
              sx={{ width: '100%' }}
          >
              Reports have been refreshed!
          </Alert>
          </Snackbar>

          <Button variant="contained" sx={{margin: '0px 0px 15px 0px'}}onClick={()=>{refresh(setReports,filter)}}>Refresh</Button>
            <div style={{ display: 'flex', gap: '10px', alignItems: 'center', justifyContent: 'center', marginBottom: '20px' }}> 
                    <InputLabel id="Filter By">Filter By</InputLabel>
                    <Select
                        labelId='Filter By'
                        value={filter}

                        onChange={(e) => {
                            setFilter(e.target.value);
                            onSearchChange(setReports, e.target.value);
                        }}
                    >
                        <MenuItem value="date">Date</MenuItem>
                        <MenuItem value="computerCode">Computer Code</MenuItem>
                        <MenuItem value="resolved">Report Status</MenuItem>
                    </Select>
                    </div>
          <Box sx={{ display: "flex", flexDirection: "row" }}>
            
                    <ReportTab />
                  </Box>
                  <ReportsTable array={reports} getReports={getReports} setReports={setReports}/>
        </Box>
        <CreateReport reportUpdated={[reportUpdated, setReportUpdated]} />

      </>
    )
}
//export default ReportsPage;
