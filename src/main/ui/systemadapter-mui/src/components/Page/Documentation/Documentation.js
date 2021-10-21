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

import DesignPageDocumentation from './Design/DesignPageDocumentation';

import AuthenticationPageDocumentation from './Security/AuthenticationPageDocumentation';
import AuthorizationPageDocumentation from './Security/AuthorizationPageDocumentation';
import EncryptionPageDocumentation from './Security/EncryptionPageDocumentation';
    
    const drawerWidth = 240;
    
    const useStyles = makeStyles((theme) => ({
      root: {
        display: 'flex',
      },
      appBar: {
        zIndex: theme.zIndex.drawer + 1,
      },
      drawer: {
        width: drawerWidth,
        flexShrink: 0,
      },
      drawerPaper: {
        width: drawerWidth,
      },
      drawerContainer: {
        overflow: 'auto',
      },
      content: {
        flexGrow: 1,
        padding: theme.spacing(3),
      },
    }));
    
    const Documentation = () => {
      const classes = useStyles();

      const [text, setText] = useState("Design")

      const content = {
        Design: <DesignPageDocumentation />,
        Authentication: <AuthenticationPageDocumentation />,
        Authorization: <AuthorizationPageDocumentation />,
        Encryption: <EncryptionPageDocumentation />
       
      }

      const onClickHandler = (event) => {
        setText((prevText) => {
          return event.target.innerText;
        })
      }

      const normalLinks = (text) => {return <ListItem onClick={onClickHandler} button key={text}><ListItemIcon><DescriptionIcon /></ListItemIcon><ListItemText primary={text} /></ListItem>}

      const securityLinks = (text) => {return <div><ListItemButton onClick={handleClick}>
      <ListItemIcon>
        <InboxIcon />
      </ListItemIcon>
      <ListItemText primary={text} />
      {open ? <ExpandLess /> : <ExpandMore />}
    </ListItemButton>
    <Collapse in={open} timeout="auto" unmountOnExit>
      {['Authentication', 'Authorization', 'Encryption'].map((text, index) => (
        <ListItem onClick={onClickHandler} button key={text}><ListItemIcon><DescriptionIcon /></ListItemIcon><ListItemText primary={text} /></ListItem>
      ))}
    </Collapse></div>}

      const [open, setOpen] = useState(true);

      const handleClick = () => {
        setOpen(!open);
      };
    
      return (
        <div className={classes.root}>
          <Drawer
            className={classes.drawer}
            variant="permanent"
            classes={{
              paper: classes.drawerPaper,
            }}
          >
            <Toolbar />
            <div className={classes.drawerContainer}>
              <List>
                {['Design', 'Security'].map((text, index) => (

                  
                    text === 'Security' ? securityLinks(text) : normalLinks(text)
                  

                  
                ))}
              </List>
            </div>
          </Drawer>
          <main className={classes.content}>
            <Toolbar />
            {content[text]}
          </main>
        </div>
      );
}

export default Documentation;