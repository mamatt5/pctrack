import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

const HelpPage = () => {
  return (
    <div>
      <h1>Help & Support</h1>

      <Accordion>

        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography>Users</Typography>
        </AccordionSummary>

        <AccordionDetails>
            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Permissions</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography paragraph>Users registered to this software are given permissions based on the following roles:</Typography>
                <Accordion>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography align='left'>Staff</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography align='left'>
                        Basic view permissions for rooms, computers, and software
                    </Typography>
                    <Typography align='left'>
                        Mark room mandates as done
                    </Typography>
                </AccordionDetails>
                </Accordion>

                <Accordion>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography align='left'>Room admin</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography align='left'>
                        All staff permissions
                    </Typography>
                    <Typography align='left'>
                        Create and edit mandates for a room
                    </Typography>
                    <Typography align='left'>
                        Create and edit computers
                    </Typography>
                    <Typography align='left'>
                        Assign another room admin to a room
                    </Typography>
                </AccordionDetails>
                </Accordion>

                <Accordion>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography align='left'>Location admin</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography align='left'>
                        All Room admin permissions
                    </Typography>
                    <Typography align='left'>
                        Create rooms in a location
                    </Typography>
                    <Typography align='left'>
                        Assign another location admin for a location
                    </Typography>
                </AccordionDetails>
                </Accordion>

                <Accordion>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography align='left'>Business admin</Typography>
                </AccordionSummary>
                <AccordionDetails>
                <Typography align='left'>
                        All Location admin permissions
                    </Typography>
                    <Typography align='left'>
                        Create a location
                    </Typography>
                    <Typography align='left'>
                        Manage staff permissions
                    </Typography>
                </AccordionDetails>
                </Accordion>
            </AccordionDetails>
            </Accordion>

            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Managing users</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left'>
                    Users with certain permissions can assign roles up to their own level. This can be done on the manage users page.
                </Typography>
                <Typography align='left'>
                    Note that a user can have multiple staff roles across multiple location, ie. John can be a location admin in Sydney
                </Typography>
                <Typography align='left'>
                    and a room admin in Hong Kong.
                </Typography>
            </AccordionDetails>
            </Accordion>

            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Registering users</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left'>
                    Admins can register a new user into the system through the manage users page.
                </Typography>
            </AccordionDetails>
            </Accordion>

            
        </AccordionDetails>
      
      </Accordion>


      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography>Location</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography align='left'>
            Users with business admin permissions are allowed to create locations in the Add Location tab.
            </Typography>
        </AccordionDetails>
      </Accordion>


      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography>Rooms</Typography>
        </AccordionSummary>

        <AccordionDetails>
            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Viewing rooms</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left'>
                    In the rooms page, the user is shown a list of rooms registered in the system. A user can go to a room and view the computers
                </Typography>
                <Typography align='left'>
                    and mandates registered for that room. If permissions allow, the user can also create/edit a mandate.
                </Typography>
            </AccordionDetails>
            </Accordion>

            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Creating rooms</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left'>
                    Users with location admin permissions are able to create rooms at the locations where they hold these permissions.
                </Typography>
           
            </AccordionDetails>
            </Accordion>

            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Mandates</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left'>
                    These are requirements made by admins for a room to be ready for a specific purpose. Regular staff can view these mandates where
                </Typography>
                <Typography align='left'>
                    they can be assigned by an admin to prepare the room for the given mandate. Only admins can edit/create a mandate, but regular staff
                </Typography>
                <Typography align='left'>
                    can mark these mandates as done and will subsequently be deleted from the list.
                </Typography>
            </AccordionDetails>
            </Accordion>
        </AccordionDetails>
      </Accordion>

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
            <Typography>Computers</Typography>
        </AccordionSummary>
        <AccordionDetails>
        <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Role-ready computers</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left' paragraph>
                     Role-ready computers are defined with the following:
                </Typography>
                <Typography align='left' paragraph>
                     Dev-ready computers: computers that have Visual Studio, Eclipse IDE, Node.js, Python, NPM, MySQL components, JDK, and Git installed.
                </Typography>
                <Typography align='left' paragraph>
                     BI-ready computers: computers that have Microsoft Excel and PowerBI installed.
                </Typography>
                <Typography align='left' paragraph>
                    Note that a computer can be both Dev-ready and BI-ready.
                </Typography>
            </AccordionDetails>
            </Accordion>

            <Accordion>
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                <Typography>Computer icons</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Typography align='left'>
                    Computer icons signify one computer unit. It is color-coded depending on its readiness for a role, with Green being ready for both roles
                </Typography>
                <Typography align='left'>
                    Yellow for a specific role, and Red for none. Clicking these icons would allow the users to view the software installed in that computer
                </Typography>
                <Typography align='left'>
                    and also check where the computer is located.
                </Typography>
            </AccordionDetails>
            </Accordion>
        </AccordionDetails>
      </Accordion>


      
      

      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography>Software</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography align='left'>
          In the View Software tab, users are presented with a comprehensive list of all installed software, including their respective versions, across all computers.
            </Typography>
        </AccordionDetails>
      </Accordion>
    </div>
  );
};

export default HelpPage;
