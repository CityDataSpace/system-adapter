import { useState } from "react";

import { makeStyles } from '@material-ui/core/styles';

import HomeIcon from '@material-ui/icons/Home';
import InfoIcon from '@material-ui/icons/Info';
import PersonIcon from '@material-ui/icons/Person';
import PersonAddIcon from '@material-ui/icons/PersonAdd';
import FindInPageIcon from '@material-ui/icons/FindInPage';


import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';

const useStyles = makeStyles({
  root: {
    background: 'linear-gradient(45deg, #97f0c8 30%, #29f096 90%)',
    border: 0,
    borderRadius: 3,
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
    color: 'white',
    height: 48,
    padding: '0 30px',
  },
});

const Header = (props) => {
    const classes = useStyles()

    const [value, setValue] = useState('')

    const onChangeHandler = (event, newValue) => {
        setValue((previousValue) => {
            return newValue;
        });

        props.onClickedLink(newValue);
    }

    return (
        <div>
            <BottomNavigation
                value={value}
                onChange={onChangeHandler}
                showLabels
                className={classes.root}
            >

                <BottomNavigationAction label="Home" icon={<HomeIcon />} />
                <BottomNavigationAction label="About" icon={<InfoIcon />} />   
                <BottomNavigationAction label="Documentation" icon={<FindInPageIcon />} /> 
                <BottomNavigationAction label="Sign In" icon={<PersonIcon />} />
                <BottomNavigationAction label="Sign Up" icon={<PersonAddIcon />} />  
            
            </BottomNavigation>
        </div>
    );
}

export default Header;