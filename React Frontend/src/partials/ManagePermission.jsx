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
import { GetAdminsLevels } from "../components/Admin";
import { TextField } from "@mui/material";
import { subtractArrays } from "../helpers/helperFunctions";
import WarningIcon from "@mui/icons-material/Warning";


//#####
// TODO:
// refactor the modals if bothered. they can all be reused
//#####

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
	console.log(staffId);
	const config = {
		method: "delete",
		endpoint: `staff/${staffId}`,
	};

	callApi(funct, null, config);
};

// for all staff but room admin
export const CreateStaff = (funct, userId, locId, adminLevel) => {
	console.log(adminLevel, "admin level!!!! ");
	const config = {
		method: "post",
		endpoint: "staff",
		data: {
			user: {
				userId: userId,
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

// for all staff
const createRoomAdmin = (funct, userId, locId, adminLevel, roomsArray) => {
	const config = {
		method: "post",
		endpoint: "createRoomAdmin",
		data: {
			user: {
				userId: userId,
			},
			location: {
				locationId: locId,
			},
			adminLevel: {
				id: adminLevel,
			},
			roomAssigned: roomsArray,
		},
	};

	callApi(funct, null, config);
};

// for all staff
const editRoomAdmin = (funct, userId, staffId, locId, adminLevel, roomsArray) => {
	console.log(staffId, locId, adminLevel, roomsArray);
	const config = {
		method: "put",
		endpoint: `editRoomAdmin/${staffId}`,
		data: {
			staffId: staffId,
			user: { userId: userId },
			location: {
				locationId: locId,
			},
			adminLevel: {
				id: adminLevel,
			},
			roomAssigned: roomsArray,
		},
	};

	callApi(funct, null, config);
};

const getRoomsOfAdmin = (funct, staffId) => {
	console.log(staffId);
	const config = {
		method: "get",
		endpoint: `getRoomAdminRooms/${staffId}`,
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
		GetAdminsLevels(setAdminLevels);
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

const PermissonModal = ({ openModal, setOpenModal, staff, adminLevels, admin, setChange }) => {
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
					<ChangePerms
						staff={staff}
						adminLevels={assignableAdminLevels}
						setOpenModal={setOpenModal}
						setChange={setChange}
					/>
				</Box>
			</Fade>
		</Modal>
	);
};

// the staff is NOT us, is the staff were edting!!!
const ChangePerms = ({ staff, adminLevels, setOpenModal, setChange }) => {
	const [permission, setPermission] = useState("");
	const [adminRooms, setAdminRooms] = useState([]);
	const [selectedRooms, setSelectedRoom] = useState(adminRooms);
	const [rooms, setRoom] = useState([]);

	let newAdminId = "";

	// disable the submite button if the different ice the same as before
	const roomSelectedDifference =
		selectedRooms.length > adminRooms.length
			? subtractArrays(selectedRooms, adminRooms)
			: subtractArrays(adminRooms, selectedRooms);

	useEffect(() => {
		getRoomsAtLocation(setRoom, staff.location.locationId);
		getRoomsOfAdmin(setAdminRooms, staff.staffId);
	}, []);

	useEffect(() => {
		setSelectedRoom(adminRooms);
	}, [adminRooms]);

	console.log(adminLevels);
	console.log(adminLevels.find((role) => role.name === "Business")?.id);
	console.log(adminRooms);
	console.log(roomSelectedDifference);

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
				sx={{ width: "80%", marginTop: "1rem" }}
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
			<Box sx={{ width: "80%" }}>
				<SelectSmall
					array={adminLevels}
					label={"name"}
					item={permission}
					setItem={setPermission}
					disabledKey={
						staff.adminLevel.precedence === 100
							? ""
							: staff.adminLevel.name === "Room"
							? "dobtnarch"
							: staff.adminLevel.name
					}
				/>
			</Box>

			{/*  if new perms is a room or both level are stuck on room show the room select */}
			{permission === "Room" || (staff.adminLevel.name === "Room" && permission === "Room") ? (
				<Box sx={{ width: "80%" }}>
					<Typography variant="h6" sx={{ marginY: "1rem" }}>
						Rooms:{" "}
					</Typography>
					{MultipleSelect(
						rooms,
						"name",
						staff.adminLevel.name === "Room" ? "Update Rooms" : "Add Rooms",
						"Room Name",
						true,
						setSelectedRoom,
						adminRooms
					)}
				</Box>
			) : null}

			<Button
				variant="contained"
				// disabled if permissions == room and new permissions == room and the room isnt changed
				disabled={
					permission === "" ||
					(staff.adminLevel.name === "Room" &&
						permission === "Room" &&
						roomSelectedDifference.length === 0)
				}
				sx={{ position: "absolute", bottom: 0, right: 0, margin: "2rem", width: "15%" }}
				onClick={() => {
					if (permission === "Room" && staff.adminLevel.name === "Room") {
						editRoomAdmin(
							() => {
								setOpenModal(false);
								setChange((i) => !i);
							},
							staff.user.userId,
							staff.staffId,
							staff.location.locationId,
							staff.adminLevel.id,
							selectedRooms
						);
					} else if (permission === "none") {
						console.log("HIHIHHIHI");
						deleteStaff(() => {}, staff.staffId);
						createStaff(
							() => {
								setOpenModal(false);
								setChange((i) => !i);
							},
							staff.user.userId,
							staff.location.locationId,
							adminLevels.find((role) => role.name === "")?.id
						);
					} else if (permission === "Room") {
						console.log("OH NONOONO");
						deleteStaff(() => {}, staff.staffId);

						createRoomAdmin(
							() => {
								setOpenModal(false);
								setChange((i) => !i);
							},
							staff.user.userId,
							staff.location.locationId,
							adminLevels.find((role) => role.name === permission)?.id,
							selectedRooms
						);
					} else {
						console.log(permission);
						console.log("YUIKESSSSS");
						deleteStaff(() => {}, staff.staffId);
						createStaff(
							() => {
								setOpenModal(false);
								setChange((i) => !i);
							},
							staff.user.userId,
							staff.location.locationId,
							adminLevels.find((role) => role.name === permission)?.id
						);
					}
				}}
			>
				Submit
			</Button>
		</>
	);
};

// for room admins, you need to select a room in that location.
export const DeleteModal = ({ openModal, setOpenModal, staff, setChange }) => {
	console.log(staff);
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
					}}
				>
					{DeleteStaff(staff, setOpenModal, setChange)}
				</Box>
			</Fade>
		</Modal>
	);
};

const DeleteStaff = (staff, setOpenModal, setChange) => {
	console.log(staff);
	return (
		<Box className="centerHorizonal" sx={{}}>
			<IconButton sx={{ color: "#fb8723", fontSize: "30%" }}>
				<WarningIcon />
			</IconButton>
			<Typography variant="h5" sx={{ marginY: "1rem" }}>
				Delete Staff Member
			</Typography>
			<Typography sx={{ marginBottom: "1rem" }}>
				{staff.user.firstName} <i style={{ color: "gray" }}>{staff.user.email} </i>
				{staff.location.name}
			</Typography>
			<Typography variant="subtitle2" sx={{ color: "gray", marginY: "20px" }}>
				Are you sure you want to delete this Staff member? This action cannot be reversed.
			</Typography>

			<Box sx={{ marginY: "1rem" }}>
				<Button
					variant="contained"
					color="primary"
					disableElevation
					sx={{ marginX: "10px", borderRadius: "20px" }}
					onClick={() => {
						deleteStaff(() => {
							setOpenModal(false), setChange((c) => !c);
						}, staff.staffId);
					}}
				>
					Confirm
				</Button>
				<Button
					variant="contained"
					color="inherit"
					disableElevation
					sx={{ marginX: "10px", borderRadius: "20px" }}
					onClick={() => setOpenModal(false)}
				>
					Cancel
				</Button>
			</Box>
		</Box>
	);
};




export default PermissonModal;
