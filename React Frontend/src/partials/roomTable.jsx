import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import ManageSearchIcon from '@mui/icons-material/ManageSearch';
import { IconButton } from '@mui/material';
import DeviceHubIcon from '@mui/icons-material/DeviceHub';
import { useState } from 'react';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

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


export default function CustomizedTables({array}) {
    console.log(array)


    const [mandateModal, setMandateModal] = useState(false)
    const [render, setRender] = useState(false)
    const navigate = useNavigate();


    const openModal = () => {
      setMandateModal(true);
    };

    const closeModal = () => {
      setMandateModal(false);
    };

    const t = () => {
      setRender(true)
    }

    const goToComputerPage = () => {
      navigate("/viewcomputerroom")
    }

  return (
    <TableContainer component={Paper}  sx={{maxWidth:"40vw"}}>
  <Table sx={{ minWidth: 700 }} aria-label="customized table">
    <TableHead>
      <TableRow>
        <StyledTableCell align="left">Location</StyledTableCell>
        <StyledTableCell align="left">Room Name</StyledTableCell>
        <StyledTableCell align="left"></StyledTableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {array.map((row) => (
        <><StyledTableRow key={row.name}>
          <StyledTableCell align="left">{row.location.name}</StyledTableCell>
          <StyledTableCell align="left">{row.name}</StyledTableCell>
          <StyledTableCell align="left" sx={{ width: '100px' }}>


            <IconButton size="small" onClick={() => {
              navigate("/viewcomputerroom", { state: row })
            }}>
              <ManageSearchIcon />
            </IconButton>

            <IconButton size="small" onClick={openModal}>
              <DeviceHubIcon />
            </IconButton>


          </StyledTableCell>
        </StyledTableRow>
        
        <Modal
          open={mandateModal}
          onClose={closeModal}
          onClick={t} 
          
        >
          <Box sx={style}>
            <h1>Create Mandate</h1>
            <p>Look at console for room information</p>
            <p>Background is not meant to go black when opening this menu but idk how to fix atm</p>
            {console.log(row)}

          </Box>
        </Modal>
        
        
        </>
        
      ))}
    </TableBody>
  </Table>
</TableContainer>
  );
}