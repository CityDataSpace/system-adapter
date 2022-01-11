import { useEffect, useState } from "react";

import {
  Grid,
  FormControl,
  InputLabel,
  Input,
  Typography,
} from "@material-ui/core";

import axios, { AxiosResponse } from "axios";

interface ProfileProps {
  userId: number;
}

interface AxiosResponseData {
  name: string;
  surname: string;
  emailAddress: string;
}

interface AxiosResponseDataWrapper {}

const Profile = (props: ProfileProps) => {
  const [name, setName] = useState<string>("");

  const [surname, setSurname] = useState<string>("");

  const [emailAddress, setEmailAddress] = useState<string>("");

  useEffect(() => {
    axios
      .get(`http://localhost:1400/getuser/${props.userId}`)
      .then((response: AxiosResponse<AxiosResponseDataWrapper>) => {
        const data = response.data as AxiosResponseData;
        setName(data.name);
        setSurname(data.surname);
        setEmailAddress(data.emailAddress);
      });
  });

  return (
    <Grid
      container
      spacing={0}
      direction="column"
      alignItems="center"
      justifyContent="center"
      style={{ marginTop: "100px" }}
    >
      <Typography align="center" variant="h4">
        Profile Information
      </Typography>
      <br />
      <br />
      <FormControl>
        <InputLabel htmlFor="my-input">Name</InputLabel>
        <Input value={name} type="text" id="name" aria-describedby="name" />
      </FormControl>
      <br />
      <br />
      <FormControl>
        <InputLabel htmlFor="my-input">Surname</InputLabel>
        <Input
          value={surname}
          type="text"
          id="surname"
          aria-describedby="surname"
        />
      </FormControl>
      <br />
      <br />
      <FormControl>
        <InputLabel htmlFor="my-input">E-mail Address</InputLabel>
        <Input
          value={emailAddress}
          type="text"
          id="emailaddress"
          aria-describedby="emailaddress"
        />
      </FormControl>
      <br />
      <br />
    </Grid>
  );
};

export default Profile;
