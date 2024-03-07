import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Button, CardActionArea, createTheme } from '@mui/material';
import ComputerTwoToneIcon from '@mui/icons-material/ComputerTwoTone';
import { useState } from 'react';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import ProgramTable from './programTable';
import ComputerIcon from '@mui/icons-material/Computer';
import callApi from '../api/callApi';
import { ThemeProvider } from '@emotion/react';
import AddComputer from './AddComputer';

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
const theme = createTheme({
    palette: {
        yellow: {
            main: '#E3D026',
            light: '#E9DB5D',
            dark: '#A29415',
            contrastText: '#242105',
        },
    },
});

const ComputerCard = (props) => {
    const { computer } = props;
    const [open, setModal] = useState(false);
    const [render, setRender] = useState(false);
    const [updated, setUpdated] = useState(true);
    const [addComputer, setAddComputer] = useState(false);

    const openModal = () => {
        setModal(true);
    };

    const closeModal = () => {
        setModal(false);
        setUpdated(false);
    };

    const t = () => {
        setRender(true)
    }

    const delComputer = () => {
        const config = {
            method: "delete",
            endpoint: "computers/" + computer.computerId
        }

        callApi(closeModal, null, config);
    }

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
                onClick={t}


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
                    <ThemeProvider theme={theme}>
                        <AddComputer updated={[updated,setUpdated]} computer={computer} />
                        <Button
                            variant="contained"
                            color="error"
                            sx={{margin: "50px"}}
                            onClick={delComputer}
                        >Delete Computer</Button>
                    </ThemeProvider>
                </Box>
            </Modal>
        </Card>
    );
}

export default ComputerCard