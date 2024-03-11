import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useState } from "react";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import { Divider, Typography } from "@mui/material";
import callApi from "../api/callApi";
import { useEffect } from "react";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { RegisterLocation } from "../partials/ManagePermission";
import { MultipleSelect } from "../partials/CheckBoxDropDowns";
// const fields = ["firstName", "lastName", "password", "email"];
let formData = {
	firstName: "",
	lastName: "",
	password: "",
	email: "",
};
// let updatedCheckedValues = {};

const getUsername = (users, first, last) => {
	console.log(users);
	console.log(first, last);
	const expectedUsername = `${first}.${last}`;
	if (users.length === 0) return expectedUsername;
	let count = users.length;
	return `${expectedUsername}${count}`;
};

export const RegisterModal = ({ openModal, setOpenModal, locations, adminLevels, setChange }) => {
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
							maxWidth: 600,

							borderRadius: "5px",
							p: 4,
						}}
					>
						<Register
							setOpenModal={setOpenModal}
							locations={locations}
							adminLevels={adminLevels}
							setChange={setChange}
						/>
					</Box>
				</Fade>
			</Modal>
		</>
	);
};
let selectedOptions = []
const Register = ({ setOpenModal, locations, adminLevels, setChange }) => {
	const [firstNameErr, setFirstNameErr] = useState("");
	const [lastNameErr, setLastNameErr] = useState("");
	const [passwordErr, setPasswordError] = useState("");
	const [emailErr, setEmailErr] = useState("");
	const [formValid, setFormValid] = useState(false);
	const lowestAdminLevel = adminLevels.reduce((prev, curr) =>
		prev.precedence < curr.precedence ? curr : prev
	);


	// checks if all fields are filled
	const isFormValid = () => {
		const textInputsNotFilled = Object.values(formData).some((value) => value === "");
		console.log(textInputsNotFilled);
		if (textInputsNotFilled) return false;
		console.log(selectedOptions)
		if (selectedOptions.length === 0 ) return false;
		return true;
	};

	const checkEmailExists = (e) => {
		e.preventDefault();
		const data = new FormData(e.currentTarget);
		const email = new FormData(e.currentTarget).get("email").trim();
		const config = {
			method: "get",
			endpoint: `usersEmail/${email}`,
		};
		console.log(email);
		callApi(
			() => checkRegister(data, true),
			() => checkRegister(data, false),
			config
		);
	};

	const checkRegister = (data, emailExists) => {
		console.log(data);
		const firstName = data.get("firstName").trim();
		const lastName = data.get("lastName").trim();
		const password = data.get("password").trim();
		const email = data.get("email").trim();

		// assume that every user must then be made into a x
		// as a result, register will create a new user, and then make then a staff.
		// same username and laste name simply adds 1
		// Count the number of special characters

		const hasTwoSpecialChars = () => {
			const specialChars = password.match(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g) || [];
			return specialChars.length >= 2;
		};

		if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
			setEmailErr("Invalid Email format");
		} else if (emailExists) {
			setEmailErr(
				'This user already exists. Toggle on "Users" if you wish to register them to a new location'
			);
		} else if (!/^[a-zA-Z]+$/.test(firstName)) {
			setFirstNameErr("Name must only contain characters");
		} else if (!/^[a-zA-Z]+$/.test(lastName)) {
			setLastNameErr("Name must only contain characters");
		} else if (password.length < 12) {
			setPasswordError("Password must be 12 or more characters long");
		} else if (!hasTwoSpecialChars()) {
			setPasswordError("Password must have 2 or more special characters");
		} else {
			const config = {
				method: "get",
				endpoint: `searchUser/${firstName}.${lastName}`,
			};

			callApi(createUser, null, config, firstName, lastName, password, email);
		}
	};

	const createUser = (users, firstName, lastName, password, email) => {
		console.log(users, firstName, lastName);
		const username = getUsername(users, firstName, lastName);
		console.log(username);
		const currentDate = new Date();
		const config = {
			method: "post",
			endpoint: "users",
			data: {
				username: username,
				password: "password",
				firstName: firstName,
				lastName: lastName,
				email: email,
				joinDate: currentDate.toISOString().split("T")[0],
			},
		};

		callApi(createStaff, null, config);
	};

	const createStaff = (data) => {
		console.log(data);
		const userid = data.userId;
		const adminid = lowestAdminLevel.id;
		RegisterLocation(selectedOptions, userid, adminid, setOpenModal, setChange)
	};

	// checklist
	const checkInput = (field, value) => {
		setFirstNameErr("");
		setLastNameErr("");
		setPasswordError("");
		setEmailErr("");

		console.log(value, field);
		formData = { ...formData, [field]: value };
		console.log(formData);
		setFormValid(isFormValid());
	};

	const handleCheck = (value) => {
		console.log(value)
		selectedOptions = value
		setFormValid(isFormValid());
	};

	//

	// console.log(checkedValues);
	return locations.length === 0 ? null : (
		<Box sx={{ width: "95%", padding: "1.5rem" }}>
			<Box>
				<Typography variant="h4">Register a user</Typography>
			</Box>
			<Divider sx={{ margin: "1rem 0 1rem 0" }} />
			<form onSubmit={checkEmailExists} className="flexCol">
				<TextField
					size="medium"
					name="firstName"
					label="Given Name"
					error={Boolean(firstNameErr)}
					helperText={firstNameErr ? firstNameErr : ""}
					onChange={(e) => checkInput("firstName", e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>
				<TextField
					name="lastName"
					label="Family Name"
					error={Boolean(lastNameErr)}
					helperText={lastNameErr ? lastNameErr : ""}
					onChange={(e) => checkInput("lastName", e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>

				<TextField
					name="email"
					label="Email"
					error={Boolean(emailErr)}
					helperText={emailErr ? emailErr : ""}
					onChange={(e) => checkInput("email", e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>
				<TextField
					name="password"
					label="Password"
					error={Boolean(passwordErr)}
					helperText={passwordErr ? passwordErr : ""}
					onChange={(e) => checkInput("password", e.target.value)}
					sx={{ margin: "0.5rem" }}
				/>

				<Box sx={{ margin: "0.5rem" }} >
					{MultipleSelect(
						locations,
						"city",
						"Locations",
						"Add Locations",
						true,
						handleCheck
					)}
				</Box>
				<Box className="centerHorizonal">
					<Button type="submit" variant="contained" disabled={!formValid} sx={{marginTop:"2rem"}}>
						Register
					</Button>
				</Box>
			</form>
		</Box>
	);
};

export default Register;
