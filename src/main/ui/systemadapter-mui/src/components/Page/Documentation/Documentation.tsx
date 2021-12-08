import React from 'react';
import { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';

import ModelLinksContainer from './Container/ModelLinksContainer';
import DesignPageDocumentation from './Design/DesignPageDocumentation';
import SecurityLinksContainer from './Container/SecurityLinksContainer';
import OrdinaryLinksContainer from './Container/OrdinaryLinksContainer';
import ConverterLinksContainer from './Container/ConverterLinksContainer';

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

    const [content, setContent] = useState<JSX.Element>(<DesignPageDocumentation />)

    const securityLinksContainerOnClickHandler = (value : JSX.Element) => {
      setContent((prevContent) => {
        return value;
      })
    }
 
    return (
      <div className={classes.root}>
        <Drawer className={classes.drawer} variant="permanent" classes={{paper: classes.drawerPaper}}>
          <Toolbar />
          <div className={classes.drawerContainer}>
            <List>
              {['Ordinary', 'Models', 'Security', 'Converters'].map((clickedOption) => (
                (() => {
                  if (clickedOption === 'Security')  return <SecurityLinksContainer key="securityLinksContainer" onClickValue={securityLinksContainerOnClickHandler} />; 
                  else if(clickedOption === 'Models') return <ModelLinksContainer key="modelLinksContainer" onClickValue={securityLinksContainerOnClickHandler} />;
                  else if(clickedOption === 'Ordinary') return <OrdinaryLinksContainer  key="ordinaryLinksContainer" onClickValue={securityLinksContainerOnClickHandler} />;
                  else if(clickedOption === 'Converters') return <ConverterLinksContainer  key="converterLinksContainer" onClickValue={securityLinksContainerOnClickHandler} />;    
                })()               
              ))}
            </List>
          </div>
        </Drawer>
        <main className={classes.content}>
          <Toolbar />
          {content}
        </main>
      </div>
    );
  }

export default Documentation;