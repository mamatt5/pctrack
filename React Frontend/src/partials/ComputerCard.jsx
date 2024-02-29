import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import ComputerTwoToneIcon from '@mui/icons-material/ComputerTwoTone';
import ComputerIcon from '@mui/icons-material/Computer';
import { useState } from 'react';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import { Style } from '@mui/icons-material';
import { CardHeader } from '@mui/material';

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

const ComputerCard = () => {
  const [open, setModal] = useState(false)
  const [render, setRender] = useState(false)



  const openModal = () => {
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };

  const t = () => {
    setRender(true)
  }



    return (
        <Card sx= {{ 
          maxWidth: 130, 
          
          borderRadius: 5, 
          boxShadow: '8px 8px 25px rgba(0, 0, 0, 0.2)',
          transition: 'transform 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94)',
          width: 350, 
        
          ':hover': {
          transform: 'scale(1.05)',
          boxShadow: 20, 
          
        }, }}>
          
          <CardActionArea onClick={openModal}>
            {/* <CardHeader title="Computer Details"/> */}
            <CardContent>
             
              <ComputerTwoToneIcon sx = {{fontSize: 100}}/>
              
            </CardContent>
          </CardActionArea>

          <Modal
                open={open}
                onClose={closeModal}
                onClick={t} 
              >
                <Box sx={style}>
                <Typography>
                  <h1>Computer Details</h1>
                  </Typography>
                </Box>
              </Modal>
        </Card>
      );
}

export default ComputerCard