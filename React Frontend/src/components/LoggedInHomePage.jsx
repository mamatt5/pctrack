import React from "react";
import NavBar from "../partials/NavBar";
import callApi from "../api/callApi";
import { useState, useEffect } from "react";

const LoggedInHomePage = () => {
	// check if user if an admin

	return (
		<>
			<div>Home Page</div>
			<NavBar />
		</>
	);
};

export default LoggedInHomePage;
