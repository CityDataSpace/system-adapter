import React, { ChangeEvent, useState } from "react";

import {
  Grid,
  FormControl,
  InputLabel,
  Input,
  FormHelperText,
  Button,
  Typography,
} from "@material-ui/core";

import axios from "axios";
import SignInRequestBody from "../../FormRequestBodyObjects/SignInRequestBody";
import SignInAuthRequestBody from "../../FormRequestBodyObjects/SignInAuthRequestBody";

interface SignInProps {
  signInClick: Function;
  signedInUserId: Function;
}

const SignIn = (props: SignInProps) => {
  const [emailAddress, setEmailAddress] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const onClickHandler = (event: ChangeEvent<{}>) => {
    event.preventDefault();

    axios
      .post(
        "http://localhost:1400/user/sign/signin",
        new SignInRequestBody(emailAddress, password),
        new SignInAuthRequestBody(emailAddress, password).parse()
      )
      .then((response) => {
        props.signInClick(true);
        props.signedInUserId(response.data.loggedInUserId);
      })
      .catch((err) => console.log(err));
  };

  const emailAddressChangeHandler = (
    changedEmailAddress: ChangeEvent<HTMLInputElement>
  ) => {
    setEmailAddress(changedEmailAddress.target.value);
  };

  const passwordChangeHandler = (
    changedPassword: ChangeEvent<HTMLInputElement>
  ) => {
    setPassword(changedPassword.target.value);
  };

  return (
    <Grid
      container
      spacing={0}
      direction="column"
      alignItems="center"
      justifyContent="center"
      style={{ marginTop: "100px" }}
    >
      <Grid item xs={3}>
        <Typography align="center" variant="h4">
          Sign In Form
        </Typography>
        <br />
        <br />
        <FormControl>
          <InputLabel htmlFor="my-input">Email address</InputLabel>
          <Input
            value={emailAddress}
            onChange={emailAddressChangeHandler}
            type="text"
            id="emailAddress"
            aria-describedby="email address"
          />
          <FormHelperText id="my-helper-text">
            Enter e-mail address you used for registration
          </FormHelperText>
        </FormControl>
        <br />
        <br />
        <FormControl>
          <InputLabel htmlFor="my-input">Password</InputLabel>
          <Input
            value={password}
            onChange={passwordChangeHandler}
            type="password"
            id="password"
            aria-describedby="password"
          />
          <FormHelperText id="my-helper-text">
            Enter the password you used for registration
          </FormHelperText>
        </FormControl>
        <br />
        <br />
        <Button onClick={onClickHandler} variant="contained" color="primary">
          Sign In
        </Button>
      </Grid>
    </Grid>
  );
};

export default SignIn;
