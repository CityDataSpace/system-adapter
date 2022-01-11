import React from "react";

import { Grid, Typography } from "@material-ui/core";

const About = () => {
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
        <Grid item xs={6}>
          <Typography align="center" variant="h4">
            Fraunhofer Data Space System Adapter
          </Typography>
          <br></br>
          <Typography
            style={{ wordWrap: "break-word" }}
            align="justify"
            variant="body1"
          >
            System Adapter is made from three layers of security:
            Authentication, Authorization and Encryption.
          </Typography>
          <br></br>
          <Typography
            style={{ wordWrap: "break-word" }}
            align="justify"
            variant="body1"
          >
            Authentication is handled by Spring Security Library. We use basic
            form authentication in order to access pages with sensitive data.
          </Typography>
          <br></br>
          <Typography
            style={{ wordWrap: "break-word" }}
            align="justify"
            variant="body1"
          >
            Authorization is handled through permissions and roles designed
            inside Spring Application.
          </Typography>
          <br></br>
          <Typography
            style={{ wordWrap: "break-word" }}
            align="justify"
            variant="body1"
          >
            Encryption is handled by RSA with public and private key. Private
            key is used for encryption whereas public key is used for
            decryption.
          </Typography>
        </Grid>
      </Grid>
    </div>
  );
};

export default About;
