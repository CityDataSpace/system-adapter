import React from 'react';

import { useState } from 'react';

import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import Collapse from '@mui/material/Collapse';
import ListItemButton from '@mui/material/ListItemButton';
import DescriptionIcon from '@material-ui/icons/Description';
import FolderIcon from '@mui/icons-material/Folder';
import FolderOpenIcon from '@mui/icons-material/FolderOpen';
import AesEncryptionPageDocumentation from '../Converter/AesEncryptionPageDocumentation';
import AesEncryptionValuesPageDocumentation from '../Converter/AesEncryptionValuesPageDocumentation';
import AesEncryptionValuesSecretKeyPageDocumentation from '../Converter/AesEncryptionValuesSecretKeyPageDocumentation'; 
import RsaEncryptionPageDocumentation from '../Converter/RsaEncryptionPageDocumentation';

const ModelLinksContainer = (props) => {

  const [open, setOpen] = useState(false);

  const name = "Converters"

  const content = {
    AesEncryption : <AesEncryptionPageDocumentation />,
    AesEncryptionValues : <AesEncryptionValuesPageDocumentation />,
    AesEncryptionValuesSecretKey : <AesEncryptionValuesSecretKeyPageDocumentation />,
    RsaEncryption : <RsaEncryptionPageDocumentation />   
  }

  const handleClick = () => {
    setOpen(!open);
  };

  const onClickHandler = (event) => {
    props.onClickValue(content[event.target.innerText])
  }

  return (<div>
    <ListItemButton onClick={handleClick}>
        <ListItemIcon>{open ? <FolderOpenIcon /> : <FolderIcon />}</ListItemIcon>
        <ListItemText primary={name} />{open ? <ExpandLess /> : <ExpandMore />}
    </ListItemButton>
    <Collapse in={open} timeout="auto" unmountOnExit>
      {['AesEncryption', 'AesEncryptionValues', 'AesEncryptionValuesSecretKey', 'RsaEncryption'].map((link) => (
        <ListItem style={{"padding-left":"40px"}} onClick={onClickHandler} button key={link}><ListItemIcon><DescriptionIcon /></ListItemIcon><ListItemText primary={link} /></ListItem>
      ))}
    </Collapse>
  </div>
  );

}

export default ModelLinksContainer;