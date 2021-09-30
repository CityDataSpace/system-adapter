import React from 'react';

import { 
    Grid,
    Typography  
} from '@material-ui/core';

const Home = (props) => {
    return (
        <div>
            <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ marginTop: '100px' }}
            >
                <Grid item xs={6}>
                    <Typography align="center" variant="h4">Fraunhofer Data Space System Adapter</Typography>
                    <br></br>
                    <Typography style={{ wordWrap: "break-word" }} align="justify" variant="body1">
                        Fraunhofer Data Space System Adapter is an application developed in Java Spring that
                        allows for storing sensitive data in a secure manner. Since Fraunhofer Data Space Ecosystem
                        supports different connectors and micro-services, there is a need to have a possibility to securely
                        store data outside the connectors. Therefore, System Adapter was developed.
                    </Typography>
                    <br></br>
                    <Typography style={{ wordWrap: "break-word" }} align="justify" variant="body1">
                        System Adapter allows for different kind of databases to be registered. Currently, Mysql and Postgres are
                        supported. 
                    </Typography>      
                </Grid>
            </Grid>
        </div>
    );
}

export default Home;