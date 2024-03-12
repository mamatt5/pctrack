import { useEffect, useState } from "react";
import callApi from "../api/callApi";

const getRooms = (setRooms, locationId) => {
  // Functionality to get rooms by locationId
  const config = {
    method: "get",
    endpoint: `roomsByLocation/${locationId}`,
  };

  // Call the API and update the rooms state
  callApi(
    (data) => {
      setRooms((prevRooms) => [...prevRooms, ...data]);
    },
    null,
    config
  );
};

const LocationComputerStatus = ({ perms }) => {
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    // Map through perms and get rooms for each location
    setRooms([])
    perms.forEach((staff) => {
      getRooms(setRooms, staff.location.locationId);
    });
  }, []);


  return (
    <>
      {/* {rooms.map((room) => (
        <Typography key={room.id}>
          {room.location.city} {room.name}
        </Typography>
      ))} */}
    </>
  );
};

export default LocationComputerStatus;
