import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { CardActionArea } from '@mui/material';
import ComputerTwoToneIcon from '@mui/icons-material/ComputerTwoTone';
import { useState } from 'react';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import ProgramTable from './programTable';

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

const ComputerCard = (props) => {
    const { computer } = props;
    const [open, setModal] = useState(false);
    const [render, setRender] = useState(false);

    const openModal = () => {
        setModal(true);
    };

    const closeModal = () => {
        setModal(false);
    };

    const t = () => {
        setRender(true)
    }

    const getColor = (computer) => {

        switch(computer.role) {
            case 'NONE':
                return 'red'

            case 'BI':
            case 'DEV':
                return 'yellow';
            
            case 'BOTH':
                return 'green';

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
            margin: '5px'
        }}>
            <CardActionArea onClick={openModal}>
                {/* <CardHeader title="Computer Details"/> */}
                <CardContent>
                    {computer.role === 'DEV' && <div style={{ textAlign: 'center' }}>DEV</div>}
                    {computer.role === 'BI' && <div style={{ textAlign: 'center' }}>BI</div>}
                    <ComputerTwoToneIcon sx={{ fontSize: 100 }} />
                    <div style={{ textAlign: 'center' }}>{computer.computerCode}</div>

                </CardContent>
            </CardActionArea>

            <Modal
                open={open}
                onClose={closeModal}
                onClick={t}
            >
                <Box sx={style}>
                    <h1>Computer Details</h1>
                    <h2>{computer.computerCode}</h2>
                    <h3>Room: {computer.room.name}, {computer.room.location.name}</h3>
                    {computer.role !== 'NONE' && <h3>Role: {computer.role}</h3>}
                    {computer.programList?.length > 0 && <ProgramTable array = {computer.programList} />}
                </Box>
            </Modal>
        </Card>
    );
}

export default ComputerCard