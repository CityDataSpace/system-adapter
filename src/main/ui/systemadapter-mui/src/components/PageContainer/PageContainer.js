import React from 'react';

import { useState } from 'react';

import Container from '@material-ui/core/Container';
import About from '../Page/About';
import Documentation from '../Page/Documentation';
import Home from '../Page/Home';
import SignIn from '../Page/SignIn';
import SignUp from '../Page/SignUp';
import SignOut from '../Page/SignOut';

const PageContainer = (props) => {

    const [isUserLoggedIn, setIsUserLoggedIn] = useState(props.isUserLoggedIn)

    const signInClickHandler = (data) => {
        setIsUserLoggedIn(data)
        props.isUserLoggedInToken(data)
    }

    const pageContainer =  isUserLoggedIn ? [
        <Home />,
        <About />,
        <SignOut signInClick={signInClickHandler} />
    ] : [
        <Home />,
        <About />,
        <Documentation />,
        <SignIn signInClick={signInClickHandler} />,
        <SignUp />
    ];

    return (
        <Container maxWidth={"lg"}>
            {pageContainer[props.onSelectedLink]}
        </Container>
    );
}

export default PageContainer;