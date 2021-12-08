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
import UserModelPageDocumentation from "../Model/UserModelPageDocumentation";
import PayloadModelPageDocumentation from "../Model/PayloadModelPageDocumentation";
import RegistrationModelPageDocumentation from "../Model/RegistrationModelPageDocumentation";
import RoleModelPageDocumentation from "../Model/RoleModelPageDocumentation";

interface ModelLinksContainerProps {
  onClickValue: Function;
}

const ModelLinksContainer = (props : ModelLinksContainerProps) => {
  const [open, setOpen] = useState<boolean>(false);

  const name: string = "Models";

  const content: JSX.Element[] = [
    <UserModelPageDocumentation />,
    <PayloadModelPageDocumentation />,
    <RegistrationModelPageDocumentation />,
    <RoleModelPageDocumentation />,
  ];

  const contentSelector = (innerText: string): number => {
    if (innerText === "User") return 0;
    else if (innerText === "Payload") return 1;
    else if (innerText === "Registration") return 2;
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
        {["User", "Payload", "Registration", "Role"].map((link) => (
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

export default ModelLinksContainer;
