import AddLocationPage from "./addLocationPage"
import AddRoomPage from "./addRoomPage"

const ManageFacilities = ({admin, currStaff}) => {
    console.log(admin)
    console.log(currStaff)
    return (<>
    <AddLocationPage admin={admin}/>
    <AddRoomPage admin={admin} currStaff={currStaff}/>

    </>)
}

export default ManageFacilities