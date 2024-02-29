import React from "react";
import { styled, useTheme } from "@mui/material/styles";
import { Box } from "@mui/material";
import MuiDrawer from "@mui/material/Drawer";
import MuiAppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import List from "@mui/material/List";
import CssBaseline from "@mui/material/CssBaseline";
import Typography from "@mui/material/Typography";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Tooltip from '@mui/material/Tooltip';


import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState, useEffect, useCallback } from "react";

import callApi from "../api/callApi";
import ComputerIcon from "@mui/icons-material/Computer";
import SearchIcon from "@mui/icons-material/Search";
import ApiIcon from "@mui/icons-material/Api";
import ChairIcon from "@mui/icons-material/Chair";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import GridViewOutlinedIcon from "@mui/icons-material/GridViewOutlined";
import DataSaverOffOutlinedIcon from "@mui/icons-material/DataSaverOffOutlined";
import UpgradeOutlinedIcon from "@mui/icons-material/UpgradeOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import MeetingRoomIcon from '@mui/icons-material/MeetingRoom';

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
  const {admin} = props
  const { id } = useParams();
	const theme = useTheme();
	const [open, setOpen] = useState(false);
	const navigate = useNavigate();

	const [openExpenseCreator, setOpenExpenseCreator] = useState(false);

	const handleOpen = () => setOpenExpenseCreator(true);
	const handleClose = () => setOpenExpenseCreator(false);

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

		} else if (page === "Log Out") {
			localStorage.removeItem("token");
			navigate("/");
		}
  }




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
					{["Manage Users"].map((text, index) => (
						<ListItem key={text} disablePadding sx={{ display: "block" }}>
						<Tooltip title={text} placement="right">
							<ListItemButton
								sx={{
									minHeight: 48,
									justifyContent: open ? "initial" : "center",
									px: 2.5,
								}}
								onClickCapture={() => handleIconNav("Manage Users", id)}
							>
								<ListItemIcon
									sx={{
										minWidth: 0,
										mr: open ? 3 : "auto",
										justifyContent: "center",
									}}
								>
									{index === 0 &&  <AdminPanelSettingsIcon />}

								</ListItemIcon>
								<ListItemText primary={text} sx={{ opacity: open ? 1 : 0 }} />
							</ListItemButton>
							</Tooltip>
						</ListItem>
						))}
					</>
				) : null}

        {/* staff + admin stuff */}
				<List sx={{padding:0}}>
					{["Search Rooms", "Search Computer", "Search Software"].map((text, index) => (
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
									{index === 0 &&  <MeetingRoomIcon />}
									{index === 1 && <ComputerIcon />}
									{index === 2 && <ApiIcon />}
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
					{["Update Details", "Log Out"].map((text, index) => (
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
									{index === 0 && <UpgradeOutlinedIcon />}
									{index === 1 && <LogoutOutlinedIcon />}
								</ListItemIcon>
								<ListItemText primary={text} sx={{ opacity: open ? 1 : 0 }} />
							</ListItemButton>
							</Tooltip>
						</ListItem>
					))}
				</List>
			</Drawer>
			<Box component="main" sx={{ flexGrow: 1, p: 3 }}>
			</Box>
		</Box>
	);
}
