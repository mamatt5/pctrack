import { useState } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Import BrowserRouter as Router

//pages
import Login from "./components/Login";
import Admin from "./components/Admin";
import Home from "./components/Home";

function App() {


	return (
		<>
			<Router>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/login" element={<Login />} />
					<Route path="/admin" element={<Admin />} />

				</Routes>
        <Home/>
			</Router>

		</>
	);
}

export default App;
