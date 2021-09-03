import React from 'react';

const SignIn = (props) => {

    const onClickHandler = (event) => {
        event.preventDefault();

       
        props.signInClick(true)

    }

    return (
        <div>
            <button onClick={onClickHandler}>Sign In</button>
        </div>
    );
}

export default SignIn;