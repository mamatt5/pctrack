import { useState } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Import BrowserRouter as Router
import { ThemeProvider } from "@mui/material";
import { createTheme } from "@mui/material";
//pages

import Login from "./components/Login";
import Admin from "./components/Admin";
import LoggedInHomePage from "./components/LoggedInHomePage";
import { SearchComputerPage } from "./components/SearchComputerPage";
import { SearchRoomPage } from "./components/SearchRoomPage";
import { SearchSoftwarePage } from "./components/SearchSoftwarePage";
import UpdateDetailsPage from "./components/UpdateDetailsPage";
import NavBar from "./partials/NavBar";

function App() {

	const defaultTheme = createTheme({
		palette: {
		  background: {
			default : "white", //#e3f2fd
		  }
		},
	  });

	return (
		<div  >
			<ThemeProvider theme={defaultTheme} >
			<Router>
				<Routes>
					<Route path="/" element={<Login />} />
					<Route path="/login" element={<Login />} />
					<Route path="/home/:id/*" element={<LoggedInHomePage />} />

				</Routes>

			</Router>
			</ThemeProvider>

		</div>
	);
}

export default App;
