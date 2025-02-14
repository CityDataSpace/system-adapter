import { ChangeEvent } from "react";

import { makeStyles } from "@material-ui/core/styles";

import HomeIcon from "@material-ui/icons/Home";
import InfoIcon from "@material-ui/icons/Info";
import PersonIcon from "@material-ui/icons/Person";
import FindInPageIcon from "@material-ui/icons/FindInPage";
import PersonOutlineIcon from "@material-ui/icons/PersonOutline";
import AccountBoxIcon from "@material-ui/icons/AccountBox";
import DescriptionIcon from "@material-ui/icons/Description";
import ControlPointIcon from "@material-ui/icons/ControlPoint";

import BottomNavigation from "@material-ui/core/BottomNavigation";
import BottomNavigationAction from "@material-ui/core/BottomNavigationAction";

interface HeaderPropsInterface {
  onClickedLink : Function,
  linkValue : number,
  isUserLoggedIn : boolean
};

const useStyles = makeStyles({
  root: {
    background: "linear-gradient(45deg, #97f0c8 30%, #29f096 90%)",
    border: 0,
    borderRadius: 3,
    boxShadow: "0 3px 5px 2px rgba(255, 105, 135, .3)",
    color: "white",
    height: 48,
    padding: "0 30px",
  },
});

const Header = (props : HeaderPropsInterface) => {
  const classes = useStyles();

  const onChangeHandler = (_ : ChangeEvent<{}>, newValue : number) : void  => {
    props.onClickedLink(newValue);
  };

  return (
    <div>
      <BottomNavigation
        value={props.linkValue}
        onChange={onChangeHandler}
        showLabels
        className={classes.root}
      >
        {props.isUserLoggedIn
          ? [
              <BottomNavigationAction
                key="loggedInProfile"
                label="Profile"
                icon={<AccountBoxIcon />}
              />,
              <BottomNavigationAction
                key="loggedInRegister"
                label="Register"
                icon={<ControlPointIcon />}
              />,
              <BottomNavigationAction
                key="loggedInPayload"
                label="Payload"
                icon={<DescriptionIcon />}
              />,
              <BottomNavigationAction
                key="loggedInSignOut"
                label="Sign Out"
                icon={<PersonOutlineIcon />}
              />,
            ]
          : [
              <BottomNavigationAction
                key="loggedOutHome"
                label="Home"
                icon={<HomeIcon />}
              />,
              <BottomNavigationAction
                key="loggedOutAbout"
                label="About"
                icon={<InfoIcon />}
              />,
              <BottomNavigationAction
                key="loggedOutDocumentation"
                label="Documentation"
                icon={<FindInPageIcon />}
              />,
              <BottomNavigationAction
                key="loggedOutSignIn"
                label="Sign In"
                icon={<PersonIcon />}
              />,
            ]}
      </BottomNavigation>
    </div>
  );
};

export default Header;
