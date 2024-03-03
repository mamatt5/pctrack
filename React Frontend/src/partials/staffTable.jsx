import * as React from "react";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import TablePagination from "@mui/material/TablePagination";
import { Box, IconButton, Typography } from "@mui/material";
import SearchBar from "./SearchBar";
import EditIcon from "@mui/icons-material/Edit";
import PermissonModal from "./ManagePermission";
import EditOffIcon from "@mui/icons-material/EditOff";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
	[`&.${tableCellClasses.head}`]: {
		backgroundColor: "#f3f7f9",
		color: "black",
	},
	[`&.${tableCellClasses.body}`]: {
		fontSize: 14,
		// borderBottom: "none",
		color: "#3b3b3b",
		// padding:"0.8rem"
	},
	width: "10rem",
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
	// "&:nth-of-type(odd)": {
	// 	backgroundColor:"#f9fafb",
	// },
	// hide last border
	"&:last-child td, &:last-child th": {
		border: 0,
	},
	// backgroundColor: "white", // Set background color to white
	// "&:last-child td, &:last-child th": {
	// 	border: 0, // Remove border for last child
	// },
	"&:hover": {
		backgroundColor: "#f3f7f9",
	},
}));

// checks if a staff is able to manage another staff
// i.e. are premissions high enough for that location.
// first filter out the location.
// business can only do stuff in their location....
const hasPrecedence = (logginedInStaff, toBeManagedStaff) => {
	console.log(logginedInStaff);
	console.log(toBeManagedStaff);

	// first filters bu location, then filters by precednece.
	const currStaffHasPredence = logginedInStaff.filter((staff) => {
		return (
			console.log(staff.adminLevel.precedence),
			staff.location.locationId === toBeManagedStaff.location.locationId &&
				staff.adminLevel.precedence < toBeManagedStaff.adminLevel.precedence
		);
	});
	// if length is 0 then no precednece sinc eno matches were found
	return currStaffHasPredence.length !== 0;
};
/**
 * PAGINATION
 * SEARCH BAR
 * FILTER
 * SORT
 *
 * @param {*} param0
 * @returns
 */

export default function CustomizedTables({
	array,
	currStaff,
	setQuery,
	staffCount,
	adminLevels = { adminLevels },
	onChangePage,
}) {
	const [page, setPage] = React.useState(0);
	const [rowsPerPage, setRowsPerPage] = React.useState(10);
	// the modal for editing permissions, its a collection of all the modals
	// for each user
	const [openModals, setOpenModals] = React.useState({});

	const handleChangePage = (event, newPage) => {
		setPage(newPage);
		onChangePage(newPage, rowsPerPage);
	};

	const handleChangeRowsPerPage = (event) => {
		setRowsPerPage(parseInt(event.target.value, 10));
		setPage(0); // Reset the page to the first page when rows per page changes
		onChangePage(0, parseInt(event.target.value, 10));
	};

	// set the modal were opening correpsonding to id as true.
	const handleOpenModal = (id) => {
		setOpenModals((otherModals) => ({
			...otherModals,
			[id]: true,
		}));
	};

	const handleCloseModal = (id) => {
		setOpenModals((otherModals) => ({
			...otherModals,
			[id]: false,
		}));
	};

	// when new query reset page to 0.
	const handleSetQuery = (newQuery) => {
		setPage(0); // Reset the page to 0 when a new query is set
		setQuery(newQuery);
	};

	// calcaultes the num of empty rows on a page to avoid the layout screwing up
	const emptyRows = rowsPerPage - Math.min(rowsPerPage, staffCount - page * rowsPerPage);

	return (
		<>
			<TableContainer
				component={Paper}
				sx={{
					height: "62vh",
					borderBottom: "1px solid black",
					borderBottomLeftRadius: 0,
					borderBottomRightRadius: 0,
				}}
				elevation="0"
			>
				<Table sx={{ minWidth: 700 }} aria-label="customized table" stickyHeader>
					{/* HEAD */}
					<TableHead>
						<TableRow>
							<StyledTableCell align="left" sx={{ fontWeight: "bold", paddingLeft: "2rem" }}>
								Username
							</StyledTableCell>
							<StyledTableCell align="left" sx={{ fontWeight: "bold" }}>
								Office
							</StyledTableCell>
							<StyledTableCell align="left" sx={{ fontWeight: "bold" }}>
								Joined
							</StyledTableCell>
							<StyledTableCell align="left" sx={{ fontWeight: "bold" }}>
								Perms
							</StyledTableCell>
							<StyledTableCell align="right" sx={{ fontWeight: "bold" }}>
								Action
							</StyledTableCell>
						</TableRow>
					</TableHead>
					{/* BODY */}
					<TableBody>
						{/* actual rows */}
						{emptyRows === rowsPerPage ? (
							<TableRow style={{ height: 53 * 10 }}>
								<TableCell colSpan={6} align="center">
									<Typography variant="h6" sx={{ color: "gray", fontStyle: "italic" }}>
										User not found
									</Typography>
								</TableCell>
							</TableRow>
						) : (
							<>
								{array.map((row) => (
									<StyledTableRow key={row.name}>
										<StyledTableCell align="left" sx={{ paddingLeft: "2rem" }}>
											{row.user.username}
										</StyledTableCell>

										<StyledTableCell align="left">{row.location.city}</StyledTableCell>
										<StyledTableCell align="left">{row.user.joinDate}</StyledTableCell>
										<StyledTableCell align="left">{row.adminLevel.name}</StyledTableCell>
										<StyledTableCell align="right" component="th" scope="row">
											{row.name}

											{hasPrecedence(currStaff, row) ? (
												<>
													<IconButton>
														<EditIcon
															onClick={() => handleOpenModal(row.staffId)}
															sx={{ color: "#a1c3d3", "&:hover": { cursor: "pointer" } }}
														/>
													</IconButton>
													{/* setOpenModal={(isOpen) => isOpen ? handleOpenModal(row.id) :
                        handleCloseModal(row.id)}: This passes a function setOpenModal
                        to the PermissionModal component. When setOpenModal(true) is called
                        inside PermissionModal, it calls handleOpenModal(row.id) to open the modal
                         for the corresponding row.id. When setOpenModal(false) is called inside
                         PermissionModal, it calls handleCloseModal(row.id) to close the modal
                         for the corresponding row.id. */}
													<PermissonModal
														openModal={!!openModals[row.staffId]}
														setOpenModal={(isOpen) =>
															isOpen ? handleOpenModal(row.staffId) : handleCloseModal(row.staffId)
														}
														staff={row}
														adminLevels={adminLevels}
														admin={currStaff}
													/>
												</>
											) : (
												<IconButton disableRipple>
													<EditOffIcon sx={{ color: "#ecf0f2" }} />
												</IconButton>
											)}
										</StyledTableCell>
									</StyledTableRow>
								))}
								{/* empty rows  */}
								{emptyRows > 0 && page === 1 && (
									<TableRow style={{ height: 53 * emptyRows }}>
										<TableCell colSpan={6} />
									</TableRow>
								)}
							</>
						)}
					</TableBody>
				</Table>
			</TableContainer>

			{/* footer  */}
			<Box sx={{ display: "flex", justifyContent: "space-between", padding: "0.5rem 0 0.5rem 0" }}>
				<SearchBar placeholder={"Search User"} setQuery={handleSetQuery} />
				{/* PAGINATION  */}
				<TablePagination
					rowsPerPageOptions={[10, 25, 100]}
					component="div"
					count={staffCount}
					rowsPerPage={rowsPerPage}
					page={page}
					onPageChange={handleChangePage}
					onRowsPerPageChange={handleChangeRowsPerPage}
					labelRowsPerPage={
						<Typography variant="body2" sx={{ color: "#8a989f" }}>
							{" "}
							{/* Change color as needed */}
							Rows per page:
						</Typography>
					}
				/>
			</Box>
		</>
	);
}
