import React from 'react';
import { useState, useEffect } from 'react';

import axios from 'axios';

import { 
    Grid,
    Typography  
} from '@material-ui/core';

interface PayloadProps {
  userId : number,
  signInClick : Function
};

interface PayloadResponse {
  id : number,
  headerId : number,
  data : string
};

const Payload = (props : PayloadProps) => {

    const [payloadIdsMysql, setPayloadIdsMysql] = useState<number[]>([])
    const [payloadHeaderIdsMysql, setPayloadHeaderIdsMysql] = useState<number[]>([])
    const [payloadDataMysql, setPayloadDataMysql] = useState<string[]>([])
    const [countMysql, setCountMysql] = useState<number[]>([])

    const [payloadIdsPostgres, setPayloadIdsPostgres] = useState<number[]>([])
    const [payloadHeaderIdsPostgres, setPayloadHeaderIdsPostgres] = useState<number[]>([])
    const [payloadDataPostgres, setPayloadDataPostgres] = useState<string[]>([])
    const [countPostgres, setCountPostgres] = useState<number[]>([])


    const MysqlPayloads = () => {
        useEffect(() => {

            axios.get("http://localhost:1400/api/mysql/payloads/findAll")
                .then((response) => {
                // ToDO catch properly errors for 401  unauthorized
                    if(response.data.needsLogin) {
                        props.signInClick(false);
                    }
                    
                    let payloadIdsResponseData : number[] = []
                    let payloadHeaderIdsResponseData : number[] = []
                    let payloadDataResponseData : string[] = []
                    let countArray : number[] = []
        
                    response.data.forEach((payload : PayloadResponse) => {
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
                    
                    let payloadIdsResponseData : number[] = []
                    let payloadHeaderIdsResponseData : number[] = []
                    let payloadDataResponseData : string[] = []
                    let countArray : number[] = []
        
                    response.data.forEach((payload : PayloadResponse) => {
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