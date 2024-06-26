import ClearAllOutlinedIcon from "@mui/icons-material/ClearAllOutlined";
import FilterListIcon from "@mui/icons-material/FilterList";
import LibraryAddCheckOutlinedIcon from "@mui/icons-material/LibraryAddCheckOutlined";
import PersonAddAltIcon from "@mui/icons-material/PersonAddAlt";
import { Divider, IconButton, Tooltip, Typography } from "@mui/material";
import Box from "@mui/material/Box";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormGroup from "@mui/material/FormGroup";
import Paper from "@mui/material/Paper";
import Popover from "@mui/material/Popover";
import { useEffect, useState } from "react";
import callApi from "../api/callApi";
import CustomizedTables from "../partials/StaffsTable";
import CustomizedSwitches from "../partials/Switch";
import { RegisterModal } from "./Register";

let pageNum = 0;
let pageSize = 10;

/**
 * Returns all the levels of admins
 * @param {*} setAdminLevels function for setting all admin levels
 */
export const GetAdminsLevels = (setAdminLevels) => {
	// Functionality to get staff on pagination
	const config = {
		method: "get",
		endpoint: "adminLevels",
	};

	callApi(setAdminLevels, null, config);
};

/**
 * Returns the number of staff after its been filtered.
 * @param {*} setStaffCount  sets the staff count
 * @param {*} locationIds    the location Ids of the ticked locations for the filter
 * @param {*} adminLevelIds  the adminIds of the ticked admins for the filter
 */
const getStaffCount = (setStaffCount, locationIds, adminLevelIds) => {
	const config = {
		method: "get",
		endpoint: "countStaffFiltered",
		params: {
			locationIds: locationIds,
			adminLevelIds: adminLevelIds,
		},
	};

	callApi(setStaffCount, null, config);
};

/**
 *
 * @param {*} setStaff 	    sets the staff
 * @param {*} locationIds    the location Ids of the ticked locations for the filter
 * @param {*} adminLevelIds the adminIds of the ticked admins for the filter
 */
const getStaff = (setStaff, locationIds, adminLevelIds) => {
	// Functionality to get staff on pagination
	const config = {
		method: "get",
		endpoint: "filteredStaff",
		params: {
			locationIds: locationIds,
			adminLevelIds: adminLevelIds,
			pageNumber: pageNum,
			pageSize: pageSize,
		},
	};

	callApi(setStaff, null, config);
};

/**
 * Returned a filtered list of staff when using search bar
 * @param {*} query the search query
 * @param {*} setStaff setting the results
 * @param {*} locationIds the ids of the ticked location filters
 * @param {*} adminLevelIds the ids of the ticked admin filters
 */
const searchStaff = (query, setStaff, locationIds, adminLevelIds) => {
	// Functionality to search users
	const config = {
		method: "get",
		endpoint: `searchStaff/${query}`,
		params: {
			locationIds: locationIds,
			adminLevelIds: adminLevelIds,
			pageNumber: pageNum,
			pageSize: pageSize,
		},
	};

	callApi(setStaff, null, config);
};

/**
 * Reutnrs total number of users
 * @param {*} setUserCount sets the num of users
 */
const getUserCount = (setUserCount) => {
	// Functionality to get staff on pagination
	const config = {
		method: "get",
		endpoint: "countUser",
	};

	callApi(setUserCount, null, config);
};

/**
 * Returns the number of users when quieried via search bar
 * @param {*} query the query
 * @param {*} setUser sets the users
 * @param  {...any} optional any optional params to pass into setUser
 */
const searchUser = (query, setUser, ...optional) => {
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

/**
 * Returns a list of staff after apssing filter and the search bar query
 * @param {*} query
 * @param {*} setStaffCount
 * @param {*} locationIds
 * @param {*} adminLevelIds
 */
const searchStaffCount = (query, setStaffCount, locationIds, adminLevelIds) => {
	// gets the staff count
	const config = {
		method: "get",
		endpoint: `countStaffPartial/${query}`,
		params: {
			locationIds: locationIds,
			adminLevelIds: adminLevelIds,
		},
	};

	callApi(setStaffCount, null, config);
};

/**
 * Reutnrs the number of users found after passing the search bar query
 * @param {*} query
 * @param {*} setUserCount
 */
const searchUserCount = (query, setUserCount) => {
	const config = {
		method: "get",
		endpoint: `countUserPartial/${query}`,
	};

	callApi(setUserCount, null, config);
};

/**
 * Returns all locations
 * @param {*} setLocations
 */
const getLocations = (setLocations) => {
	const config = {
		method: "get",
		endpoint: "locations",
	};
	callApi(setLocations, null, config);
};

/**
 * Returns the staff given an userId
 * @param {*} funct
 * @param {*} userId
 * @param {*} person
 */
const findUserRegisteredLocs = (funct, userId, person) => {
	const config = {
		method: "get",
		endpoint: `staff/${userId}`,
	};
	callApi(funct, null, config, userId, person);
};

/**
 * Gets the User for a paginated page
 * @param {*} setUserLocations
 */
const getUser = (setUserLocations) => {
	// Functionality to get all users

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

/**
 * Gets the holistic staff count disregardinig pagination
 * @param {*} setAllStaffCount
 */
const getAllStaffCount = (setAllStaffCount) => {
	const config = {
		method: "get",
		endpoint: `countStaff`,
	};

	callApi(setAllStaffCount, null, config);
};

/**
 * Use effect and state factory for fetching all data first before passing it to
 * any components that need it.
 * @param {*} param0
 * @returns
 */
const Admin = ({ currStaff }) => {
	document.body.style = "background: #f2f5f7;";

	// basically for each location, admins have different adminlevels
	// first check location matching.

	const [openModal, setOpenModal] = useState(false);
	// sets all the staff.
	const [staff, setStaff] = useState([]);
	// sets all the users.
	const [user, setUser] = useState([]);

	const [query, setQuery] = useState("");
	const [change, setChange] = useState(true); // notifies theres been a page size/page change
	const [pageZero, setPageZero] = useState(true); // reserts pages to 0
	const [allStaffCount, setAllStaffCount] = useState(-1); // ALL staff count
	const [staffCount, setStaffCount] = useState(-1); // this is the staff count for PAGINATION.
	const [userCount, setUserCount] = useState(-1);
	const [adminLevels, setAdminLevels] = useState([]);
	const [locations, setLocations] = useState([]);
	const [usersOn, setUsersOn] = useState(false);
	const [userLocation, setUserLocation] = useState([]);
	// initial states of the checkboxes int he filter
	let locationsByIdCheckBox = {};
	let adminLevelsByIdCheckBox = {};
	const [locationCheckBox, setLocationCheckBox] = useState(locationsByIdCheckBox);
	const [permissionsCheckBox, setPermissionsCheckbox] = useState(adminLevelsByIdCheckBox);

	useEffect(() => {
		getAllStaffCount(setAllStaffCount);
		getLocations(setLocations);
		GetAdminsLevels(setAdminLevels);
	}, []);

	useEffect(() => {
		const initialLocationCheckBox = locations.reduce((acc, location) => {
			acc[location.locationId] = true;
			return acc;
		}, {});

		setLocationCheckBox(initialLocationCheckBox);
	}, [locations]);

	useEffect(() => {
		const initialPermissionsCheckBox = adminLevels.reduce((acc, admin) => {
			acc[admin.id] = true;
			return acc;
		}, {});
		setPermissionsCheckbox(initialPermissionsCheckBox);
	}, [adminLevels]);

	//// for the filter popover
	const [anchorEl, setAnchorEl] = useState(null);
	const handleClick = (event) => {
		setAnchorEl(event.currentTarget);
	};
	const handleClose = () => {
		setAnchorEl(null);
		pageNum = 0;
		setChange((c) => !c);
	};

	const open = Boolean(anchorEl);
	const id = open ? "simple-popover" : undefined;

	// for the checkboxes in the filter
	// set to true intially
	const handleCheckBoxLocationChange = (event) => {
		setLocationCheckBox({
			...locationCheckBox,
			[event.target.name]: event.target.checked,
		});
		setPageZero((pageZero) => !pageZero);
	};

	const handleCheckBoxAdminChange = (event) => {
		setPermissionsCheckbox({
			...permissionsCheckBox,
			[event.target.name]: event.target.checked,
		});
		setPageZero((pageZero) => !pageZero);
	};

	// admins can only register users in locations they have admin permissions in
	const registerableLocations = currStaff.map((item) => item.location);
	// Getting locatiosn

	useEffect(() => {
		pageNum = 0;
	}, [query, usersOn]);

	useEffect(() => {
		let locationIds = Object.keys(locationCheckBox)
			.filter((key) => locationCheckBox[key])
			.join(",");
		let adminLevelIds = Object.keys(permissionsCheckBox)
			.filter((key) => permissionsCheckBox[key])
			.join(",");
		if (query === "") {
			if (!usersOn) {
				getStaff(setStaff, locationIds, adminLevelIds);
				getStaffCount(setStaffCount, locationIds, adminLevelIds);
			} else {
				setUserLocation([]);
				getUser(setUser);
				getUserCount(setUserCount);
			}
		} else {
			if (!usersOn) {
				searchStaff(query, setStaff, locationIds, adminLevelIds);
				searchStaffCount(query, setStaffCount, locationIds, adminLevelIds);
			} else {
				setUserLocation([]);
				searchUser(query, setUser);
				searchUserCount(query, setUserCount);
			}
		}
	}, [query, change, usersOn, permissionsCheckBox, locationCheckBox]);

	useEffect(() => {
		setUserLocations(user);
	}, [user]);

	const setUserLocations = (user) => {
		for (const person of user) {
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

	return (
		// staff.length === 0 && allStaffCount === -1 ? <Loading/> :
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

						<Tooltip title="Filter Users" placement="bottom" onClick={handleClick}>
							<IconButton onClick={handleClick}>
								<FilterListIcon />
							</IconButton>
						</Tooltip>
						<Box>
							<Popover
								id={id}
								open={open}
								anchorEl={anchorEl}
								onClose={handleClose}
								anchorOrigin={{
									vertical: "bottom",
									horizontal: "left",
								}}
							>
								<Box sx={{ display: "flex", justifyContent: "space-between" }}>
									<Typography variant="h6" sx={{ padding: "1rem", paddingX: "2rem" }}>
										Filters
									</Typography>
									<Box sx={{ display: "flex", alignItems: "center", paddingRight: "1rem" }}>
										<Tooltip title="Select All">
											<IconButton
												onClick={() => {
													pageNum = 0;
													setChange((C) => !C);
													setPageZero((pageZero) => !pageZero);
													const updatedPermissionsCheckBox = Object.fromEntries(
														Object.keys(permissionsCheckBox).map((key) => [key, true])
													);
													setPermissionsCheckbox(updatedPermissionsCheckBox);
													const loc = Object.fromEntries(
														Object.keys(locationCheckBox).map((key) => [key, true])
													);
													setLocationCheckBox(loc);
												}}
											>
												<LibraryAddCheckOutlinedIcon sx={{ color: "#a1c3d3" }} />
											</IconButton>
										</Tooltip>
										<Tooltip title="Clear All">
											<IconButton
												onClick={() => {
													pageNum = 0;
													setChange((C) => !C);
													setPageZero((pageZero) => !pageZero);
													const updatedPermissionsCheckBox = Object.fromEntries(
														Object.keys(permissionsCheckBox).map((key) => [key, false])
													);
													setPermissionsCheckbox(updatedPermissionsCheckBox);
													const loc = Object.fromEntries(
														Object.keys(locationCheckBox).map((key) => [key, false])
													);
													setLocationCheckBox(loc);
												}}
											>
												<ClearAllOutlinedIcon sx={{ color: "#a1c3d3" }} />
											</IconButton>
										</Tooltip>
									</Box>
								</Box>

								<Divider />

								<Box className="flexRow" sx={{ padding: "2rem" }}>
									<FormGroup>
										{adminLevels.map((admin) => (
											<div key={admin.id}>
												<FormControlLabel
													control={
														<Checkbox
															checked={permissionsCheckBox[admin.id]}
															onChange={handleCheckBoxAdminChange}
															name={admin.id.toString()}
														/>
													}
													label={admin.name === "" ? "None" : admin.name}
												/>
											</div>
										))}
									</FormGroup>
									<FormGroup>
										{locations.map((location) => (
											<div key={location.locationId}>
												<FormControlLabel
													control={
														<Checkbox
															checked={locationCheckBox[location.locationId]}
															onChange={handleCheckBoxLocationChange}
															name={location.locationId.toString()}
														/>
													}
													label={location.city}
												/>
											</div>
										))}
									</FormGroup>
								</Box>
								{/* <Box  sx={{paddingX:"2rem"}}>
									<Button variant="outlined" sx={{borderRadius:20}}> Clear All</Button>
									<Button variant="outlined"  sx={{borderRadius:20}}>Select All</Button>
								</Box> */}
							</Popover>
						</Box>
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
					pageZero={pageZero}
				/>
			</Paper>
		</Box>
	);
};

export default Admin;
