import { Divider, Typography } from "@mui/material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Fade from "@mui/material/Fade";
import Modal from "@mui/material/Modal";
import TextField from "@mui/material/TextField";
import { useState } from "react";
import callApi from "../api/callApi";
import { MultipleSelect } from "../partials/CheckBoxDropDowns";
import { RegisterLocation } from "../partials/ManagePermission";

let formData = {
	firstName: "",
	lastName: "",
	password: "",
	email: "",
};

/**
 * Generates a unique username. if the generated username exists, then
 * add a number to it to uniquemly identify it
 * @param {*} users
 * @param {*} first
 * @param {*} last
 * @returns the username int he form of firstname.lastname{number}
 */
const getUsername = (users, first, last) => {
	const expectedUsername = `${first}.${last}`;
	if (users.length === 0) return expectedUsername;
	let count = users.length;
	return `${expectedUsername}${count}`;
};

/**
 * The modal that opens when an admin wants to register a
 * @param {*} param0
 * @returns
 */
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

let selectedOptions = [];
/**
 * Implementation for registering a user. Its what opens inside the reigster modal
 * @param {*} param0
 * @returns
 */
const Register = ({ setOpenModal, locations, adminLevels, setChange }) => {
	const [firstNameErr, setFirstNameErr] = useState("");
	const [lastNameErr, setLastNameErr] = useState("");
	const [passwordErr, setPasswordError] = useState("");
	const [emailErr, setEmailErr] = useState("");
	const [formValid, setFormValid] = useState(false);
	const lowestAdminLevel = adminLevels.reduce((prev, curr) =>
		prev.precedence < curr.precedence ? curr : prev
	);

	/**
	 * Checks if the form is valid
	 * @returns
	 */
	const isFormValid = () => {
		const textInputsNotFilled = Object.values(formData).some((value) => value === "");
		if (textInputsNotFilled) return false;
		if (selectedOptions.length === 0) return false;
		return true;
	};

	/**
	 * CHecks if the email exists
	 * @param {*} e
	 */
	const checkEmailExists = (e) => {
		e.preventDefault();
		const data = new FormData(e.currentTarget);
		const email = new FormData(e.currentTarget).get("email").trim();
		const config = {
			method: "get",
			endpoint: `usersEmail/${email}`,
		};
		callApi(
			() => checkRegister(data, true),
			() => checkRegister(data, false),
			config
		);
	};

	/**
	 * Checks that the register form is correctly filled out
	 * @param {*} data
	 * @param {*} emailExists
	 */
	const checkRegister = (data, emailExists) => {
		const firstName = data.get("firstName").trim();
		const lastName = data.get("lastName").trim();
		const password = data.get("password").trim();
		const email = data.get("email").trim();
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
	/**
	 * Creates the new user
	 * @param {*} users
	 * @param {*} firstName
	 * @param {*} lastName
	 * @param {*} password
	 * @param {*} email
	 */
	const createUser = (users, firstName, lastName, password, email) => {
		const username = getUsername(users, firstName, lastName);

		const currentDate = new Date();
		const config = {
			method: "post",
			endpoint: "users",
			data: {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName,
				email: email,
				joinDate: currentDate.toISOString().split("T")[0],
			},
		};

		callApi(createStaff, null, config);
	};

	/**
	 * Create the staff which is an instance of a user with location and perms
	 * @param {*} data
	 */
	const createStaff = (data) => {
		const userid = data.userId;
		const adminid = lowestAdminLevel.id;
		RegisterLocation(selectedOptions, userid, adminid, setOpenModal, setChange);
	};

	/**
	 * Resets the error messages and performs a check on all data passed in the
	 * register form
	 * @param {*} field
	 * @param {*} value
	 */
	const checkInput = (field, value) => {
		setFirstNameErr("");
		setLastNameErr("");
		setPasswordError("");
		setEmailErr("");

		formData = { ...formData, [field]: value };
		setFormValid(isFormValid());
	};

	const handleCheck = (value) => {
		selectedOptions = value;
		setFormValid(isFormValid());
	};

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

				<Box sx={{ margin: "0.5rem" }}>
					{MultipleSelect(locations, "city", "Locations", "Add Locations", true, handleCheck)}
				</Box>
				<Box className="centerHorizonal">
					<Button
						type="submit"
						variant="contained"
						disabled={!formValid}
						sx={{ marginTop: "2rem" }}
					>
						Register
					</Button>
				</Box>
			</form>
		</Box>
	);
};

export default Register;
