import React from "react";

import { Grid, Button } from "@material-ui/core";

interface SignOutProps {
  userId: number,
  signInClick: Function
};

const SignOut = (props: SignOutProps) => {
  const onClickHandler = (event: React.ChangeEvent<{}>) => {
    event.preventDefault();

    props.signInClick(false);
  };

  return (
    <div>
      <Grid
        container
        spacing={0}
        direction="column"
        alignItems="center"
        justifyContent="center"
        style={{ marginTop: "100px" }}
      >
        <Grid item xs={3}>
          <Button onClick={onClickHandler} variant="contained" color="primary">
            Sign Out
          </Button>
        </Grid>
      </Grid>
    </div>
  );
};

export default SignOut;
