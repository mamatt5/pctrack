import React from "react";
import NavBar from "../partials/NavBar";
import callApi from "../api/callApi";
import { useState, useEffect } from "react";
import { useMatch, BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { SearchComputerPage } from "./SearchComputerPage";
import { SearchRoomPage } from "./SearchRoomPage";
import { SearchSoftwarePage } from "./SearchSoftwarePage";
import { useParams } from "react-router-dom";
import Admin from "./Admin";
import ViewComputersInRoomPage from "./ViewComputersInRoomPage";
import { Fade, Typography } from "@mui/material";
import { Grow } from "@mui/material";
import { createTheme } from '@mui/material/styles'
import { Collapse } from "@mui/material";
import AddLocationPage from "./addLocationPage";
import AddRoomPage from "./addRoomPage";



import { Box } from "@mui/material";
import { ReportsPage } from "./ReportsPage";
import HelpPage from "./HelpPage";

export const checkAdmin = (setAdmin, setStaff, id) => {
	const config = {
		method: "get",
		endpoint: `staff/${id}`,
	};
	callApi(checkAdminLevel, null, config, setAdmin, setStaff);
};

const checkAdminLevel = (data, setAdmin, setStaff) => {
	console.log(data);
	// dta == [] means not even a staff,
	// dta == null means its just a staff, not admin
	setAdmin(false);
	setStaff(data);

	for (let i = 0; i < data.length; i++) {
		console.log(data[i].adminLevel.precedence);
		// in data base, all precedence = 100 means NOT admin
		if (data[i].adminLevel.precedence !== 100) {
			setAdmin(true);
			break;
		}
	}
};

const welcomePage = (staff) => {


	return (
		<>
			{staff.map((roles, index) => (
				<>
					{index === 0 ? (
						<Box>
							<Fade in={true} timeout={500}>
								<h1>
									Welcome {roles.user.firstName} {roles.user.lastName}{" "}
								</h1>
							</Fade>

							<Fade in={true} timeout={1000}>
							<h2>
								Your Current Permissions are:{" "}
							</h2>
							</Fade>

						</Box>
					) : null}

					<Box>
						<Fade in={true} timeout={2000}>
							{roles.adminLevel.name === "" ? 
							<Typography>
								User at {roles.location.city}
							</Typography>:

							<Typography>
								{roles.adminLevel.name} Admin at {roles.location.city}
							</Typography>
							}
							

						</Fade>


					</Box>
				</>
			))}
		</>
	);
};
const LoggedInHomePage = () => {
	// check if user if an admin
	// checking if is admin.
	const { id } = useParams();
	const [admin, setAdmin] = useState(false);
	const [staff, setStaff] = useState([]); // gives all the staff info
	console.log(staff);
	useEffect(() => {
		console.log("checking");
		checkAdmin(setAdmin, setStaff, id);
	}, []);

	console.log(staff)
	const isHomePage = useMatch("/home/:id");

	return (
		<Box className="centerHorizonal">
			{isHomePage ? welcomePage(staff) : null}

			<Routes>
				<Route path="/searchroom" element={<SearchRoomPage />} />
				<Route path="/searchsoftware" element={<SearchSoftwarePage />} />
				<Route path="/searchcomputer" element={<SearchComputerPage />} />
				<Route path="/admin" element={<Admin currStaff={staff} />} />
				<Route path="/viewcomputerroom" element={<ViewComputersInRoomPage />} />
				<Route path="/reports" element={<ReportsPage/>}/>
				<Route path="/help" element={<HelpPage />} />
				<Route path="/addlocation" element={<AddLocationPage admin={admin} />} />
				<Route path="/addroom" element={<AddRoomPage admin={admin} currStaff={staff} />} />
			</Routes>
			{console.log("boi")}
			{console.log(staff)}
			<NavBar admin={admin} staff={staff} />
		</Box>
	);
};

export default LoggedInHomePage;
