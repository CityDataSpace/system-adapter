import React from 'react';

import { useState } from 'react';

import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import Collapse from '@mui/material/Collapse';
import ListItemButton from '@mui/material/ListItemButton';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import DescriptionIcon from '@material-ui/icons/Description';
import UserModelPageDocumentation from '../Model/UserModelPageDocumentation';
import PayloadModelPageDocumentation from '../Model/PayloadModelPageDocumentation';
import RegistrationModelPageDocumentation from '../Model/RegistrationModelPageDocumentation';
import RoleModelPageDocumentation from '../Model/RoleModelPageDocumentation';


const ModelLinksContainer = (props) => {

  const [open, setOpen] = useState(false);

  const name = "Models"

  const content = {
    User : <UserModelPageDocumentation />,
    Payload : <PayloadModelPageDocumentation />,
    Registration : <RegistrationModelPageDocumentation />,
    Role : <RoleModelPageDocumentation />   
  }

  const handleClick = () => {
    setOpen(!open);
  };

  const onClickHandler = (event) => {
    props.onClickValue(content[event.target.innerText])
  }

  return (<div>
    <ListItemButton onClick={handleClick}>
        <ListItemIcon><InboxIcon /></ListItemIcon>
        <ListItemText primary={name} />{open ? <ExpandLess /> : <ExpandMore />}
    </ListItemButton>
    <Collapse in={open} timeout="auto" unmountOnExit>
      {['User', 'Payload', 'Registration', 'Role'].map((link) => (
        <ListItem onClick={onClickHandler} button key={link}><ListItemIcon><DescriptionIcon /></ListItemIcon><ListItemText primary={link} /></ListItem>
      ))}
    </Collapse>
  </div>
  );

}

export default ModelLinksContainer;