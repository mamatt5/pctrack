import AddLocationPage from "./addLocationPage";
import AddRoomPage from "./addRoomPage";
import { Box, Typography } from "@mui/material";
import callApi from "../api/callApi";
import { useState, useEffect } from "react";
import LocationComputerStatus from "../partials/LocationComputerStatus";

const ManageFacilities = ({ admin, currStaff, setRender}) => {
	console.log(admin);
	console.log(currStaff);
	const BusinessPerms = currStaff.filter((staff) => staff.adminLevel.name === "Business");
	const LocationPerms = currStaff.filter((staff) => staff.adminLevel.name === "Location");
	

    console.log(currStaff)
    console.log(BusinessPerms)

	return (
		<Box sx={{ paddingTop: "9rem" }}>
			{BusinessPerms.length !== 0 ? (
				<>

                    <LocationComputerStatus perms={BusinessPerms}/>
					<AddLocationPage admin={admin} setRender={setRender} />
					<AddRoomPage admin={admin} currStaff={currStaff} />
				</>
			) : BusinessPerms.length === 0 && LocationPerms.length !== 0 ? (
				<>
                  <LocationComputerStatus perms={LocationPerms}/>
					<AddRoomPage admin={admin} currStaff={currStaff}/>
				</>
			) : null}
		</Box>
	);
};

export default ManageFacilities;
