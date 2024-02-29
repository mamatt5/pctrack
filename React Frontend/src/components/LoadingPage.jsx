import Box from "@mui/material/Box";
import CircularProgress from "@mui/material/CircularProgress";

const Loading = () => {
	return (
		<>
			<div className="centerHorizonal">
				<Box
					sx={{
						display: "flex",
						alignItems: "center",
						flexDirection: "column",
						justifyContent: "center",
						height: "100vh",
					}}
				>
					<Box>
						{" "}
						<CircularProgress />
					</Box>
					<Box>Loading</Box>
				</Box>
			</div>
		</>
	);
};

export default Loading