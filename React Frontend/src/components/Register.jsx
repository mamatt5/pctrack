import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useState } from "react";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import { Typography } from "@mui/material";
import callApi from "../api/callApi";
import { useEffect } from "react";

const fields = ["firstName", "lastName", "password"];
let formData = {
	firstName: "",
	lastName: "",
	password: "",
};
let updatedCheckedValues = {};

const getUsername = (users, first, last) => {
	console.log(users);
	let count = 0;
	const expectedUsername = `${first}.${last}`;
	for (let user of users) {
		console.log(user.username);
		if (user.username.startsWith(expectedUsername)) count++;
	}

	if (count === 0) return expectedUsername;
	return `${first}.${last}${count}`;
};

const getLocations = (setLocations) => {
	const config = {
		method: "get",
		endpoint: "locations",
	};
	callApi(
		(locations) => {
			setLocations(locations);
		},
		null,
		config
	);
};

const Register = ({ users, setOpenModal }) => {
	console.log(users);
	const [locations, setLocations] = useState([]);
	const [firstNameErr, setFirstNameErr] = useState("");
	const [lastNameErr, setLastNameErr] = useState("");
	const [passwordErr, setPasswordError] = useState("");
	const [formValid, setFormValid] = useState(false);
	const [checkedValues, setCheckedValues] = useState({});

	// Getting locatiosn

	useEffect(() => {
		getLocations(setLocations);

		let locs = {};
		locations.forEach((location) => {
			console.log(location);
			locs[location.city] = false;
		});
		setCheckedValues(locs);
	}, []);

	// checks if all fields are filled
	const isFormValid = () => {
		const textInputsNotFilled = Object.values(formData).some((value) => value === "");
		console.log(textInputsNotFilled);
		if (textInputsNotFilled) return false;

		// const obj = { a: 1, b: 2, c: 3 };
		// console.log(Object.values(obj)); // Output: [1, 2, 3]

		console.log(updatedCheckedValues);
		const atLeastOneChecked = Object.values(updatedCheckedValues).some((value) => value === true);

		if (!atLeastOneChecked) return false;
		return true;
	};

	const checkRegister = (e) => {
		e.preventDefault();
		const data = new FormData(e.currentTarget);
		console.log(data);
		const firstName = data.get("firstName").trim();
		const lastName = data.get("lastName").trim();
		const password = data.get("password").trim();


		// assume that every user must then be made into a staff
		// as a result, register will create a new user, and then make then a staff.
		// same username and laste name simply adds 1

		// Count the number of special characters
		const hasTwoSpecialChars = () => {
			const specialChars = password.match(/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g) || [];
			return specialChars.length >= 2;
		};
		if (!/^[a-zA-Z]+$/.test(firstName)) {
			setFirstNameErr("Name must only contain characters");
		} else if (!/^[a-zA-Z]+$/.test(lastName)) {
			setLastNameErr("Name must only contain characters");
		} else if (password.length < 12) {
			setPasswordError("Password must be 12 or more characters long");
		} else if (!hasTwoSpecialChars()) {
			setPasswordError("Password must have 2 or more special characters");
		} else {
			const username = getUsername(users, firstName, lastName);
			console.log(username);

			const config = {
				method: "post",
				endpoint: "users",
				data: {
					username: username,
					password: password,
					firstName: firstName,
					lastName: lastName,
				},
			};
			console.log("hueh");
			callApi(createStaff, null, config);
		}
	};

	const createStaff = (data) => {
		console.log(checkedValues);
		console.log("huseh");
		console.log(data);

		// make new staff for each ticked location

		Object.entries(checkedValues).forEach(([city, checked]) => {
			console.log(city, checked);
			let id = locations.find((location) => location.city === city).locationId;
			console.log(id);
			const config = {
				method: "post",
				endpoint: "staff",
				data: {
					user: {
						userId: data.userId,
					},
					location: {
						locationId: id,
					},
					adminLevel: null,
				},
			};

			callApi(
				() => {
					setOpenModal(false);
				},
				null,
				config
			);
		});
	};

	// checklist
	const checkInput = (field, value) => {
		setFirstNameErr("");
		setLastNameErr("");
		setPasswordError("");

		console.log(value, field);
		formData = { ...formData, [field]: value };
		console.log(formData);
		setFormValid(isFormValid());
	};

	const handleCheck = (event) => {
		setCheckedValues({
			...checkedValues,
			[event.target.name]: event.target.checked,
		});
		console.log(checkedValues);
		updatedCheckedValues = { ...checkedValues, [event.target.name]: event.target.checked };
		console.log(updatedCheckedValues);

		setFormValid(isFormValid());
	};

	//

	console.log(checkedValues);
	return (
		<>
			<Box sx={{ border: "1px solid black", borderRadius: "10px", width: "35vw", padding: "5%" }}>
				<Box>
					<Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
						Register a user
					</Typography>
				</Box>

				<form onSubmit={checkRegister} className="flexCol">
					<TextField
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
						name="password"
						label="Password"
						error={Boolean(passwordErr)}
						helperText={passwordErr ? passwordErr : ""}
						onChange={(e) => checkInput("password", e.target.value)}
						sx={{ margin: "0.5rem" }}
					/>

					<Typography>Office</Typography>
					<Box className="flexRow" sx={{ alignItems: "center", paddingLeft:"2rem"}}>
						<FormGroup>
							{locations.map((item) => (
								<FormControlLabel
									control={
										<Checkbox
											checked={checkedValues.APAC}
											onChange={handleCheck}
											name={item.city}
										/>
									}
									label={item.city}
								/>
							))}
						</FormGroup>
					</Box>
					<Box className="centerHorizonal">
						<Button
							type="submit"
							variant="contained"
							disabled={!formValid}
							sx={{ width: "20%" }}
							onClick={() => {}}
						>
							{" "}
							Register
						</Button>
					</Box>
				</form>
			</Box>
		</>
	);
};

export default Register;
