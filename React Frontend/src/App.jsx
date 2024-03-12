import { ThemeProvider } from "@mui/material";
import { createTheme } from '@mui/material/styles';
import { useState } from "react";
import { Route, Routes } from "react-router-dom"; // Import BrowserRouter as Router
import "./App.css";
//pages

import { Box, Modal, Switch, Typography } from "@mui/material";
import { useEffect } from "react";
import { useIdleTimer } from 'react-idle-timer';
import { useLocation, useNavigate } from "react-router-dom";
import LoggedInHomePage from "./components/LoggedInHomePage";
import Login from "./components/Login";
import { ReportsPage } from "./components/ReportsPage";

const promptBeforeIdle = 4_000

function App() {

	const defaultTheme = createTheme({
		palette: {
			background: {
				default: "#edf1f9 ",
			}
		},
	});


	let timeout = 10000_000
	
	const [openModal, setOpen] = useState(false)
	const [demoMode, setDemoMode] = useState(false);

	if (demoMode) {
		timeout = 10_000
	} else {
		timeout = 10000_000
	}

	const [remaining, setRemaining] = useState(timeout)

	let location = useLocation();

	const closeModal = () => {
		setOpen(false);
		localStorage.removeItem("token");
		navigate("/");
	};


	const onPrompt = () => {
		setOpen(true)
	}

	const change = () => {

		setDemoMode(!demoMode)
		if (demoMode) {
			timeout = 10_000
		} else {
			timeout = 10000_000
		}
	}

	// idle timer use to detect used to log a user out if they
	// have been idle for too long
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

		if (location.pathname === "/") {

			pause(true)
		} else {

			resume(true)
			activate(true)


		}
	}, [location, demoMode])




	return (

		<>
			<ThemeProvider theme={defaultTheme} >
				{location.pathname === "/" &&
					<div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
						<span>Idle Mode</span>
						<Switch checked={demoMode} onClick={change} />
					</div>}

				<Modal
					open={openModal}
					onClose={closeModal}
				>
					<Box sx={{
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
					<Route path="/" element={<Login changeDemoMode={change} />} />
					<Route path="/login" element={<Login changeDemoMode={change} />} />
					<Route path="/home/:id/*" element={<LoggedInHomePage />} />
					<Route path="/home/reports" element={<ReportsPage />} />

				</Routes>

			</ThemeProvider>

		</>
	);
}

export default App;
