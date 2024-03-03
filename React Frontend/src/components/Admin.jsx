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
import { IconButton, Tooltip, Typography } from "@mui/material";

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

const searchStaffCount = (query, setStaffCount) => {
	console.log(query);
	// Functionality to search users
	const config = {
		method: "get",
		endpoint: `countStaffPartial/${query}`,
	};

	callApi(setStaffCount, null, config);
};

const getUsers = (setUsers) => {
	// Functionality to get users
	const config = {
		method: "get",
		endpoint: "users",
	};

	callApi(setUsers, null, config);
};

const RegisterModal = ({ openModal, setOpenModal, users }) => {
	console.log(users);
	return (
		<>
			<Modal
				open={openModal}
				onClose={() => setOpenModal(false)}
				closeAfterTransition
				aria-labelledby="register modal"
				aria-describedby="opens a modal to register a user"
				sx={{
					"& .MuiBackdrop-root": {
						backgroundColor: "rgba(0, 0, 0, 0.2)", // Adjust opacity here (0.5 for 50% darkness)
					},
				}}
			>
				<Fade in={openModal}>
					<Box

						sx={{
							position: "absolute",
							top: "50%",
							left: "50%",
							transform: "translate(-50%, -50%)",
							backgroundColor: "white",
							borderRadius: 3,
							width: "70vw",
                            maxWidth:600,

							borderRadius: "5px",
							p: 4,
						}}
					>
						<Register users={users} setOpenModal={setOpenModal} />
					</Box>
				</Fade>
			</Modal>
		</>
	);
};

const Admin = ({ currStaff }) => {
	document.body.style = "background: #f2f5f7;";

	// basically for each location, admins have different adminlevels
	// first check location matching.

	console.log(currStaff);
	const [openModal, setOpenModal] = useState(false);
	const [staff, setStaff] = useState([]);
	const [users, setUsers] = useState([]);
	const [query, setQuery] = useState("");
	const [change, setChange] = useState(true); // notifies theres been a page size/page change
	const [staffCount, setStaffCount] = useState(-1); // -1 othewise we might mistake it as no users found
	const [adminLevels, setAdminLevels] = useState([]);

	useEffect(() => {
		getAdminsLevels(setAdminLevels);
	}, []);


	useEffect(() => {
		if (query === "") {
			getStaff(setStaff);
			getStaffCount(setStaffCount);
		} else {
			searchStaff(query, setStaff);
			searchStaffCount(query, setStaffCount);
		}
	}, [openModal, query, change]);

	useEffect(() => {
		pageNum = 0;
	}, [query]);

	useEffect(() => {
		getUsers(setUsers);
	}, [openModal]);

	return (
		<Box className="centerHorizonal" sx={{ paddingTop: "5rem", marginLeft: "8%" }}>
			<Box sx={{ display: "flex", flexDirection: "row" }}></Box>

			<RegisterModal openModal={openModal} setOpenModal={setOpenModal} users={users} />
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
					<Box>
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
					array={staff}
                    currStaff={currStaff}
					setQuery={setQuery}
					staffCount={staffCount}
                    adminLevels={adminLevels}
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
