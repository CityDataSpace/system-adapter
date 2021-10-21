import React from 'react';

import { useState } from 'react';

import { 
    Grid,
    FormControl, 
    InputLabel, 
    Input, 
    FormHelperText,
    Button,
    Typography  
} from '@material-ui/core';

import axios from 'axios';
import SignUpRequestBody from '../../FormRequestBodyObjects/SignUpRequestBody';

const SignUp = () => {

    const [name, setName] = useState('');
    const [surname,  setSurname] = useState('');
    const [emailAddress, setEmailAddress] = useState('');
    const [password, setPassword] = useState('');

    const nameChangeHandler = (changedName) => {
        setName(changedName.target.value);
    }

    const surnameChangeHandler = (changedSurname) => {
        setSurname(changedSurname.target.value);
    }

    const emailAddressChangeHandler = (changedEmailAddress) => {
        setEmailAddress(changedEmailAddress.target.value);
    }

    const passwordChangeHandler = (changedPassword) => {
        setPassword(changedPassword.target.value);
    }

    const onClickHandler = (event) => {
        event.preventDefault();

        axios.post("http://localhost:1400/user/sign/signup", new SignUpRequestBody(name, surname, emailAddress, password))
             .then((response) => console.log(response));

        //props.signInClick(true)
    }

    

    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ marginTop: '100px' }}
        >
            <Grid item xs={3}>
                <Typography align="center" variant="h4">Sign Up Form</Typography>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">Name</InputLabel>
                    <Input value={name} onChange={nameChangeHandler} type="text" id="name" aria-describedby="name" />
                    <FormHelperText id="my-helper-text">Enter your name</FormHelperText>
                </FormControl>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">Surname</InputLabel>
                    <Input value={surname} onChange={surnameChangeHandler} type="text" id="surname" aria-describedby="surname" />
                    <FormHelperText id="my-helper-text">Enter e-mail address you used for registration</FormHelperText>
                </FormControl>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">Email address</InputLabel>
                    <Input value={emailAddress} onChange={emailAddressChangeHandler} type="text" id="emailAddress" aria-describedby="email address" />
                    <FormHelperText id="my-helper-text">Enter e-mail address you used for registration</FormHelperText>
                </FormControl>
                <br /><br />
                <FormControl>
                    <InputLabel htmlFor="my-input">Password</InputLabel>
                    <Input value={password} onChange={passwordChangeHandler} type="password" id="password" aria-describedby="password" />
                    <FormHelperText id="my-helper-text">Enter the password you used for registration</FormHelperText>
                </FormControl>
                <br /><br />
                <Button onClick={onClickHandler} variant="contained" color="primary">Sign In</Button>
            </Grid>
        </Grid> 
    );
}

export default SignUp;