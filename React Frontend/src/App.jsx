import { useState } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Import BrowserRouter as Router
import { ThemeProvider } from "@mui/material";
import { createTheme } from '@mui/material/styles'
//pages

import Login from "./components/Login";
import Admin from "./components/Admin";
import LoggedInHomePage from "./components/LoggedInHomePage";
import { SearchComputerPage } from "./components/SearchComputerPage";
import { SearchRoomPage } from "./components/SearchRoomPage";
import { SearchSoftwarePage } from "./components/SearchSoftwarePage";
import NavBar from "./partials/NavBar";
import ViewComputersInRoomPage from "./components/ViewComputersInRoomPage";
import { ReportsPage } from "./components/ReportsPage";
import { useIdleTimer } from 'react-idle-timer'
import { useEffect } from "react";
import { Modal } from "@mui/material";
import { Box } from "@mui/material";
import { Style } from "@mui/icons-material";
import { Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useLocation } from 'react-router-dom';

const timeout = 10000_000
const promptBeforeIdle = 4_000


function App() {

	const defaultTheme = createTheme({
		palette: {
		  background: {
			default : "#edf1f9 ",
		  } 
		},
	  });
	  

	  const [remaining, setRemaining] = useState(timeout)
	  const [openModal, setOpen] = useState(false)
	  const [logout, setLogOut] = useState(false)
	  let location = useLocation();

	  const navigate = useNavigate();
	 
	  const onPrompt = () => {
		setOpen(true)
	  }

	  const closeModal = () => {
        setOpen(false);
		localStorage.removeItem("token");
		console.log("close")
		navigate("/");
    };

	
	  const { getRemainingTime, pause, resume, activate } = useIdleTimer({
		
		onPrompt,
		timeout,
		promptBeforeIdle,
		stopOnIdle: true
	  })
	
	  useEffect(() => {
		const interval = setInterval(() => {
		  setRemaining(Math.ceil(getRemainingTime() / 1000))
		}, 500)
	
		return () => {
		  clearInterval(interval)
		}
	  })

	  useEffect(() => {
		console.log(location)
		if (location.pathname === "/") {
			console.log("pausing")
			pause(true)
		} else {
			console.log("resuming")
			// resume(true)
			// activate(true)
			pause(true)
		}
	  }, [location])
	
	
	  const timeTillPrompt = Math.max(remaining - promptBeforeIdle / 1000, 0) 


	return (
		
		<>
			<ThemeProvider theme={defaultTheme} >
		
			
			<Modal
				open={openModal}
				onClose={closeModal}
			>
			   <Box  sx={{
                    position: 'absolute',
					top: '50%',
					left: '50%',
					transform: 'translate(-50%, -50%)',
					width: 700,
					bgcolor: 'background.paper',
					boxShadow: 24,
					padding: 6,
					borderRadius: 8,
                    padding: '20px',
                }}>


					<Typography variant="h3" align="center">
							Idle Alert
						</Typography>
						<Typography variant="h5" align="center">
							You have been idle for too long. Please log in again
						</Typography>
                </Box>

			</Modal>

			

			

			
		
				<Routes>
					<Route path="/" element={<Login />} />
					<Route path="/login" element={<Login />} />
					<Route path="/home/:id/*" element={<LoggedInHomePage />} />
					<Route path="/home/reports" element={<ReportsPage/>} />
					
				</Routes>

			</ThemeProvider>

		</>
	);
}

export default App;
