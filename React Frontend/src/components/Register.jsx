import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useState } from "react";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import { Typography } from "@mui/material";
import callApi from "../api/callApi";

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

const Register = ({ users, setOpenModal }) => {
	console.log(users);
	const [firstNameErr, setFirstNameErr] = useState("");
	const [lastNameErr, setLastNameErr] = useState("");
	const [passwordErr, setPasswordError] = useState("");
	const [formValid, setFormValid] = useState(false);

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

		// .. i guess we can only assume that all users are new?
		// 1. try to find the cobination starting with firstName.lastname
		// 2. find how many we find as num
		// 3. add the num to firstName.lastname(num + 1)
		// 4. assume its a new user?
		// for now... assume that every user must then be made into a staff
		// despite the redundancy in the dataloader.
		// as a result, register will create a new user, and then make then a staff.
		// confusing, need clarification w team

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
		console.log("hueh")
		callApi(createStaff, null, config);

	};

	// TO DO!!
	/**
	 *
	 *
	 *
	 * location id 1 for now.... got lazy getting all locations.
	 *
	 */
	const createStaff = (data) => {
		console.log("huseh")
		console.log(data);
		const config = {
			method: "post",
			endpoint: "staff",
			data: {
				user: {
					userId: data.userId,
				},
				location: {
					locationId: 1,
				},
				adminLevel: null,
			},
		};
		console.log("hi");
		callApi(() => {setOpenModal(false)}, null, config);
	};

	const checkInput = (field, value) => {
		setFirstNameErr("");
		setLastNameErr("");
		setPasswordError("");

		console.log(value, field);
		formData = { ...formData, [field]: value };
		console.log(formData);
		setFormValid(isFormValid());
	};

	// Checkbox State Handling
	const [checkedValues, setCheckedValues] = useState({
		APAC: false,
		HongKong: false,
		Singapore: false,
	});

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

	return (
		<>
			<Box sx={{ border: "1px solid black", borderRadius: "10px", width: "40vw", padding: "5%" }}>
				<Box>Register a user</Box>
				<form onSubmit={checkRegister} className="flexCol">
					<TextField
						name="firstName"
						label="Given Name"
						error={Boolean(firstNameErr)}
						helperText={firstNameErr ? firstNameErr : ""}
						onChange={(e) => checkInput("firstName", e.target.value)}
					/>
					<TextField
						name="lastName"
						label="Family Name"
						error={Boolean(lastNameErr)}
						helperText={lastNameErr ? lastNameErr : ""}
						onChange={(e) => checkInput("lastName", e.target.value)}
					/>
					<TextField
						name="password"
						label="Password"
						error={Boolean(passwordErr)}
						helperText={passwordErr ? passwordErr : ""}
						onChange={(e) => checkInput("password", e.target.value)}
					/>

					<Typography>Office</Typography>
					<Box className="flexRow" sx={{ alignItems: "center" }}>
						<FormGroup>
							<FormControlLabel
								control={
									<Checkbox checked={checkedValues.APAC} onChange={handleCheck} name="APAC" />
								}
								label="APAC"
							/>
							<FormControlLabel
								control={
									<Checkbox
										checked={checkedValues.HongKong}
										onChange={handleCheck}
										name="HongKong"
									/>
								}
								label="Hong Kong"
							/>
							<FormControlLabel
								control={
									<Checkbox
										checked={checkedValues.Singapore}
										onChange={handleCheck}
										name="Singapore"
									/>
								}
								label="Singapore"
							/>
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
