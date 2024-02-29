import axios from 'axios'
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { useParams } from 'react-router-dom'

const RoomMandates = () => {
  const [roomMandates, setRoomMandates] = useState([])
  const { roomId } = useParams()

  useEffect(() => {
    loadMandates()
  })


  const loadMandates = () => {
    // room 1 for now
    axios.get(`http://localhost:8181/PCTrack/rooms/3/mandates`)
    .then(response => {setRoomMandates(response.data)})
  }

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