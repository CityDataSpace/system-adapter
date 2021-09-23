import React from 'react';

import {useEffect, useState} from 'react';

import { 
    Grid,
    FormControl, 
    InputLabel, 
    Input, 
    FormHelperText,
    Typography  
} from '@material-ui/core';

import axios from 'axios';

const Profile = (props) => {

    const [name, setName] = useState('')

    const [surname, setSurname] = useState('')

    const [emailAddress, setEmailAddress] = useState('')

    useEffect(() => {
        axios.get(`http://localhost:1400/getuser/${props.userId}`)
             .then((response) => {
                const data = response.data;
                setName(data.name);
                setSurname(data.surname);
                setEmailAddress(data.emailAddress);
             });
    })

    

    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ marginTop: '100px' }}
        >
            <Grid item ms={4}>
                <Typography align="center" variant="h4">Profile Information</Typography>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">Name</InputLabel>
                    <Input value={name} type="text" id="name" aria-describedby="name" />
                </FormControl>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">Surname</InputLabel>
                    <Input value={surname} type="text" id="surname" aria-describedby="surname" />
                </FormControl>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">E-mail Address</InputLabel>
                    <Input value={emailAddress} type="text" id="emailaddress" aria-describedby="emailaddress" />
                </FormControl>
                <br /><br />
            </Grid>
        </Grid> 
    );
}

export default Profile;