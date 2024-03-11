import AddLocationPage from "./addLocationPage";
import AddRoomPage from "./addRoomPage";
import { useState } from "react";

const ManageFacilities = ({ admin, currStaff }) => {
  // console.log(admin);
  // console.log(currStaff);
  const BusinessPerms = currStaff.filter((staff) => staff.adminLevel.name === "Business");
  const LocationPerms = currStaff.filter((staff) => staff.adminLevel.name === "Location");

  const [render, setRender] = useState(false);
  const handleChange = () => {
    // render value does change when new location is added
    setRender(!render);
  };


  return (
    <>
      {BusinessPerms.length !== 0 ? (
        <>
          <AddLocationPage admin={admin} handle={handleChange} />
          <AddRoomPage admin={admin} currStaff={currStaff} updated={render}/>
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
