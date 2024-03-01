import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { Divider, Select, Typography } from "@mui/material";
import { Box } from "@mui/material";
import SelectSmall from "./CheckBoxDropDowns";
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";
import Config from "../configs.json";

const MAX_PRECEDENCE = Config.MAX_PRECEDENCE;
// 1. find the location were editing
// 2. find the permission we have at that location
// 3. return all adminlevels below that.

const getAssignableLevels = (admin, allAdminlevels, locationId) => {
	console.log(admin);
	console.log(allAdminlevels);
	console.log(locationId);

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

	console.log(filteredAdminLevel);
	return filteredAdminLevel.sort((a,b) => a.precedence - b.precedence);
};

/**
 * @staff staff is the user we are editing
 * @admin admin is the admin editing the user
 * @adminLevels is all the possible admin levels
 * @param {*}
 * @returns
 */
const PermissonModal = ({ openModal, setOpenModal, staff, adminLevels, admin }) => {
	console.log(openModal);
	console.log(staff);
	console.log(adminLevels);
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
					<FirstPage staff={staff} adminLevels={assignableAdminLevels} />

				</Box>
			</Fade>
		</Modal>
	);
};

const FirstPage = ({ staff, adminLevels }) => {
	return (
		<>
			<Typography variant="h4" sx={{ marginBottom: "2rem" }}>
				Manage User Permissions
			</Typography>
			<Divider />
			<Typography variant="h5">
				{staff.user.firstName} {staff.user.lastName}
			</Typography>
			<Typography>{staff.location.city}</Typography>

			<Typography>
				Current Level:{" "}
				{staff.adminLevel.precedence === 100 ? "No permissions" : `${staff.adminLevel.name} Admin`}
			</Typography>
			<SelectSmall array={adminLevels} label={"name"} />
		</>
	);
};

export default PermissonModal;
