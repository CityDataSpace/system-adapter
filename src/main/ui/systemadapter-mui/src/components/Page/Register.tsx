import React from 'react';
import {useState, useEffect} from 'react';

import axios from 'axios';

import { 
    Grid,
    Typography,
    Button  
} from '@material-ui/core';
import RegistrationRequestBody from '../FormRequestBodyObjects/RegistrationRequestBody';

interface RegisterProps {
  userId : number
};

const Register = (props : RegisterProps) => {

    const [registrations, setRegistrations] = useState<number[]>([])

    useEffect(() => {
        axios.get(`http://localhost:1400/registration/${props.userId}`)
             .then((response) => {
                setRegistrations(response.data.registrations);    
             });
    }, [props.userId])


    const handleMysql = (event : React.ChangeEvent<{}>) => {
        event.preventDefault();
        registerDatabase(0);
    }

    const handlePostgres = (event : React.ChangeEvent<{}>) => {
        event.preventDefault();
        registerDatabase(1);
    }

    const registerDatabase = (databaseType : number) => {
        
        axios.post('http://localhost:1400/registration', new RegistrationRequestBody(props.userId, databaseType))
            .then((response) => {
                // Force re render
            }); 
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
               [0, 1].map((database : number) => {
                    if(database === 0) {
                        if(registrations.includes(database))
                            return (<Typography key="0" align="center" variant="h4">Mysql Database Available</Typography>)
                        else 
                            return(<Typography key="0" align="center" variant="h4">
                                    <Button onClick={handleMysql} variant="contained" color="primary">Register Mysql Database</Button>
                                  </Typography>)
                   }
                    else if(database === 1) {
                        if(registrations.includes(database))
                            return (<Typography key="1" align="center" variant="h4">Postgres Database Available</Typography>)
                        else 
                            return(<Typography key="1" align="center" variant="h4">
                                    <Button onClick={handlePostgres} value="1" variant="contained" color="primary">Register Postgres Database</Button>
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