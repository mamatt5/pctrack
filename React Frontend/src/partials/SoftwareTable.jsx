import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { styled } from '@mui/material/styles';
import * as React from 'react';

/**
 * Custom styled component for table rows and cells  
 * Applies alternating row colors for better readability.
 */ 
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

/**
 * Renders the software table with data from the 'array' prop passed from SearchSoftwarePage
 */
export default function SoftwareTables({array}) {
    console.log(array)
  return (
    <TableContainer component={Paper} sx={{maxWidth:"70vw"}}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>

            <StyledTableCell align="left">Name</StyledTableCell>
            <StyledTableCell align="left">Version Number</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {array.map((row) => (
            <StyledTableRow key={row.programId}>
              <StyledTableCell align="left">{row.software.name}</StyledTableCell>
              <StyledTableCell align="left">{row.version}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}