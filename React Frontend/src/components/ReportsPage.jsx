import { InputLabel, MenuItem, Select } from '@mui/material';
import Box from "@mui/material/Box";
import React, { useEffect, useState } from 'react';
import callApi from "../api/callApi";
import CreateReport from '../partials/CreateReport';
import ReportsTable from "../partials/ReportsTable";


/**
 * @returns - Displays this ReportsPage
 */
export const ReportsPage = () => {
  const [reports, setReports] = useState([]);
  const [filter, setFilter] = useState("date")
  const [reportUpdated, setReportUpdated] = useState(false);

  /**
   * Fetches initial reports after component renders.
   */
  useEffect(() => {
    getReports(setReports)
  }, []);


  /**
   * Fetch reports, filtered by dates by default
   * @param {*} setReports - Function to update the reports state.
   */
  const getReports = (setReports) => {
    const config = {
      method: "get",
      endpoint: "reports/date",
    };
    callApi(setReports, null, config);
  };

  /**
   * Refetches reports and resets the 'reportUpdated' flag.
   * Used after a report is created or updated.
   */
  const getUpdatedReports = () => {
    getReports(setReports);
    setReportUpdated(false);
  };

  /**
   * Handles filter change, fetching reports based on the new filter.
   * @param {*} setReports - Function to update the reports state.
   * @param {*} filter - The new filter (date, computerCode, or resolved).
   */
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

  /**
   * Rending this componenet and UI elements
   * Display heading, filter selection of reports, ReportsTable to display reports, CreateReport component to create new reports
   */
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
