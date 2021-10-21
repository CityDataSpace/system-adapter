import React from 'react';


import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DescriptionIcon from '@material-ui/icons/Description';

import DesignPageDocumentation from '../Design/DesignPageDocumentation';

const OrdinaryLinksContainer = (props) => {

    const content = {
        Design: <DesignPageDocumentation />
    }

    const onClickHandler = (event) => {
        props.onClickValue(content[event.target.innerText])
    }

    return (['Design'].map((link) => (
        <ListItem 
                onClick={onClickHandler} 
                button 
                key={link}>
            <ListItemIcon><DescriptionIcon /></ListItemIcon><ListItemText primary={link} /></ListItem>
    )));
}

export default OrdinaryLinksContainer;