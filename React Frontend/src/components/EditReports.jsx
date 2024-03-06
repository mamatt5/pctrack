import { Button, Dialog, DialogTitle, DialogContent, DialogContentText, DialogActions, TextField } from '@mui/material'
import React, { useEffect, useState } from 'react'
import callApi from '../api/callApi'
import { useParams } from 'react-router-dom';
import Checkbox from '@mui/material/Checkbox';
import { useNavigate } from "react-router-dom";

// const getReports = (reportId, setReports) => {
//   const config = {
//     method: 'get',
//     endpoint: `reports/${reportId}`
//   };
//   callApi(setReports, null, config)
// }

// const createReport = (computer, description, setReports) => {
//   const newReport = { 
//     computer: computer,
//     description: description }

//   const config = {
//     method: 'post',
//     endpoint: 'reports',
//     data: newReport
//   };
//   callApi(()=> getReports(setReports), null, config)
// }

const updateReport = (updatedRpt, setReports, navToReportsPage) => {
  const config = {
    method: 'put',
    endpoint: 'reports',
    data : updatedRpt
  };

  callApi(()=> getReports(setReports, navToReportsPage), navToReportsPage, config)
}

const Reports = ({report}) => {
  const navigate = useNavigate();
  const [reports, setReports] = useState([])
  const [reportDescription, setReportDescription] = useState('')
  const [reportResolved, setReportResolved] = useState()
  const [editReportDialogue, setEditReportDialogue] = useState(false)
  const reportId = report.reportId
  const { id } = useParams()

  // useEffect(() => {
  //   getReports(reportId, setReports);
  // }, [report])

  const editReport = (report) => {
    setReports(report)
    setReportDescription(report.description)
    setReportResolved(report.resolved)
    setEditReportDialogue(true)
  }

  const navToReportsPage = (id) => {
    navigate(`/home/${id}/reports`)
  }

  const handleUpdate = () => {
    const updatedReport = { ...report, description: reportDescription, resolved: reportResolved}
    updateReport(updatedReport, setReports, navToReportsPage);
    setEditReportDialogue(false)
    //navigate(`/home/${id}/reports`)
    //setReportDescription('')
    //setReports(null)
  }

  const handleChange = (e) => {
    setReportResolved(e.target.checked);
  };

  return (
    <>
      <div>
        <ul style={{listStyle: 'none'}}>
          <h2>Report: {report.computer.computerCode} - {report.computer.room.name} {report.computer.room.location.city}</h2>
                <li></li>
                <li key={report.reportId}> Description: {report.description} </li>
                <li>Report status: {report.resolved ? "Resolved" : "Active"}</li>
                <li><Button onClick={()=> editReport(report)}>Edit</Button>
                </li>
        </ul>
      </div>

      <Dialog open={editReportDialogue} onClose={()=>setEditReportDialogue(false)} fullWidth>

        <DialogTitle>Editing report</DialogTitle>
        <DialogContent>
          Description: 
          <TextField
            value={reportDescription}
            onChange={(e)=> setReportDescription(e.target.value)}
            multiline
            fullWidth
            />
            Issue Resolved:
            <Checkbox
              checked={reportResolved}
              label="Resolved"
              onChange={handleChange}
              inputProps={{ 'aria-label': 'controlled' }}
            />
        </DialogContent>
        <DialogActions>
          <Button onClick={()=>{setReportDescription('');setEditReportDialogue(false)}}>Cancel</Button>
          <Button onClick={handleUpdate}>Save</Button>
        </DialogActions>

      </Dialog>
    </>
  )
}

export default Reports
