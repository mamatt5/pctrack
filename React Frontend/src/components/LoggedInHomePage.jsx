import React from "react";
import NavBar from "../partials/NavBar";
import callApi from "../api/callApi";
import { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { SearchComputerPage } from "./SearchComputerPage";
import { SearchRoomPage } from "./SearchRoomPage";
import { SearchSoftwarePage } from "./SearchSoftwarePage";
import { useParams } from "react-router-dom";
import Admin from "./Admin";
import ViewComputersInRoomPage from "./ViewComputersInRoomPage";

import UpdateDetailsPage from "./UpdateDetailsPage";

const LoggedInHomePage = () => {
	// check if user if an admin
	// checking if is admin.
	const { id } = useParams();
	const [admin, setAdmin] = useState(false);

	const checkAdmin = () => {
		const config = {
			method: "get",
			endpoint: `staff/${id}`,
		};
		callApi(checkAdminLevel, null, config);
	};

	const checkAdminLevel = (data) => {
		console.log(data);
		// dta == [] means not even a staff,
		// dta == null means its just a staff, not admin
		setAdmin(false);

		for (let i = 0; i < data.length; i++) {
			console.log(data[i].adminLevel);
			if (data[i].adminLevel !== null ) {
				console.log(data[i].adminlevel)
				setAdmin(true);
				break;
			}
		}
		console.log(admin)
	};

	useEffect(() => {
		console.log("checking");
		checkAdmin();
	}, []);

	console.log(admin);

	return (
		<>
			<Routes>
				<Route path="/searchroom" element={<SearchRoomPage />} />
				<Route path="/searchsoftware" element={<SearchSoftwarePage />} />
				<Route path="/searchcomputer" element={<SearchComputerPage />} />
				<Route path="/updatedetails" element={<UpdateDetailsPage />} />
				<Route path="/admin" element={<Admin />} />
				<Route path="/viewcomputerroom" element={<ViewComputersInRoomPage />} />
			</Routes>
			<NavBar admin={admin} />
		</>
	);
};

export default LoggedInHomePage;
