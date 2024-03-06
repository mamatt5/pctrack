import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { IconButton } from '@mui/material';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import RemoveCircleIcon from '@mui/icons-material/RemoveCircle';
import {useEffect, useState} from 'react';
import EditIcon from '@mui/icons-material/Edit';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import EditReports from '../components/EditReports';
import callApi from "../api/callApi";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 700,
  bgcolor: 'background.paper',
  boxShadow: 24,
  padding: 6,
  borderRadius: 8,

};

// const updateReport = (reports, setReports, rowId) => {
//   const updatedReports = [...reports]; 
//   const indexToUpdate = updatedReports.findIndex(
//     (report) => report.reportId === rowId
//   );
//   if (indexToUpdate !== -1) {
//     const reportToUpdate = updatedReports[indexToUpdate];
//     reportToUpdate.resolved = !reportToUpdate.resolved; 
//     const config = {
//       method: 'put',
//       endpoint: 'reports',
//       data: reportToUpdate, 
//     };
//     callApi(() => getReports(setReports), null, config);
//   }
// };

// const updateReportResolved = (reports, setReport) => {
//   const config = {
//     method: 'put',
//     endpoint: 'reports',
//     data : {
//       resolved: true
//     }
//   };

//   callApi(()=> getReports(setReports), null, config)
// }

// useEffect(() => {
//   getReports(setReports); 
// }, []); 


// const getReports = (setReports) => {
//   const config = {
//     method: "get",
//     endpoint: "reports",
//   };
//   callApi(setReports, null, config);
// };


export default function ReportsTable({array, getReports, setReports}) {
  const [selectedRow, setSelectedRow] = useState(null)
  const navigate = useNavigate();
  const { id } = useParams();

  const openModal = (row) => {
    setSelectedRow(row);
  };

  const closeModal = () => {
    setSelectedRow(null);
    // getReports(setReports);
    // console.log("closing Model 123")
  };

  const goToReportsPage = () => {
    navigate("/reports")
  }

    //console.log(reports)
  return (
    <TableContainer component={Paper} sx={{maxWidth:"70vw"}}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="left">Computer</StyledTableCell>
            <StyledTableCell align="left">Reported By</StyledTableCell>
            <StyledTableCell align="left">Date Created</StyledTableCell>
            <StyledTableCell align="left">Description</StyledTableCell>
            <StyledTableCell align="left">Fixed Status</StyledTableCell>
            <StyledTableCell align="right">Action</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {array.map((row) => (
            <><StyledTableRow key={row.reportId}>
              <StyledTableCell align="left">{row.computer.computerCode} - {row.computer.room.name} {row.computer.room.location.city} </StyledTableCell>
              <StyledTableCell align="left">{row.user.username}</StyledTableCell>
              <StyledTableCell align="left">{row.dateCreated}</StyledTableCell>
              <StyledTableCell align="left">{row.description}</StyledTableCell>
              <StyledTableCell align="center">{row.resolved ? <CheckCircleIcon/>: 'Active'}</StyledTableCell>
              <StyledTableCell align="right" sx={{ width: '100px' }}>  

                <IconButton size="small" onClick={()=>openModal(row)}>
                  <EditIcon />
                </IconButton> 
              </StyledTableCell>
          </StyledTableRow>
          

        </>
        ))}
        <Modal
          open={!!selectedRow}
          onClose={closeModal}>
          <Box sx={style}>
            {selectedRow && <EditReports report={selectedRow} getReportsFunc={getReports} setReports={setReports}/>}

          </Box>
        </Modal>
        </TableBody>
      </Table>
    </TableContainer>
  );
}