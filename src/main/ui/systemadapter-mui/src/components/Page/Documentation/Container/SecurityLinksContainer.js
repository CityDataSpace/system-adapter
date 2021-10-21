import React from 'react';

import { useState } from 'react';

import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import Collapse from '@mui/material/Collapse';
import ListItemButton from '@mui/material/ListItemButton';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import DescriptionIcon from '@material-ui/icons/Description';

const SecurityLinksContainer = (props) => {

    const [open, setOpen] = useState(true);

    const [text, setText] = useState("Design")

    const handleClick = () => {
      setOpen(!open);
    };

    const onClickHandler = (event) => {
        setText((prevText) => {
          return event.target.innerText;
        })
      }

    return (<div>
        <ListItemButton onClick={handleClick}>
            <ListItemIcon><InboxIcon /></ListItemIcon>
            <ListItemText primary={text} />{open ? <ExpandLess /> : <ExpandMore />}
        </ListItemButton>
      <Collapse in={open} timeout="auto" unmountOnExit>
        {['Authentication', 'Authorization', 'Encryption'].map((text, index) => (
          <ListItem onClick={onClickHandler} button key={text}><ListItemIcon><DescriptionIcon /></ListItemIcon><ListItemText primary={text} /></ListItem>
        ))}
      </Collapse>
    </div>

    );

}

export default SecurityLinksContainer;