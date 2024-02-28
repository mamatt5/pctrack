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

const getStaff = (setStaff) => {
	// Functionality to get users
	const config = {
		method: "get",
		endpoint: "staff",
	};

	// if we cant find the user, its a username issue
	// if we can, its a password issue.
	callApi(setStaff, null, config);
};

const getUsers = (setUsers) => {
	// Functionality to get users
	const config = {
		method: "get",
		endpoint: "users",
	};

	// if we cant find the user, its a username issue
	// if we can, its a password issue.
	callApi(setUsers, null, config);
};

const UsersTab = () => {
	return <></>;
};

const RegisterModal = ({ openModal, setOpenModal, users }) => {
    console.log(users)
	return (
		<>
			<Modal
				open={openModal}
				onClose={() => setOpenModal(false)}
				closeAfterTransition
				aria-labelledby="register modal"
				aria-describedby="opens a modal to register a user"
			>
				<Fade in={openModal}>
					<Box
						className="centerHorizonal"
						sx={{
							position: "absolute",
							top: "50%",
							left: "50%",
							transform: "translate(-50%, -50%)",
							backgroundColor: "white",
                            borderRadius:3,
							p: 4,
						}}
					>
						<Register users={users} setOpenModal={setOpenModal}/>
					</Box>
				</Fade>
			</Modal>
		</>
	);
};

const Admin = () => {
	const [openModal, setOpenModal] = useState(false);
	const [staff, setStaff] = useState([]);
    const [users, setUsers] = useState([]);

	useEffect(() => {
		getStaff(setStaff);
        getUsers(setUsers)
	}, [openModal]);


	return (
		<>
			<Box className="dashBoardPadding">
				<Box sx={{ display: "flex", flexDirection: "row" }}>
					<UsersTab />
				</Box>

				<Button
					variant="contained"
					disableElevation
					sx={{
						position: "fixed",
						bottom: "10%",
						right: "10%",
						zIndex: 1000,
					}}
					onClick={() => setOpenModal(true)}
				>
					Register User
				</Button>
				<RegisterModal openModal={openModal} setOpenModal={setOpenModal} users={users}/>
				<CustomizedTables array={staff} />
			</Box>
		</>
	);
};

export default Admin;

