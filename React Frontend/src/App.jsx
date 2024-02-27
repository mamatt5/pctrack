import { useState } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Import BrowserRouter as Router
import { ThemeProvider } from "@mui/material";
import { createTheme } from "@mui/material";
//pages

//pages
import Login from "./components/Login";
import Admin from "./components/Admin";
import Home from "./components/Home";
import LoggedInHomePage from "./components/LoggedInHomePage";
import { SearchComputerPage } from "./components/SearchComputerPage";
import { SearchRoomPage } from "./components/SearchRoomPage";
import { SearchSoftwarePage } from "./components/SearchSoftwarePage";
import UpdateDetailsPage from "./components/UpdateDetailsPage";

function App() {

	const defaultTheme = createTheme({
		palette: {
		  background: {
			default : "#e3f2fd",
		  } 
		},
	  });

	return (
		<>
			<ThemeProvider theme={defaultTheme} >
			<Router>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/login" element={<Login />} />
					<Route path="/admin" element={<Admin />} />
					
					<Route path="/home" element={<LoggedInHomePage />} />
					<Route path="/searchroom" element={<SearchRoomPage />} />
					<Route path="/searchsoftware" element={<SearchSoftwarePage />} />
					<Route path="/searchcomputer" element={<SearchComputerPage />} />
					<Route path="/updatedetails" element={<UpdateDetailsPage />} />
				</Routes>
      
			</Router>
			</ThemeProvider>
		</>
	);
}

export default App;
