import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import DescriptionIcon from "@material-ui/icons/Description";

import DesignPageDocumentation from "../Design/DesignPageDocumentation";

interface OrdinaryLinksContainerProps {
  onClickValue: Function;
}

const OrdinaryLinksContainer = (props : OrdinaryLinksContainerProps) => {
  
  const content : JSX.Element[] = [
    <DesignPageDocumentation />,
  ];

  const onClickHandler = (event : any) => {
    props.onClickValue(content[contentSelector(event.target.innerText)]);
  };

  const contentSelector = (innerText: string): number => {
    if (innerText === "Authentication") return 0;
    else if (innerText === "Authorization") return 1;
    return 2;
  };

  return (
    <div>
      {["Design"].map((link) => (
        <ListItem onClick={onClickHandler} button key={link}>
          <ListItemIcon>
            <DescriptionIcon />
          </ListItemIcon>
          <ListItemText primary={link} />
        </ListItem>
      ))}
    </div>
  );
};

export default OrdinaryLinksContainer;
