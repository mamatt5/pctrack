import { ThemeProvider } from '@emotion/react';
import ComputerIcon from '@mui/icons-material/Computer';
import { Box, Button, CardActionArea, Modal, createTheme } from '@mui/material';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import * as React from 'react';
import { useState } from 'react';
import callApi from '../api/callApi';
import AddComputer from './AddComputer';
import ProgramTable from './programTable';

/**
 * Styling used for ComputerCard component
 */
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

/**
 * Determine user is allowed to edit or delete computer
 * @param {Object} computer - The current computer object for this ComputerCard component.
 * @param {Object[]} rooms - List of rooms that the user have admin privileges.
 * @returns {boolean} The verdict of granting computer editing authorization.
 */
const checkPerm = (computer, rooms) => {
    var boolean = false;
    if (rooms.filter(x => x.roomId == computer.room.roomId).length > 0) {
        boolean = true;
    }

    return boolean;
}

/**
 * Main Computer Card component, displaying computers in SearchComputerPage
 * @param {Object} props
 * @param {Object} props.computer - The computer object assigned to this ComputerCard component
 * @param {boolean} open - A variable indicate the model is open or not
 * @param {function} setModal - The function change 'open' variable
 * @param {[boolean, function]} props.updated - Indication of the SearchComputerPage requires a database update
 * @returns {ReactNode} A clickable component include brief info of the computer, with details of the computer upon clicking
 */
const ComputerCard = (props) => {
    const { computer, staff, rooms } = props;
    const [open, setModal] = useState(false);
    const [updated, setUpdated] = props.updated;

    /**
     * Set 'open' to true - open modal
     */
    const openModal = () => {
        setModal(true);
    };

    /**
     * Set 'open' to false - open modal
     */
    const closeModal = () => {
        setModal(false);
        setUpdated(false);
    };

    /**
     * Function after click 'Delete Computer'
     */
    const delComputer = () => {
        const config = {
            method: "delete",
            endpoint: "computers/" + computer.computerId
        }

        callApi(closeModal, null, config);
        props.onDelete();

    }

    /**
     * Getting the ComputerCard background colour based on its role
     * @param {Object} computer - The computer object assigned to this ComputerCard component
     * @returns {String} The correct colour code
     */
    const getColor = (computer) => {

        switch (computer.role) {
            case 'NONE':
                return '#FF6961'

            case 'BI':
            case 'DEV':
                return '#ffff66';

            case 'BOTH':
                return '#77DD77';

            default:
                return 'gray'
        }

    }

    return (
        <>

            <Card sx={{
                maxWidth: 130,

                borderRadius: 5,
                boxShadow: '8px 8px 25px rgba(0, 0, 0, 0.2)',
                transition: 'transform 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94)',
                width: 350,

                ':hover': {
                    transform: 'scale(1.05)',
                    boxShadow: 20,

                },

                backgroundColor: getColor(computer),
                margin: '5px',

            }}>
                <CardActionArea onClick={openModal}>
                    {/* <CardHeader title="Computer Details"/> */}
                    <CardContent>
                        {computer.role === 'DEV' && <div style={{ textAlign: 'center', fontWeight: 'bold' }}>DEV</div>}
                        {computer.role === 'BI' && <div style={{ textAlign: 'center', fontWeight: 'bold' }}>BI</div>}
                        {computer.role === 'BOTH' && <div style={{ textAlign: 'center', fontWeight: 'bold' }}>&nbsp;</div>}
                        {computer.role === 'NONE' && <div style={{ textAlign: 'center', fontWeight: 'bold' }}>&nbsp;</div>}
                        <ComputerIcon sx={{ fontSize: 100 }} />
                        <div style={{ textAlign: 'center' }}>{computer.computerCode}</div>

                    </CardContent>
                </CardActionArea>

                <Modal
                    open={open}
                    onClose={closeModal}


                >
                    <Box sx={{
                        ...style,
                        overflowY: 'scroll', // Enable vertical scrolling
                        maxHeight: '90vh',
                        padding: '20px',
                        '-ms-overflow-style': 'none',  // Hide scrollbar for Internet Explorer and Edge
                        'scrollbar-width': 'none', // Hide scrollbar for Firefox
                        '&::-webkit-scrollbar': {
                            display: 'none', // Hide scrollbar for WebKit browsers (Chrome, Safari)
                        },
                        textAlign: 'center'
                    }}>
                        <h1>Computer Details</h1>
                        <h2>{computer.computerCode}</h2>
                        <h3>Room: {computer.room.name}, {computer.room.location.name}</h3>
                        {computer.role !== 'NONE' && <h3>Role: {computer.role}</h3>}
                        {computer.programList?.length > 0 && <ProgramTable array={computer.programList} />}
                        <br></br>
                        {checkPerm(computer, rooms) &&
                            <>
                                <AddComputer updated={[updated, setUpdated]} computer={computer} staff={staff} rooms={rooms} onUpdate={props.onUpdate} />
                                <Button
                                    variant="contained"
                                    color="error"
                                    sx={{ margin: "50px" }}
                                    onClick={delComputer}
                                >Delete Computer</Button>
                            </>
                        }
                    </Box>
                </Modal>
            </Card>
        </>
    );
}

export default ComputerCard