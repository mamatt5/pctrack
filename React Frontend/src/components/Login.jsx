import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

// outside to prevent reredner
let email = "";
let password = "";

// logs a user in
/**
 *
 *
 * @returns
 */
const login = () => {
    const navigate = useNavigate();

	const [emailError, setEmailError] = useState("");
	const [passwordError, setPasswordError] = useState("");
	const [formValid, setFormValid] = useState(false);

	const checkInput = (field, value) => {
		console.log(value);
		if (field === "email") email = value;
		else if (field === "password") password = value;

		// Check overall form validity
		setFormValid(email !== "" && password !== "");
		console.log(formValid);
	};

	const checkLogin = (e) => {
		e.preventDefault();
		const data = new FormData(e.currentTarget);
		const email = data.get("email").trim();
		const password = data.get("password").trim();
		if (email !== "admin") {
			setEmailError("* Email not found");
		} else if (password == "") {
			setPasswordError("* Password incorrect");
		} else {
            loginUser("Sd", "admin")
        }
	};

	// store token in local storage if a user logs in
	// when login out, make sure to delete from local storage
	const loginUser = (bearerToken, username) => {
		// localStorage.setItem("token", bearerToken);
        navigate(`/admin`)
	};

	return (
		<>
			<Box style={{ left: 0 }}>PC Track</Box>
			<Box sx={{ border: "1px solid black", borderRadius: "10px" }}>
				<Box>Login</Box>
				<form onSubmit={checkLogin} className="flexCol">
					<TextField
						error={Boolean(emailError)}
						helperText={emailError ? emailError : ""}
						name="email"
						label="Email"
						onChange={(e) => checkInput("email", e.target.value)}
					/>
					<TextField
						error={Boolean(passwordError)}
						helperText={passwordError ? passwordError : ""}
						name="password"
						label="Password"
						onChange={(e) => checkInput("password", e.target.value)}
					/>
					<Button type="submit" variant="contained" disabled={!formValid} disableElevation>
						Login
					</Button>
				</form>
			</Box>
			<Home/>
		</>
	);
};

export default login;
