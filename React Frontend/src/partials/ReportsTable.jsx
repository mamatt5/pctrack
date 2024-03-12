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
import CancelIcon from '@mui/icons-material/Cancel';
import { useState } from 'react';
import EditIcon from '@mui/icons-material/Edit';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import EditReports from '../components/EditReports';

// Custom styled components for table rows and cells
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

// Functional component for displaying a table of reports with edit functionality
export default function ReportsTable({ array, getReports, setReports }) {
  const [selectedRow, setSelectedRow] = useState(null)

  const openModal = (row) => {
    setSelectedRow(row);
  };

  const closeModal = () => {
    setSelectedRow(null);
  };


  return (
    <TableContainer component={Paper} sx={{ maxWidth: "70vw" }}>
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
              <StyledTableCell align="center">{row.resolved ? <CheckCircleIcon color='success' /> : <CancelIcon color='error' />}</StyledTableCell>
              <StyledTableCell align="right" sx={{ width: '100px' }}>
                <IconButton size="small" onClick={() => openModal(row)}>
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
              {selectedRow && <EditReports report={selectedRow} getAllReports={getReports} setReportsFunc={setReports} closeModal={closeModal} />}
            </Box>
          </Modal>
        </TableBody>
      </Table>
    </TableContainer>
  );
}