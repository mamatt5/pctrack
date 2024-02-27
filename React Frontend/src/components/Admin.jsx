import Register from "./Register";

import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import callApi from "../api/callApi";
import NavBar from "../partials/NavBar";

const AdminDrawer = () => {

    const getUsers = () => {

    }


	return (
		<>
			<Box className="flexCol" sx={{ borderRight: "1px solid black", height: "100vh", width:"25%" }}>
				<Button>Users</Button>
				<Button>Admin</Button>

			</Box>
            <NavBar/>
		</>
	);
};
const Admin = () => {
	return (
		<>
			<Box className="flexRow">
				<AdminDrawer />

				{/* <Home/> */}
			</Box>
		</>
	);
};

export default Admin;
