import React from 'react';
import { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';

import DescriptionIcon from '@material-ui/icons/Description';

import AuthenticationPageDocumentation from './Documentation/AuthenticationPageDocumentation';
import AuthorizationPageDocumentation from './Documentation/AuthorizationPageDocumentation';
import DesignPageDocumentation from './Documentation/DesignPageDocumentation';
import EncryptionPageDocumentation from './Documentation/EncryptionPageDocumentation';
    
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
                {['Design', 'Authentication', 'Authorization', 'Encryption'].map((text, index) => (
                  <ListItem onClick={onClickHandler} button key={text}>
                    <ListItemIcon><DescriptionIcon /></ListItemIcon>
                    <ListItemText primary={text} />
                  </ListItem>
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