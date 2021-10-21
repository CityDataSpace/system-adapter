import React from 'react';

import { useState } from 'react';

import Container from '@material-ui/core/Container';

import Home from '../Page/Home';
import About from '../Page/About';

import Documentation from '../Page/Documentation/Documentation';

import SignIn from '../Page/User/SignIn';
import SignOut from '../Page/User/SignOut';
import Profile from '../Page/User/Profile';

import Payload from '../Page/Payload';
import Register from '../Page/Register';

const PageContainer = (props) => {


    const [userId, setUserId] = useState(-1)

    const [isUserLoggedIn, setIsUserLoggedIn] = useState(props.isUserLoggedIn)

    const signInClickHandler = (data) => {
        setIsUserLoggedIn(data)
        props.isUserLoggedInToken(data)
    }

    const signedInUserIdHandler = (signedInUserId) => {
        console.log(signedInUserId)
        setUserId(signedInUserId)
    }

    const pageContainer =  isUserLoggedIn ? [
        <Profile userId={userId} />,
        <Register userId={userId} />,
        <Payload userId={userId} />,
        <SignOut userId={userId} signInClick={signInClickHandler} />
    ] : [
        <Home />,
        <About />,
        <Documentation />,
        <SignIn signedInUserId={signedInUserIdHandler} signInClick={signInClickHandler} />
    ];

    return (
        <Container maxWidth={"lg"}>
            {pageContainer[props.onSelectedLink]}
        </Container>
    );
}

export default PageContainer;