import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { Divider, Select, Typography } from "@mui/material";
import { Box } from "@mui/material";
import SelectSmall from "./CheckBoxDropDowns";
import { useState } from "react";
import { useEffect } from "react";
import callApi from "../api/callApi";

const getAdminsLevels = (setAdminLevels) => {
	// Functionality to get staff on pagination
	const config = {
		method: "get",
		endpoint: "adminLevels",
	};

	callApi(setAdminLevels, null, config);
};

const PermissonModal = ({ openModal, setOpenModal, staff }) => {
	console.log(openModal);
	console.log(staff);

    const [adminLevels, setAdminLevels] = useState([])

    useEffect(() => {
        getAdminsLevels(setAdminLevels)
    }, [])

    console.log(adminLevels)
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
						height: "64vw",
					}}
				>
					<EditBox staff={staff} adminLevels={adminLevels} />
				</Box>
			</Fade>
		</Modal>
	);
};

const EditBox = ({ staff, adminLevels }) => {

	return (
		<>
			<Typography variant="h4" sx={{ marginBottom: "2rem" }}>
				Manage User Permissions
			</Typography>
            <Divider/>
			<Typography variant="h5">
				{staff.user.firstName} {staff.user.lastName}
			</Typography>
			<Typography>{staff.location.city}</Typography>

			<Typography>
				Current Level: {staff.adminLevel === null ? "None" : `${staff.adminLevel} Admin`}
			</Typography>
            <SelectSmall array={adminLevels} label="name"/>
		</>
	);
};

export default PermissonModal;
