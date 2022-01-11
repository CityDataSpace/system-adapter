import { useState } from "react";
import { cssSetting } from "../../../DocumentationCssSettings/CssSettingsWrapper";
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
import AuthenticationPageDocumentation from "../Security/AuthenticationPageDocumentation";
import AuthorizationPageDocumentation from "../Security/AuthorizationPageDocumentation";
import EncryptionPageDocumentation from "../Security/EncryptionPageDocumentation";

interface SecurityLinksContainerProps {
  onClickValue: Function;
}

const SecurityLinksContainer = (props: SecurityLinksContainerProps) => {
  const [open, setOpen] = useState<boolean>(false);

  const name = "Security";

  const content: JSX.Element[] = [
    <AuthenticationPageDocumentation />,
    <AuthorizationPageDocumentation />,
    <EncryptionPageDocumentation />,
  ];

  const contentSelector = (innerText: string): number => {
    if (innerText === "Authentication") return 0;
    else if (innerText === "Authorization") return 1;
    return 2;
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
        {["Authentication", "Authorization", "Encryption"].map((link) => (
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

export default SecurityLinksContainer;
