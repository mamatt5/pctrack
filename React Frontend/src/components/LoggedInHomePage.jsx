import { Fade, Typography } from "@mui/material";
import { Route, Routes, useMatch, useParams } from "react-router-dom";
import callApi from "../api/callApi";
import NavBar from "../partials/NavBar";
import { useState, useEffect } from "react";
import { SearchComputerPage } from "./SearchComputerPage";
import { SearchRoomPage } from "./SearchRoomPage";
import { SearchSoftwarePage } from "./SearchSoftwarePage";
import ViewComputersInRoomPage from "./ViewComputersInRoomPage";
import ManageFacilities from "./ManageFacilities";
import { Box } from "@mui/material";
import HelpPage from "./HelpPage";
import { ReportsPage } from "./ReportsPage";
import Admin from "./Admin";

/**
 * Checks the adminLevls and
 * @param {*} setAdmin
 * @param {*} setStaff
 * @param {*} id the staff id
 */
export const CheckAdmin = (setAdmin, setStaff, id) => {
	const config = {
		method: "get",
		endpoint: `staff/${id}`,
	};
	callApi(checkAdminLevel, null, config, setAdmin, setStaff);
};

/**
 * Checks the adminLevls and sets it in setAdmin, as well as fetching staff
 * @param {*} data is the data of the staff in question
 * @param {*} setAdmin
 * @param {*} setStaff
 */
const checkAdminLevel = (data, setAdmin, setStaff) => {

	// dta == [] means not even a staff,
	// dta == null means its just a staff, not admin
	setAdmin(false);
	setStaff(data);

	for (let i = 0; i < data.length; i++) {
		// in data base, all precedence = 100 means NOT admin
		if (data[i].adminLevel.precedence !== 100) {
			setAdmin(true);
			break;
		}
	}
};

/**
 *
 * @param {*} staff
 * @returns a basic welcome page. Details the users name, and their permissions
 */
const welcomePage = (staff) => {


	return (
		<Fade in={true} timeout={1000}>
		<Box sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            backgroundColor: 'white',
            borderRadius: 8,
            padding: 6,
            boxShadow: '0px 8px 16px rgba(0, 0, 0, 0.2)',
            className: 'box',
          }} >
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

							<Typography variant="h6">
								{roles.adminLevel.name} Admin at {roles.location.city}
							</Typography>
							}


						</Fade>


					</Box>
				</>
			))}
		</Box>
		</Fade>
	);
};

/**
 *
 * @returns the welcome page if at root page, else, its returns its repective routes.
 */
const LoggedInHomePage = () => {
	// check if user if an admin
	// checking if is admin.
	const { id } = useParams();
	const [admin, setAdmin] = useState(false);
	const [staff, setStaff] = useState([]); // gives all the staff info
	const [render, setRender] = useState(false);
	


	useEffect(() => {
		CheckAdmin(setAdmin, setStaff, id);
	}, [render]);

	
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
				<Route path="/reports" element={<ReportsPage />} />
				<Route path="/help" element={<HelpPage />} />
				{/* <Route path="/addlocation" element={<AddLocationPage admin={admin} />} />
				<Route path="/addroom" element={<AddRoomPage admin={admin} currStaff={staff} />} /> */}
				<Route path="/manageFacilities" element={< ManageFacilities  admin={admin} currStaff={staff} setRender={setRender}/>} />
			</Routes>
			<NavBar admin={admin} staff={staff} />
		</Box>
	);
};

export default LoggedInHomePage;
