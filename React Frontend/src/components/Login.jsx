import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import callApi from "../partials/callApi";

// outside to prevent reredner
let username = "";
let password = "";

// logs a user in
/**
 *
 *
 * @returns
 */
const login = () => {
	const navigate = useNavigate();

	const [usernameError, setusernameError] = useState("");
	const [passwordError, setPasswordError] = useState("");
	const [formValid, setFormValid] = useState(false);

	const checkInput = (field, value) => {
		console.log(value);
		if (field === "username") username = value;
		else if (field === "password") password = value;

		// Check overall form validity
		setFormValid(username !== "" && password !== "");
		console.log(formValid);
	};

	const checkLogin = (e) => {
		e.preventDefault();

		const data = new FormData(e.currentTarget);
		const username = data.get("username").trim();
		const password = data.get("password").trim();

		const config = {
			method: "post",
			endpoint: "auth/login",
			auth: {
				username: username,
				password: password,
			},
		};

		callApi(loginUser, loginError, config, username);
	};

	// store token in local storage if a user logs in
	// when login out, make sure to delete from local storage
	const loginUser = (bearerToken, username) => {
		localStorage.setItem("token", bearerToken);
		// check if its staff or admin.
		navigate(`/admin`);
	};

	//
	const loginError = (error) => {
		setusernameError("");
		setPasswordError("");
		console.log(username);
		const config = {
			method: "get",
			endpoint: `username/${username}`,
			auth: {
				username: username,
				password: password,
			},
		};

		// if we cant find the user, its a username issue
		// if we can, its a password issue.
		console.log(error);
		callApi(
			() => {
				setPasswordError("* Incorrect Password");
			},
			() => {
				setusernameError("* Username does not exist");
			},
			config
		);
	};

	return (
		<>
			<Box style={{ left: 0 }}>PC Track</Box>
			<Box sx={{ border: "1px solid black", borderRadius: "10px" }}>
				<Box>Login</Box>
				<form onSubmit={checkLogin} className="flexCol">
					<TextField
						error={Boolean(usernameError)}
						helperText={usernameError ? usernameError : ""}
						name="username"
						label="Username"
						onChange={(e) => checkInput("username", e.target.value)}
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
		</>
	);
};

export default login;
