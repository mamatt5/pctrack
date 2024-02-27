import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import MultipleSelectCheckmarks from "../partials/dropDown";
import InfoOutlinedIcon from "@mui/icons-material/InfoOutlined";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

import Config from "../configs.json";

const location = Config.LOCATIONS;

const fields = ["firstName", "lastName", "email", "password", "confirmPassword"];
let formData = {
	firstName: "",
	lastName: "",
	email: "",
	password: "",
	confirmPassword: "",
};

const Register = () => {
	const navigate = useNavigate();
	const [firstNameErr, setFirstNameErr] = useState("");
	const [lastNameErr, setLastNameErr] = useState("");
	const [emailErr, setEmailError] = useState("");
	const [passwordErr, setPasswordError] = useState("");
	const [confirmPasswordErr, setConfirmPasswordErr] = useState("");
	const [formValid, setFormValid] = useState(false);

	const isFormValid = () => {
		for (const field of fields) {
			if (!formData[field]) {
				return false; // If any field is empty, return false
			}
		}
		return true;
	};

	const checkRegister = (e) => {
		e.preventDefault();
		const data = new FormData(e.currentTarget);
		console.log(data);
		const firstName = data.get("firstName").trim();
		const lastName = data.get("lastName").trim();
		const email = data.get("email").trim();
		const password = data.get("password").trim();
		const confirmPassword = data.get("confirmPassword").trim();

		const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!emailPattern.test(email)) {
			setEmailError("* Invalid Email");
		} else if (password !== confirmPassword) {
			setConfirmPasswordErr("* Password does not match");
		}
	};

	const checkInput = (field, value) => {

        // reset error messages
        setEmailError("")
        setPasswordError("")
        setConfirmPasswordErr("")

	    // change the formData for the specific field
		formData = { ...formData, [field]: value };
		console.log(formData);
		setFormValid(isFormValid());
		console.log(formValid);
	};

	return (
		<>
			<Box sx={{ border: "1px solid black", borderRadius: "10px" }}>
				<Box>Register a user</Box>
				<form onSubmit={checkRegister} className="flexCol">
					<TextField
						name="firstName"
						label="Given Name"
						error={Boolean(firstNameErr)}
						helperText={firstNameErr ? firstNameErr : ""}
						onChange={(e) => checkInput("firstName", e.target.value)}
					></TextField>
					<TextField
						name="lastName"
						label="Family Name"
						error={Boolean(lastNameErr)}
						helperText={lastNameErr ? lastNameErr : ""}
						onChange={(e) => checkInput("lastName", e.target.value)}
					></TextField>
					<TextField
						name="email"
						label="Email Name"
						error={Boolean(emailErr)}
						helperText={emailErr ? emailErr : ""}
						onChange={(e) => checkInput("email", e.target.value)}
					></TextField>
					<TextField
						name="password"
						label="Password"
						error={Boolean(passwordErr)}
						helperText={passwordErr ? passwordErr : ""}
						onChange={(e) => checkInput("password", e.target.value)}
					></TextField>
					<TextField
						name="confirmPassword"
						label="Confirm Password"
						error={Boolean(confirmPasswordErr)}
						helperText={confirmPasswordErr ? confirmPasswordErr : ""}
						onChange={(e) => checkInput("confirmPassword", e.target.value)}
					></TextField>
					<Box className="flexRow" sx={{ alignItems: "center" }}>
						<MultipleSelectCheckmarks array={location} label="Location(s)" />
						<InfoOutlinedIcon sx={{ cursor: "pointer" }} />
					</Box>

					<Button type="submit" variant="contained" disabled={!formValid}>
						{" "}
						Register User
					</Button>
				</form>
			</Box>
		</>
	);
};

export default Register;
