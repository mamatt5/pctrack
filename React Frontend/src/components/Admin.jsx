import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import NavBar from "../partials/NavBar";
import { useState } from "react";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Register from "./Register";
import CustomizedTables from "../partials/staffTable";
import { useEffect } from "react";
import callApi from "../api/callApi";
import Paper from "@mui/material/Paper";
import PersonAddAltIcon from "@mui/icons-material/PersonAddAlt";
import FilterListIcon from "@mui/icons-material/FilterList";
import { Divider, IconButton, Tooltip, Typography } from "@mui/material";
import { RegisterModal } from "./Register";
import CustomizedSwitches from "../partials/Switch";
let pageNum = 0;
let pageSize = 10;

export const getAdminsLevels = (setAdminLevels) => {
	// Functionality to get staff on pagination
	const config = {
		method: "get",
		endpoint: "adminLevels",
	};

	callApi(setAdminLevels, null, config);
};

const getStaffCount = (setStaffCount) => {
	// Functionality to get staff on pagination
	console.log(pageNum, pageSize);
	const config = {
		method: "get",
		endpoint: "countStaff",
	};

	callApi(setStaffCount, null, config);
};

const getStaff = (setStaff) => {
	// Functionality to get staff on pagination
	console.log(pageNum, pageSize);
	const config = {
		method: "get",
		endpoint: "staffPage",
		params: {
			pageNumber: pageNum,
			pageSize: pageSize,
		},
	};

	callApi(setStaff, null, config);
};

const searchStaff = (query, setStaff) => {
	console.log(query);
	// Functionality to search users
	const config = {
		method: "get",
		endpoint: `searchStaff/${query}`,
		params: {
			pageNumber: pageNum,
			pageSize: pageSize,
		},
	};

	callApi(setStaff, null, config);
};

const getUserCount = (setUserCount) => {
	// Functionality to get staff on pagination
	console.log(pageNum, pageSize);
	const config = {
		method: "get",
		endpoint: "countUser",
	};

	callApi(setUserCount, null, config);
};

const searchUser = (query, setUser, ...optional) => {
	console.log(query);
	// Functionality to search users
	const config = {
		method: "get",
		endpoint: `searchUser/${query}`,
		params: {
			pageNumber: pageNum,
			pageSize: pageSize,
		},
	};

	callApi(setUser, null, config, ...optional);
};

const searchStaffCount = (query, setStaffCount) => {
	console.log(query);
	// Functionality to search users
	const config = {
		method: "get",
		endpoint: `countStaffPartial/${query}`,
	};

	callApi(setStaffCount, null, config);
};

const searchUserCount = (query, setUserCount) => {
	const config = {
		method: "get",
		endpoint: `countUserPartial/${query}`,
	};

	callApi(setUserCount, null, config);
};


const getLocations = (setLocations) => {
	const config = {
		method: "get",
		endpoint: "locations",
	};
	callApi(setLocations, null, config);
};

const findUserRegisteredLocs = (funct, userId, person) => {
	const config = {
		method: "get",
		endpoint: `staff/${userId}`,
	};
	callApi(funct, null, config, userId, person);
};

const getUser = (setUserLocations) => {
	// Functionality to get staff on pagination
	console.log(pageNum, pageSize);
	const config = {
		method: "get",
		endpoint: "userPage",
		params: {
			pageNumber: pageNum,
			pageSize: pageSize,
		},
	};

	callApi(setUserLocations, null, config);
};

const Admin = ({ currStaff }) => {
	document.body.style = "background: #f2f5f7;";

	// basically for each location, admins have different adminlevels
	// first check location matching.

	console.log(currStaff);
	const [openModal, setOpenModal] = useState(false);
	// sets all the staff.
	const [staff, setStaff] = useState([]);
	// sets all the users.
	const [user, setUser] = useState([]);

	const [query, setQuery] = useState("");
	const [change, setChange] = useState(true); // notifies theres been a page size/page change
	const [staffCount, setStaffCount] = useState(-1); // -1 othewise we might mistake it as no users found
	const [userCount, setUserCount] = useState(-1);
	const [adminLevels, setAdminLevels] = useState([]);
	const [locations, setLocations] = useState([]);
	const [usersOn, setUsersOn] = useState(false);
	const [userLocation, setUserLocation] = useState([]);

	// admins can only register users in locations they have admin permissions in
	const registerableLocations = currStaff.map((item) => item.location);
	// Getting locatiosn

	useEffect(() => {
		getLocations(setLocations);
		getAdminsLevels(setAdminLevels);
	}, []);

	useEffect(() => {
		pageNum = 0;
	}, [query, usersOn]);

	useEffect(() => {
		if (query === "") {
			if (!usersOn) {
				getStaff(setStaff);
				getStaffCount(setStaffCount);
			} else {
				setUserLocation([]);
				getUser(setUser);
				getUserCount(setUserCount);
			}
		} else {
			if (!usersOn) {

				searchStaff(query, setStaff);
				searchStaffCount(query, setStaffCount);
			} else {
				setUserLocation([]);
				searchUser(query, setUser);
				searchUserCount(query, setUserCount);
			}
		}
	}, [ query, change, usersOn]);

	useEffect(() => {
		setUserLocations(user);
	}, [user]);

	const setUserLocations = (user) => {
		// setUser(users);

		for (const person of user) {
			console.log(person);
			findUserRegisteredLocs(handleSetUserLocation, person.userId, person);
		}
	};

	const handleSetUserLocation = (data, userId, user) => {
		const locations = data.map((item) => item.location);
		const newUserLocation = { userId, location: locations, user };

		setUserLocation((prevUserLocations) => {
			// Check if the userId already exists in the array
			const index = prevUserLocations.findIndex((loc) => loc.userId === userId);

			if (index !== -1) {
				// If userId already exists, update the entry
				const updatedUserLocations = [...prevUserLocations];
				updatedUserLocations[index] = newUserLocation;
				return updatedUserLocations;
			} else {
				// If userId does not exist, add a new entry
				return [...prevUserLocations, newUserLocation];
			}
		});
	};

	console.log(userLocation);
	console.log(user);

	return (
		<Box className="centerHorizonal" sx={{ paddingTop: "5rem", marginLeft: "8%" }}>
			<Box sx={{ display: "flex", flexDirection: "row" }}></Box>

			<RegisterModal
				setOpenModal={setOpenModal}
				locations={registerableLocations}
				adminLevels={adminLevels}
				setChange={setChange}
				openModal={openModal}
			/>
			<Paper sx={{ overflow: "hidden", width: "82vw", maxWidth: 980 }} elevation={0}>
				<Box
					sx={{
						display: "flex",
						justifyContent: "space-between",
						alignItems: "center",
						padding: "1.6rem",
					}}
				>
					<Typography variant="h5" sx={{ fontWeight: "bold", color: "#3b3b3b" }}>
						Staff List
					</Typography>
					<Box className="flexRow" sx={{ display: "flex", alignItems: "center" }}>
						<Tooltip title="Toggle between User and Staff" placement="bottom">
							<Box sx={{ "&:hover": { cursor: "pointer" } }}>
								<CustomizedSwitches leftLabel={""} rightlable={"User"} setSwitchOn={setUsersOn} />
							</Box>
						</Tooltip>
						<Divider
							orientation="vertical"
							variant="middle"
							sx={{ marginX: "1rem", height: "1.7rem" }}
						/>
						<Tooltip title="Register User" onClick={() => setOpenModal(true)} placement="bottom">
							<IconButton>
								<PersonAddAltIcon />
							</IconButton>
						</Tooltip>

						<Tooltip title="Filter Users" placement="bottom">
							<IconButton>
								<FilterListIcon />
							</IconButton>
						</Tooltip>
					</Box>
				</Box>
				<CustomizedTables
					array={usersOn ? user : staff}
					currStaff={currStaff}
					setQuery={setQuery}
					staffCount={usersOn ? userCount : staffCount}
					adminLevels={adminLevels}
					usersOn={usersOn}
					userLocation={userLocation}
					setChange={setChange}
					onChangePage={(pNum, pSize) => {
						setChange((change) => !change);
						pageNum = pNum;
						pageSize = pSize;
					}}
				/>
			</Paper>
		</Box>
	);
};

export default Admin;
