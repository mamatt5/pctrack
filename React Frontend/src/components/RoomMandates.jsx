import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import callApi from '../api/callApi'

const getRoomMandates = (roomId, setRoomMandates) => {
  const config = {
    method: 'get',
    endpoint: `rooms/${roomId}/mandates`
  };
  callApi(setRoomMandates, null, config)
}

const RoomMandates = ({ roomId }) => {
  const [roomMandates, setRoomMandates] = useState([])

  useEffect(() => {
    getRoomMandates(roomId, setRoomMandates);
  }, [])

  return (
    <>
        <div>RoomMandates</div>
        
        <div>
          <ul style={{listStyle: 'none'}}>
            {roomMandates.map(mandate =>
              <li key={mandate.mandateId}>{mandate.description}</li>)}
          </ul>
        </div>
    </>

  )
}

export default RoomMandates