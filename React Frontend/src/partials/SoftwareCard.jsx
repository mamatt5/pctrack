import { Box, Modal, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, styled, tableCellClasses } from "@mui/material";
import { useState } from "react";
import callApi from "../api/callApi";
import { useEffect } from "react";

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
    overflowY: 'scroll', // Enable vertical scrolling
    maxHeight: '90vh',
    padding: '20px',
    msOverflowStyle: 'none',  // Hide scrollbar for Internet Explorer and Edge
    scrollbarWidth: 'none', // Hide scrollbar for Firefox
    '&::-webkit-scrollbar': {
        display: 'none', // Hide scrollbar for WebKit browsers (Chrome, Safari)
    },
    textAlign: 'center'
};

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
        backgroundColor: theme.palette.action.hover
    },
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));


const SoftwareCard = (props) => {
    const { software } = props;
    const [open, setOpen] = useState(false);
    const [computerList, setComputerList] = useState({});

    useEffect(() => {
        var newComputerList = computerList;
        software.versions.map((row) => {
            displayComputers(row, newComputerList);
        });
        setComputerList(newComputerList);
    }, []);

    const openModal = () => {
        setOpen(true);
    }

    const closeModal = () => {
        setOpen(false);
    }

    const displayComputers = (version, computerList) => {
        const config = {
            method: "get",
            endpoint: `computers/programs/${version.programId}`
        }
        callApi((e) => {
            computerList[version.programId] = e;
        }, null, config);
    }

    return (
        <>
            <Modal
                open={open}
                onClose={closeModal}
            >
                <Box sx={style}>
                    <h1>{software.name}</h1>
                    <TableContainer component={Paper} sx={{ maxWidth: "70vw" }}>
                        <Table sx={{ minWidth: 600 }} aria-label="customized table">
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell align="left">Version Number</StyledTableCell>
                                    <StyledTableCell align="left">Computers</StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {software.versions.map((row) => (
                                    <StyledTableRow key={row.programId}>
                                        <StyledTableCell align="left">{row.version}</StyledTableCell>
                                        <StyledTableCell align="left">
                                            {
                                                open && computerList[row.programId].map((computer) => (
                                                    <span key={computer.computerId}>{computer.computerCode} </span>
                                                ))
                                            }
                                        </StyledTableCell>
                                    </StyledTableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Modal>
            <StyledTableRow hover onClick={openModal} sx={{
                '&.MuiTableRow-root:hover': {
                    backgroundColor: 'rgba(0, 0, 0, 0.15)'
                }
            }}>
                <StyledTableCell align="left">{software.name}</StyledTableCell>
                <StyledTableCell align="left">
                    {software.versions.length > 0 ? software.versions[0].version : "No version available"}
                </StyledTableCell>
            </StyledTableRow>
        </>
    )
}

export default SoftwareCard;