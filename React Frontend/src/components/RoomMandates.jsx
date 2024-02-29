import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { useParams } from 'react-router-dom'
import callApi from '../api/callApi'

const getRoomMandates = (setRoomMandates) => {
  const config = {
    method: 'get',
    endpoint: `rooms/1/mandates`
  };
  callApi(setRoomMandates, null, config)
}

const RoomMandates = () => {
  const [roomMandates, setRoomMandates] = useState([])
  const { roomId } = useParams()

  useEffect(() => {
    getRoomMandates(setRoomMandates);
  }, [])

  return (
    <>
        <div>RoomMandates</div>
        
        <div>
          <ul style={{listStyle: 'none'}}>
            {roomMandates.map(mandate =>
              <li>{mandate.description}</li>)}
          </ul>
        </div>
    </>

  )
}

export default RoomMandates