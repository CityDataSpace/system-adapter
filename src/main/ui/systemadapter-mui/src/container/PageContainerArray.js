import React from 'react'

import About from '../components/Page/About';
import Documentation from '../components/Page/Documentation';
import Home from '../components/Page/Home';
import SignIn from '../components/Page/SignIn';
import SignUp from '../components/Page/SignUp';

const PageContainerArray = (props) => {

    return [
        <Home />,
        <About />,
        <Documentation />,
        <SignIn />,
        <SignUp />
    ];
};

export default PageContainerArray;