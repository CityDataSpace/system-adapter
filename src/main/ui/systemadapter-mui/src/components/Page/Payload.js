import React from 'react';
import { useState, useEffect } from 'react';

import axios from 'axios';

import { 
    Grid,
    Typography  
} from '@material-ui/core';

const Payload = (props) => {

    const [payloadIdsMysql, setPayloadIdsMysql] = useState([])
    const [payloadHeaderIdsMysql, setPayloadHeaderIdsMysql] = useState([])
    const [payloadDataMysql, setPayloadDataMysql] = useState([])
    const [countMysql, setCountMysql] = useState([])

    const [payloadIdsPostgres, setPayloadIdsPostgres] = useState([])
    const [payloadHeaderIdsPostgres, setPayloadHeaderIdsPostgres] = useState([])
    const [payloadDataPostgres, setPayloadDataPostgres] = useState([])
    const [countPostgres, setCountPostgres] = useState([])


    const MysqlPayloads = () => {
        useEffect(() => {

            axios.get("http://localhost:1400/api/mysql/payloads/findAll")
                .then((response) => {
                // ToDO catch properly errors for 401  unauthorized
                    if(response.data.needsLogin) {
                        props.signInClick(false);
                    }
                    
                    let payloadIdsResponseData = []
                    let payloadHeaderIdsResponseData = []
                    let payloadDataResponseData = []
                    let countArray = []
        
                    response.data.forEach((payload) => {
                        payloadIdsResponseData.push(payload.id);
                        payloadHeaderIdsResponseData.push(payload.headerId);
                        payloadDataResponseData.push(payload.data);
                    })
        
                    setPayloadIdsMysql(payloadIdsResponseData)
                    setPayloadHeaderIdsMysql(payloadHeaderIdsResponseData)
                    setPayloadDataMysql(payloadDataResponseData)
                    
                    for(var i = 0; i < payloadIdsResponseData.length; i++) {
                        countArray.push(i)
                    }
                    setCountMysql(countArray)     
                });
        
        }, []);

        return countMysql.map((element) => {
            return (
              <div key={payloadIdsMysql[element]}>
                <p>ID: {payloadIdsMysql[element]}</p>
                <p>Header ID: {payloadHeaderIdsMysql[element]}</p>
                <p>Data: {payloadDataMysql[element]}</p>
              </div>
            )
        })               
    }

    const PostgresPayloads = () => {
        useEffect(() => {

            axios.get("http://localhost:1400/api/postgres/payloads/findAll")
                .then((response) => {
                // ToDO catch properly errors for 401  unauthorized
                    if(response.data.needsLogin) {
                        props.signInClick(false);
                    }
                    
                    let payloadIdsResponseData = []
                    let payloadHeaderIdsResponseData = []
                    let payloadDataResponseData = []
                    let countArray = []
        
                    response.data.forEach((payload) => {
                        payloadIdsResponseData.push(payload.id);
                        payloadHeaderIdsResponseData.push(payload.headerId);
                        payloadDataResponseData.push(payload.data);
                    })
        
                    setPayloadIdsPostgres(payloadIdsResponseData)
                    setPayloadHeaderIdsPostgres(payloadHeaderIdsResponseData)
                    setPayloadDataPostgres(payloadDataResponseData)
                    
                    for(var i = 0; i < payloadIdsResponseData.length; i++) {
                        countArray.push(i)
                    }
                    setCountPostgres(countArray)     
                });
        
        }, []);

        return countPostgres.map((element) => {
            return (
              <div key={payloadIdsPostgres[element]}>
                <p>ID: {payloadIdsPostgres[element]}</p>
                <p>Header ID: {payloadHeaderIdsPostgres[element]}</p>
                <p>Data: {payloadDataPostgres[element]}</p>
              </div>
            )
        })               
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
            <Grid item xs={4}>
            <Typography align="center" variant="h4">Mysql Payloads</Typography>
            {
                MysqlPayloads()  
            }
            <Typography align="center" variant="h4">Postgres Payloads</Typography>
            {
                PostgresPayloads()  
            }
            </Grid>
        </Grid> 
    );
}

export default Payload;