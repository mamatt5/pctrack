import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ConstructionIcon from '@mui/icons-material/Construction';
import MenuIcon from "@mui/icons-material/Menu";
import ReportIcon from '@mui/icons-material/Report';
import { Box } from "@mui/material";
import MuiAppBar from "@mui/material/AppBar";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import MuiDrawer from "@mui/material/Drawer";
import IconButton from "@mui/material/IconButton";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Toolbar from "@mui/material/Toolbar";
import Tooltip from "@mui/material/Tooltip";
import Typography from "@mui/material/Typography";
import { styled, useTheme } from "@mui/material/styles";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { HelpCenter } from "@mui/icons-material";
import AdminPanelSettingsIcon from "@mui/icons-material/AdminPanelSettings";
import ApiIcon from "@mui/icons-material/Api";
import ComputerIcon from "@mui/icons-material/Computer";
import HomeIcon from '@mui/icons-material/Home';
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import MeetingRoomIcon from "@mui/icons-material/MeetingRoom";
import { CheckAdmin } from "../components/LoggedInHomePage";


const drawerWidth = 240;

const openedMixin = (theme) => ({
	width: drawerWidth,
	transition: theme.transitions.create("width", {
		easing: theme.transitions.easing.sharp,
		duration: theme.transitions.duration.enteringScreen,
	}),
	overflowX: "hidden",
});

const closedMixin = (theme) => ({
	transition: theme.transitions.create("width", {
		easing: theme.transitions.easing.sharp,
		duration: theme.transitions.duration.leavingScreen,
	}),
	overflowX: "hidden",
	width: `calc(${theme.spacing(7)} + 1px)`,
	[theme.breakpoints.up("sm")]: {
		width: `calc(${theme.spacing(8)} + 1px)`,
	},
});

const DrawerHeader = styled("div")(({ theme }) => ({
	display: "flex",
	alignItems: "center",
	justifyContent: "flex-end",
	padding: theme.spacing(0, 1),
	// necessary for content to be below app bar
	...theme.mixins.toolbar,
}));

const AppBar = styled(MuiAppBar, {
	shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
	zIndex: theme.zIndex.drawer + 1,
	transition: theme.transitions.create(["width", "margin"], {
		easing: theme.transitions.easing.sharp,
		duration: theme.transitions.duration.leavingScreen,
	}),
	...(open && {
		marginLeft: drawerWidth,
		width: `calc(100% - ${drawerWidth}px)`,
		transition: theme.transitions.create(["width", "margin"], {
			easing: theme.transitions.easing.sharp,
			duration: theme.transitions.duration.enteringScreen,
		}),
	}),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== "open" })(
	({ theme, open }) => ({
		width: drawerWidth,
		flexShrink: 0,
		whiteSpace: "nowrap",
		boxSizing: "border-box",
		...(open && {
			...openedMixin(theme),
			"& .MuiDrawer-paper": openedMixin(theme),
		}),
		...(!open && {
			...closedMixin(theme),
			"& .MuiDrawer-paper": closedMixin(theme),
		}),
	})
);

export default function NavBar(props) {

	const [admin, setAdmin] = useState(false);
	const [staff, setStaff] = useState([]);
	const { id } = useParams();
	const theme = useTheme();
	const [open, setOpen] = useState(false);
	const navigate = useNavigate();

	const [businessAdmin, setBusinessAdmin] = useState(false);
	const [locationAdmin, setLocationAdmin] = useState(false);
	const [roomAdmin, setRoomAdmin] = useState(false);





	useEffect(() => {

		CheckAdmin(setAdmin, setStaff, id);

		staff.forEach(staffMember => {

			let precedence = staffMember.adminLevel.precedence;


			if (precedence === 1) {
				setBusinessAdmin(true);
			} else if (precedence === 2) {
				setLocationAdmin(true);
			} else if (precedence === 3) {
				setRoomAdmin(true);
			}
		});
	}, [props]);


	const handleDrawerOpen = () => {
		setOpen(true);
	};

	const handleDrawerClose = () => {
		setOpen(false);
	};

	const handleIconNav = (page, id) => {
		if (page === "Search Rooms") {
			navigate(`/home/${id}/searchroom`);
		} else if (page === "Manage Users") {
			navigate(`/home/${id}/admin`);
		} else if (page === "Search Software") {
			navigate(`/home/${id}/searchsoftware`);
		} else if (page === "Search Computer") {
			navigate(`/home/${id}/searchcomputer`);
		} else if (page === "Search Rooms") {
			navigate(`/home/${id}/searchroom`);
		} else if (page === "Update Details") {
			navigate(`/home/${id}/searchcomputer`);
		}else if (page === "View Reports"){
			navigate(`/home/${id}/reports`)
		}else if (page === "Help") {
			navigate(`/home/${id}/help`);
		} else if (page === "Manage Facilities") {
			navigate(`/home/${id}/ManageFacilities`);
		} else if (page === "Log Out") {
			localStorage.removeItem("token");
			navigate("/");
		}
	};

	return (
		<Box sx={{ display: "flex" }}>
			<CssBaseline />

			<AppBar position="fixed" open={open} elevation={0}>
				<Toolbar>

						<IconButton
							color="inherit"
							aria-label="open drawer"
							onClick={handleDrawerOpen}
							edge="start"
							sx={{
								marginRight: 5,
								...(open && { display: "none" }),
							}}
						>
							<MenuIcon />
						</IconButton>
						<Typography variant="h6" noWrap component="div">
							PC Track
						</Typography>

						<IconButton color="inherit" sx={{ marginLeft: 'auto' }} onClick={()=>{navigate(`/home/${id}`);}}>
					<HomeIcon />
					</IconButton>
				</Toolbar>
			</AppBar>
			<Drawer variant="permanent" open={open}>
				<DrawerHeader>
					<IconButton onClick={handleDrawerClose}>
						{theme.direction === "rtl" ? <ChevronRightIcon /> : <ChevronLeftIcon />}
					</IconButton>
				</DrawerHeader>
				<Divider />
				{/* admin stuff  */}
				{admin ? (
					<>
						{["Manage Users", "Manage Facilities"].map((text, index) => (

							<ListItem key={text} disablePadding sx={{ display: "block" }}>
								<Tooltip title={text} placement="right">
									{console.log(businessAdmin)}
									<ListItemButton

										disabled={
											// index === 0 ? !businessAdmin :
											index === 1 ? !(businessAdmin || locationAdmin)  :
											false
										}
										sx={{
											minHeight: 48,
											justifyContent: open ? "initial" : "center",
											px: 2.5,
										}}
										onClickCapture={() => handleIconNav(text, id)}
									>
										<ListItemIcon
											sx={{
												minWidth: 0,
												mr: open ? 3 : "auto",
												justifyContent: "center",


											}}
										>
											{index === 0 && <AdminPanelSettingsIcon/>}
											{index === 1 && <ConstructionIcon />}
										</ListItemIcon>
										<ListItemText primary={text} sx={{ opacity: open ? 1 : 0 }} />
									</ListItemButton>
								</Tooltip>
							</ListItem>
						))}
					</>
				) : null}

				{/* staff + admin stuff */}
				<List sx={{ padding: 0 }}>
					{["Search Rooms", "Search Computer", "Search Software","View Reports"].map((text, index) => (
						<ListItem key={text} disablePadding sx={{ display: "block" }}>
							<Tooltip title={text} placement="right">
								<ListItemButton
									sx={{
										minHeight: 48,
										justifyContent: open ? "initial" : "center",
										px: 2.5,
									}}
									onClickCapture={() => handleIconNav(text, id)}
								>
									<ListItemIcon
										sx={{
											minWidth: 0,
											mr: open ? 3 : "auto",
											justifyContent: "center",
										}}
									>
										{index === 0 && <MeetingRoomIcon />}
										{index === 1 && <ComputerIcon />}
										{index === 2 && <ApiIcon />}
										{index === 3 && <ReportIcon/>}
									</ListItemIcon>
									<ListItemText primary={text} sx={{ opacity: open ? 1 : 0 }} />
								</ListItemButton>
							</Tooltip>
						</ListItem>
					))}
				</List>

				<Divider />

				{/* logging out and updating deets */}

				<List>
					{["Help", "Log Out"].map((text, index) => (
						<ListItem key={text} disablePadding sx={{ display: "block" }}>
							<Tooltip title={text} placement="right">
								<ListItemButton
									sx={{
										minHeight: 48,
										justifyContent: open ? "initial" : "center",
										px: 2.5,
									}}
									onClickCapture={() => handleIconNav(text, id)}
								>
									<ListItemIcon
										sx={{
											minWidth: 0,
											mr: open ? 3 : "auto",
											justifyContent: "center",
										}}
									>

										{index === 0 && <HelpCenter />}
										{index === 1 && <LogoutOutlinedIcon />}
									</ListItemIcon>
									<ListItemText primary={text} sx={{ opacity: open ? 1 : 0 }} />
								</ListItemButton>
							</Tooltip>
						</ListItem>
					))}
				</List>
			</Drawer>
			<Box component="main" sx={{ flexGrow: 1, p: 3 }}></Box>
		</Box>
	);
}
