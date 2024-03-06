import React from 'react'
import NavBar from '../partials/NavBar'

const addRoomPage = (props) => {
  return (
    <>
        <NavBar admin={props.admin} />
        Add a new room
    </>
  )
}

export default addRoomPage