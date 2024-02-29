import React from 'react'
import NavBar from '../partials/NavBar'
import ComputerCard from '../partials/ComputerCard'
import RoomMandates from './RoomMandates'

export const SearchRoomPage = () => {


    
  const [rooms, setRooms] = useState([]);
 

  const test = (input) => {
    console.log("cancer")
    console.log(input)
    setRooms(input)
  }


  const { id } = useParams()

  useEffect(()=>{

    const config = {
      method: "get",
      endpoint: "staff/getrooms/"+id,

    }

    callApi(test, null, config);
  }, []);

   




  return (
    <>
     <div className='dashBoardPadding'>
        <h1>Under Construction</h1>
        <div>SearchRoomPage</div>
        <ComputerCard />
        <RoomMandates />
        </div>

    </>
  )
}
