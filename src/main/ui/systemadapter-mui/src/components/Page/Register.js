import React from 'react';
import {useState, useEffect} from 'react';

import axios from 'axios';

import { 
    Grid,
    Typography,
    Button  
} from '@material-ui/core';

const Register = (props) => {

    const [registrations, setRegistrations] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:1400/getregistration/${props.userId}`)
             .then((response) => {
                setRegistrations(response.data.registrations);    
             });
    }, [props.userId])

    const onClickHandler = (event) => {
        event.preventDefault();
    };

    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ marginTop: '100px' }}
        >
            <Grid item xs={4}>
            {
               [0, 1].map((database) => {
                    if(database === 0) {
                        if(registrations.includes(database))
                            return (<Typography align="center" variant="h4">Mysql Database Available</Typography>)
                        else 
                            return(<Typography align="center" variant="h4">
                                    <Button onClick={onClickHandler} variant="contained" color="primary">Register Mysql Database</Button>
                                  </Typography>)
                   }
                    else if(database === 1) {
                        if(registrations.includes(database))
                            return (<Typography align="center" variant="h4">Postgres Database Available</Typography>)
                        else 
                            return(<Typography align="center" variant="h4">
                                    <Button onClick={onClickHandler} variant="contained" color="primary">Register Postgres Database</Button>
                                   </Typography>)
                    }

                    return (<></>)
               })
            }
            
           
            </Grid>
        </Grid> 
    );
}

export default Register;