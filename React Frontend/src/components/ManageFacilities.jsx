import AddLocationPage from "./addLocationPage";
import AddRoomPage from "./addRoomPage";

const ManageFacilities = ({ admin, currStaff }) => {
  console.log(admin);
  console.log(currStaff);
  const BusinessPerms = currStaff.filter((staff) => staff.adminLevel.name === "Business");
  const LocationPerms = currStaff.filter((staff) => staff.adminLevel.name === "Location");

  return (
    <>
      {BusinessPerms.length !== 0 ? (
        <>
          <AddLocationPage admin={admin} />
          <AddRoomPage admin={admin} currStaff={currStaff} />
        </>
      ) : BusinessPerms.length === 0 && LocationPerms.length !== 0 ? (
        <>
          <AddRoomPage admin={admin} currStaff={currStaff} />
        </>
      ) : null}
    </>
  );
};

export default ManageFacilities;
