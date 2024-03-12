import { InputLabel, MenuItem, Select } from '@mui/material';
import Box from "@mui/material/Box";
import React, { useEffect, useState } from 'react';
import callApi from "../api/callApi";
import CreateReport from '../partials/CreateReport';
import ReportsTable from "../partials/ReportsTable";

// Functional component for managing and displaying reports
export const ReportsPage = () => {
  const [reports, setReports] = useState([]);
  const [filter, setFilter] = useState("date")
  const [reportUpdated, setReportUpdated] = useState(false);

  // Fetch initial reports after component renders
  useEffect(() => {
    getReports(setReports)
  }, []);

  // Fetch reports filtered by dates by default
  const getReports = (setReports) => {
    const config = {
      method: "get",
      endpoint: "reports/date",
    };
    callApi(setReports, null, config);
  };

  // Refetch reports and reset the 'reportUpdated' flag
  const getUpdatedReports = () => {
    getReports(setReports);
    setReportUpdated(false);
  };

  // Fetch reports based on the updated filter selection
  const onSearchChange = (setReports, filter) => {
    const config = {
      method: "get",
      endpoint: `reports/${filter}`,
    }

    callApi(setReports, null, config);
  }

  const ReportTab = () => {
    return <></>;
  };

  // Rending this componenet and UI elements
  // Display heading, filter selection of reports, ReportsTable to display reports, CreateReport component to create new reports
  return (

    <>
      <Box className="dashBoardPadding">
        <h1>Reports</h1>

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
        <ReportsTable array={reports} getReports={getReports} setReports={setReports} />
      </Box>
      <CreateReport reportUpdated={[reportUpdated, setReportUpdated]} getUpdatedReports={getUpdatedReports} />


    </>
  )
}
