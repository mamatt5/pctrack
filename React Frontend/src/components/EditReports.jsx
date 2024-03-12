import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from '@mui/material';
import Checkbox from '@mui/material/Checkbox';
import React, { useState } from 'react';
import callApi from '../api/callApi';

/**
 * Updates a report using the provided API call function.
 * 
 * @param {Object} updatedRpt - The updated report object with new description and resolved state.
 * @param {Function} callback - An optional callback function to be executed after successful update.
 */

const updateReport = (updatedRpt, callback) => {
  const config = {
    method: 'put',
    endpoint: 'reports',
    data: updatedRpt
  };

  callApi(() => {
    if (callback) {
      callback();
    }
  }, null, config);
};

/**
 * Reports component that displays and allows editing of a report.
 * 
 * @param {Object} report - The report object containing details.
 * @param {Function} getAllReports - Function to retrieve all reports (used for refresh).
 * @param {Function} setReportsFunc - Function to update the reports state.
 * @param {Function} closeModal - Function to close a modal component.
 * 
 * @returns {JSX.Element} The JSX element representing the Reports component.
 */
const Reports = ({ report, getAllReports, setReportsFunc, closeModal }) => {
  const [reports, setReports] = useState([])
  const [reportDescription, setReportDescription] = useState('')
  const [reportResolved, setReportResolved] = useState()
  const [editReportDialogue, setEditReportDialogue] = useState(false)

  /**
   * Handles editing a report, populating state with report details.
   * 
   * @param {Object} report - The report object to be edited.
   */
  const editReport = (report) => {
    setReports(report)
    setReportDescription(report.description)
    setReportResolved(report.resolved)
    setEditReportDialogue(true)
  }

  // Function to handle report update
  const handleUpdate = () => {
    const updatedReport = { ...report, description: reportDescription, resolved: reportResolved };
    // Update the report using the API call function
    updateReport(updatedReport, () => {
      getAllReports(setReportsFunc);
      setEditReportDialogue(false);
      closeModal();
    });
  };

  /**
   * Function to handle checkbox change for report resolved state
   * 
   * @param {*} e 
   */
  const handleChange = (e) => {
    setReportResolved(e.target.checked);
  };

  // Render the report details and edit dialog
  /**
   * 
   */
  return (
    <>
      <div>
        <ul style={{ listStyle: 'none' }}>
          <h2>Report: {report.computer.computerCode} - {report.computer.room.name} {report.computer.room.location.city}</h2>
          <li></li>
          <li key={report.reportId}> Description: {report.description} </li>
          <li>Report status: {report.resolved ? "Resolved" : "Active"}</li>
          <li><Button onClick={() => editReport(report)}>Edit</Button>
          </li>
        </ul>
      </div>

      <Dialog open={editReportDialogue} onClose={() => setEditReportDialogue(false)} fullWidth>
        <DialogTitle>Editing report</DialogTitle>
        <DialogContent>
          Description:
          <TextField
            value={reportDescription}
            onChange={(e) => setReportDescription(e.target.value)}
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
          <Button onClick={() => { setReportDescription(''); setEditReportDialogue(false) }}>Cancel</Button>
          <Button onClick={handleUpdate}>Save</Button>
        </DialogActions>
      </Dialog>
    </>
  )
}

export default Reports
