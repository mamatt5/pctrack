import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { Typography } from "@mui/material";
import { Box } from "@mui/material";

const PermissonModal =  ({openModal, setOpenModal, staff}) => {
    console.log(openModal)
    console.log(staff)
	return (
		<>
			<Modal
				open={openModal}
				onClose={() => setOpenModal(false)}
				closeAfterTransition
				aria-labelledby="edit perm modal"
				aria-describedby="edits perm"
				sx={{
					"& .MuiBackdrop-root": {
						backgroundColor: "rgba(0, 0, 0, 0.2)", // Adjust opacity here (0.5 for 50% darkness)
					},
				}}
			>
				<Fade in={openModal}>
                    <Box
						sx={{
							position: "absolute",
							top: "50%",
							left: "50%",
							transform: "translate(-50%, -50%)",
							backgroundColor: "white",
							borderRadius: 3,
							p: 7,
                            width:"64vw",
                            height:"64vw"
						}}>
                    <EditBox staff={staff}/>
                    </Box>

				</Fade>
			</Modal>
		</>
	);
};

const EditBox = ({staff}) => {
    return (<>

<Typography variant="h4" sx={{marginBottom: "2rem"}}>
        Manage User Permissions
    </Typography>
    <Typography variant="h5">
        {staff.user.firstName}  {staff.user.lastName}
    </Typography>
    <Typography>
        {staff.location.city}
    </Typography>

    <Typography>
        Current Level:  {staff.adminLevel === null ? "None" : `${staff.adminLevel} Admin`}
    </Typography>


    </>)

}

export default PermissonModal