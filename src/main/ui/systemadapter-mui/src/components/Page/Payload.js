import React from 'react';
import { useState, useEffect } from 'react';

import axios from 'axios';

const Payload = (props) => {

    const [payloads, setPayloads] = useState({})

    useEffect(() => {
        axios.get("http://localhost:1400/api/mysql/payloads/findAll")
             .then((response) => {
                // ToDO catch properly errors for 401  unauthorized
                if(response.data.needsLogin) {
                    props.signInClick(false);
                }
             });
    });



    return (
        <div>
            I am Payload
        </div>
    );
}

export default Payload;