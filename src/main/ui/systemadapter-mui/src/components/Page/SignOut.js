import React from 'react';

const SignOut = (props) => {

    const onClickHandler = (event) => {
        event.preventDefault();

       
        props.signInClick(false)

    }

    return (
        <div>
            <button onClick={onClickHandler}>Sign Out</button>
        </div>
    );
}

export default SignOut;