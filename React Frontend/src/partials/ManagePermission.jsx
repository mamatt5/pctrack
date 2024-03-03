import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { Button, Divider, IconButton, Select, Typography } from "@mui/material";
import { Box } from "@mui/material";
import SelectSmall from "./CheckBoxDropDowns";
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";
import Config from "../configs.json";
import { MultipleSelect } from "./CheckBoxDropDowns";
import { getAdminsLevels } from "../components/Admin";
import { TextField } from "@mui/material";

const MAX_PRECEDENCE = Config.MAX_PRECEDENCE;
const MIN_PRECEDENCE = Config.MIN_PRECEDENCE;

const getRoomsAtLocation = (setRooms, locationId) => {
	const config = {
		method: "get",
		endpoint: `roomsByLocation/${locationId}`,
	};

	callApi(setRooms, null, config);
};

const deleteStaff = (funct, staffId) => {
	const config = {
		method: "delete",
		endpoint: `staff/${staffId}`,
	};

	callApi(funct, null, config);
};

const createStaff = (funct, id, locId, adminLevel) => {
	const config = {
		method: "delete",
		endpoint: "staff",
		data: {
			user: {
				userId: staffId,
			},
			location: {
				locationId: locId,
			},
			adminLevel: {
				id: adminLevel,
			},
		},
	};

	callApi(funct, null, config);
};

// 1. find the location were editing
// 2. find the permission we have at that location
// 3. return all adminlevels below that.

const getAssignableLevels = (admin, allAdminlevels, locationId) => {
	// console.log(admin);
	// console.log(allAdminlevels);
	// console.log(locationId);

	const adminLevel = admin.filter((staff) => {
		return staff.location.locationId === locationId;
	})[0].adminLevel.precedence;

	//note business (MAX PRECEDNECE ) is the only one that can assign
	//another user the same precedence role.
	let filteredAdminLevel = [];
	// console.log(adminLevel)
	if (adminLevel === MAX_PRECEDENCE) {
		filteredAdminLevel = allAdminlevels.filter((levels) => {
			return levels.precedence >= adminLevel;
		});
	} else {
		filteredAdminLevel = allAdminlevels.filter((levels) => {
			return levels.precedence > adminLevel;
		});
	}

	// console.log(filteredAdminLevel);
	return filteredAdminLevel.sort((a, b) => a.precedence - b.precedence);
};

/**
 * @staff staff is the user we are editing
 * @admin admin is the admin editing the user
 * @adminLevels is all the possible admin levels
 * @param {*}
 * @returns
 */

export const LocationsPermsModal = ({
	openModal,
	setOpenModal,
	staff,
	userlocations,
	assignableLocations,
	setChange,
}) => {
	// console.log(openModal);
	// console.log(staff);
	// console.log(adminLevels);

	return (
		<Modal
			open={openModal}
			onClose={() => setOpenModal(false)}
			closeAfterTransition
			aria-labelledby="edit perm modal"
			aria-describedby="edits perm"
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
						p: 7,
						width: "64vw",
						maxWidth: 700,
						height: "45rem",
					}}
				>
					{addLocation(staff, userlocations, assignableLocations, setOpenModal, setChange)}
				</Box>
			</Fade>
		</Modal>
	);
};

export const RegisterLocation = async (
	selectedOptions,
	staffId,
	adminlevel,
	setOpenModal,
	setChange
) => {
	console.log(staffId, adminlevel);
	try {
		const promises = selectedOptions.map((location) => {
			const config = {
				method: "post",
				endpoint: "staff",
				data: {
					user: {
						userId: staffId,
					},
					location: {
						locationId: location.locationId,
					},
					adminLevel: {
						id: adminlevel,
					},
				},
			};
			return callApi(() => {}, null, config);
		});

		await Promise.all(promises); // Wait for all API calls to finish FIRST!!!

		// After all API calls have finished
		setOpenModal(false);
		setChange((change) => !change);
	} catch (error) {
		null;
	}
};

const addLocation = (staff, userlocations, assignableLocations, setOpenModal, setChange) => {
	console.log(userlocations);
	const [adminLevels, setAdminLevels] = useState([]);
	const [selectedOptions, setSelectedOptions] = useState([]);
	let lowestAdminLevel = "";
	useEffect(() => {
		getAdminsLevels(setAdminLevels);
	}, []);

	if (adminLevels.length !== 0) {
		lowestAdminLevel = adminLevels.reduce((prev, curr) =>
			prev.precedence < curr.precedence ? curr : prev
		);
	}

	return (
		<>
			<Typography variant="h4" sx={{ marginBottom: "2rem" }}>
				Register User
			</Typography>
			<Divider />

			<Typography variant="h5" sx={{ marginY: "1rem" }}>
				Currently registered locations
			</Typography>
			<Typography variant="subtitle2" sx={{ color: "gray" }}>
				<b>
					{staff.firstName} {staff.lastName}
				</b>{" "}
				is currently registered in the following locations. To edit existing registration details,
				toggle on Staff and press the edit icon
			</Typography>
			<Typography>
				{userlocations.length === 0 ? (
					<Box sx={{ marginY: "2rem" }}>
						{MultipleSelect([], "name", "No Registered Locations", "", false)}
					</Box>
				) : (
					<Box sx={{ marginY: "2rem" }}>
						{MultipleSelect(userlocations, "name", "Registered Locations", "", false)}
					</Box>
				)}
			</Typography>

			<Typography variant="h5" sx={{ marginY: "1rem" }}>
				Register Locations
			</Typography>
			<Typography>
				{assignableLocations.length === 0 ? (
					<Box>None</Box>
				) : (
					// assignableLocations.map((location) => <Box>{location.name}</Box>)

					<Box sx={{ marginY: "2rem" }}>
						{MultipleSelect(
							assignableLocations,
							"city",
							"Locations",
							"Add Locations",
							true,
							setSelectedOptions
						)}
					</Box>
				)}
			</Typography>
			<Button
				variant="contained"
				disabled={selectedOptions.length === 0 ? true : false}
				sx={{ position: "absolute", bottom: 0, right: 0, margin: "2rem", width: "15%" }}
				onClick={() =>
					RegisterLocation(
						selectedOptions,
						staff.userId,
						lowestAdminLevel.id,
						setOpenModal,
						setChange
					)
				}
			>
				Submit
			</Button>
		</>
	);
};

const PermissonModal = ({ openModal, setOpenModal, staff, adminLevels, admin }) => {
	// console.log(openModal);
	// console.log(staff);
	// console.log(adminLevels);

	const assignableAdminLevels = getAssignableLevels(admin, adminLevels, staff.location.locationId);
	return (
		<Modal
			open={openModal}
			onClose={() => setOpenModal(false)}
			closeAfterTransition
			aria-labelledby="edit perm modal"
			aria-describedby="edits perm"
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
						p: 7,
						width: "64vw",
						maxWidth: 700,
						height: "45rem",
					}}
				>
					<ChangePerms staff={staff} adminLevels={assignableAdminLevels} />
				</Box>
			</Fade>
		</Modal>
	);
};

const ChangePerms = ({ staff, adminLevels }) => {
	const [permission, setPermission] = useState("");
	const [selectedRooms, setSelectedRoom] = useState([]);
	const [rooms, setRoom] = useState([]);

	useEffect(() => {
		getRoomsAtLocation(setRoom, staff.location.locationId);
	}, []);

	console.log(selectedRooms);
	return (
		<>
			<Typography variant="h4" sx={{ marginBottom: "2rem" }}>
				Manage User Permissions
			</Typography>
			<Divider />
			<Typography variant="h5" sx={{ marginTop: "1rem" }}>
				{staff.user.firstName} {staff.user.lastName}
			</Typography>
			<Typography sx={{ marginBottom: "1rem" }}>
				<i>{staff.location.name}</i>
			</Typography>
			<Typography variant="subtitle2" sx={{ color: "gray" }}>
				You are currently editing the permissions of
				<b>
					{" "}
					{staff.user.firstName} {staff.user.lastName}{" "}
				</b>
				registered in <b>{staff.location.city}</b>. If you wish to edit your own permissions, please
				notify another admin ( including demotions ).
			</Typography>

			<Typography variant="h6" sx={{ marginTop: "1rem" }}>
				Current Level{" "}
			</Typography>
			<TextField
				sx={{ width: "50%", marginTop: "1rem" }}
				InputProps={{
					readOnly: true,
				}}
				id="outlined-disabled"
				disabled
				label="Permissions"
				variant="filled"
				defaultValue={
					staff.adminLevel.precedence === 100 ? "No permissions" : `${staff.adminLevel.name} Admin`
				}
			/>

			<Typography variant="h6" sx={{ marginY: "1rem" }}>
				Change Permissions to{" "}
			</Typography>
			<Box sx={{ width: "50%" }}>
				<SelectSmall
					array={adminLevels}
					label={"name"}
					item={permission}
					setItem={setPermission}
					disabledKey={staff.adminLevel.precedence === 100 ? "" : staff.adminLevel.name}
				/>
			</Box>

			{permission === "Room" || staff.adminLevel.name === "Room" ? (
				<Box sx={{ width: "50%" }}>
					<Typography variant="h6" sx={{ marginY: "1rem" }}>
						Rooms:{" "}
					</Typography>
					{MultipleSelect(
						rooms,
						"name",
						staff.adminLevel.name === "Room" ? "Update Rooms" : "Add Rooms",
						"Room Name",
						true,
						setSelectedRoom
					)}
				</Box>
			) : null}

			<Button
				variant="contained"
				disabled={permission === "" ? true : false}
				sx={{ position: "absolute", bottom: 0, right: 0, margin: "2rem", width: "15%" }}
				onClick={() => {
					deleteStaff();
					createStaff();
				}}
			>
				Submit
			</Button>
		</>
	);
};

// for room admins, you need to select a room in that location.
const getRoomsAtLoc = () => {};

export default PermissonModal;
