import { Visibility, VisibilityOff } from "@mui/icons-material";
import { FormControl, Grow, IconButton, OutlinedInput, Typography } from "@mui/material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import InputAdornment from "@mui/material/InputAdornment";
import InputLabel from "@mui/material/InputLabel";
import TextField from "@mui/material/TextField";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import callApi from "../api/callApi";

// outside to prevent reredner
let username = "";
let password = "";

/**
 *
 * @param {*} props
 * @returns a login component - username and password.
 */
const login = (props) => {
	const navigate = useNavigate();

	const [showPassword, setShowPassword] = useState(false);
	const [usernameError, setusernameError] = useState("");
	const [passwordError, setPasswordError] = useState("");
	const [formValid, setFormValid] = useState(false);

	const checkInput = (field, value) => {
		if (field === "username") username = value;
		else if (field === "password") password = value;

		// Check overall form validity
		setFormValid(username !== "" && password !== "");
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

		const config = {
			method: "get",
			endpoint: `username/${username}`,
		};

		// if we cant find the user, its a username issue
		// if we can, its a password issue.
		callApi(
			(res) => {
				navigate(`/home/${res.userId}`);
			},
			null,
			config
		);
	};

	// checks if the login details are correct or not
	const loginError = (error) => {
		setusernameError("");
		setPasswordError("");
		const config = {
			method: "get",
			endpoint: `username/${username}`,
		};

		// if we cant find the user, its a username issue
		// if we can, its a password issue.
		callApi(
			() => {
				setPasswordError("* Incorrect Credentials");
			},
			() => {
				setusernameError("* Username does not exist");
			},
			config
		);
	};

	return (
		<div className="centerHorizonal">
			<Grow in={true} timeout={1000}>
				<Box
					sx={{
						marginTop: 8,
						display: "flex",
						flexDirection: "column",
						alignItems: "center",
						backgroundColor: "white",
						borderRadius: 8,
						padding: 6,
						boxShadow: "0px 8px 16px rgba(0, 0, 0, 0.2)",
						className: "box",
					}}
				>
					<Box>
						<Typography variant="h4" sx={{ padding: "1rem", fontWeight: "bold" }}>
							PC Track
						</Typography>
						<Typography variant="h6" sx={{ padding: "0.5rem" }}>
							Login
						</Typography>
					</Box>
					<form onSubmit={checkLogin} className="flexCol">
						<TextField
							error={Boolean(usernameError)}
							helperText={usernameError ? usernameError : ""}
							name="username"
							label="Username"
							onChange={(e) => checkInput("username", e.target.value)}
							sx={{ margin: "0.5rem" }}
						/>

						<FormControl sx={{ m: 1 }} variant="outlined">
							<InputLabel htmlFor="outlined-adornment-password" error={Boolean(passwordError)}>
								Password
							</InputLabel>
							<OutlinedInput
								error={Boolean(passwordError)}
								helperText={passwordError ? passwordError : ""}
								name="password"
								label="Password"
								onChange={(e) => checkInput("password", e.target.value)}
								type={showPassword ? "text" : "password"}
								endAdornment={
									<InputAdornment position="end">
										<IconButton
											aria-label="toggle password visibility"
											onClick={() => setShowPassword((show) => !show)}
											onMouseDown={(e) => e.preventDefault()}
											edge="end"
										>
											{showPassword ? <VisibilityOff /> : <Visibility />}
										</IconButton>
									</InputAdornment>
								}
							/>
						</FormControl>
						<Typography
							variant="caption"
							color="error"
							sx={{ mt: 0, ml: 3, mr: 1, marginRight: "auto" }}
						>
							{passwordError ? passwordError : ""}
						</Typography>

						<Button
							type="submit"
							variant="contained"
							disabled={!formValid}
							disableElevation
							sx={{ margin: "2rem" }}
						>
							Login
						</Button>
					</form>
				</Box>
			</Grow>
		</div>
	);
};

export default login;
