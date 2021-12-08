import { cssSetting } from "../../../DocumentationCssSettings/CssSettingsWrapper";
import { useState } from "react";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import ExpandLess from "@material-ui/icons/ExpandLess";
import ExpandMore from "@material-ui/icons/ExpandMore";
import Collapse from "@mui/material/Collapse";
import ListItemButton from "@mui/material/ListItemButton";
import DescriptionIcon from "@material-ui/icons/Description";
import FolderIcon from "@mui/icons-material/Folder";
import FolderOpenIcon from "@mui/icons-material/FolderOpen";
import AesEncryptionPageDocumentation from "../Converter/AesEncryptionPageDocumentation";
import AesEncryptionValuesPageDocumentation from "../Converter/AesEncryptionValuesPageDocumentation";
import AesEncryptionValuesSecretKeyPageDocumentation from "../Converter/AesEncryptionValuesSecretKeyPageDocumentation";
import RsaEncryptionPageDocumentation from "../Converter/RsaEncryptionPageDocumentation";

interface ConverterLinksContainerProps {
  onClickValue: Function;
}

const ConverterLinksContainer = (props : ConverterLinksContainerProps) => {
  const [open, setOpen] = useState(false);

  const name = "Converters";

  const content: JSX.Element[] = [
    <AesEncryptionPageDocumentation />,
    <AesEncryptionValuesPageDocumentation />,
    <AesEncryptionValuesSecretKeyPageDocumentation />,
    <RsaEncryptionPageDocumentation />,
  ];

  const contentSelector = (innerText: string): number => {
    if (innerText === "AesEncryption") return 0;
    else if (innerText === "AesEncryptionValues") return 1;
    else if (innerText === "AesEncryptionValuesSecretKey") return 2;
    return 3;
  };

  const handleClick = () => {
    setOpen(!open);
  };

  const onClickHandler = (event: any) => {
    props.onClickValue(content[contentSelector(event.target.innerText)]);
  };

  return (
    <div>
      <ListItemButton onClick={handleClick}>
        <ListItemIcon>
          {open ? <FolderOpenIcon /> : <FolderIcon />}
        </ListItemIcon>
        <ListItemText primary={name} />
        {open ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open} timeout="auto" unmountOnExit>
        {[
          "AesEncryption",
          "AesEncryptionValues",
          "AesEncryptionValuesSecretKey",
          "RsaEncryption",
        ].map((link) => (
          <ListItem
            style={cssSetting("padding-left", "40px")}
            onClick={onClickHandler}
            button
            key={link}
          >
            <ListItemIcon>
              <DescriptionIcon />
            </ListItemIcon>
            <ListItemText primary={link} />
          </ListItem>
        ))}
      </Collapse>
    </div>
  );
};

export default ConverterLinksContainer;
