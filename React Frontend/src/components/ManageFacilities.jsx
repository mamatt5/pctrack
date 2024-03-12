import { Box } from "@mui/material";
import LocationComputerStatus from "../partials/LocationComputerStatus";
import AddLocationPage from "./addLocationPage";
import AddRoomPage from "./addRoomPage";


/**
 * Functional component for viewing computers in a room
 * Allows users to view an interact with computers in a room
 * @returns - Displays this ViewComputersInRoomPage
 */
const ManageFacilities = ({ admin, currStaff, setRender }) => {

	const BusinessPerms = currStaff.filter((staff) => staff.adminLevel.name === "Business");
	const LocationPerms = currStaff.filter((staff) => staff.adminLevel.name === "Location");
	

	return (
		<Box sx={{ paddingTop: "9rem" }}>
			{BusinessPerms.length !== 0 ? (
				<>

					<LocationComputerStatus perms={BusinessPerms} />
					<AddLocationPage admin={admin} setRender={setRender} />
					<AddRoomPage admin={admin} currStaff={currStaff} />
				</>
			) : BusinessPerms.length === 0 && LocationPerms.length !== 0 ? (
				<>
					<LocationComputerStatus perms={LocationPerms} />
					<AddRoomPage admin={admin} currStaff={currStaff} />
				</>
			) : null}
		</Box>
	);
};

export default ManageFacilities;
