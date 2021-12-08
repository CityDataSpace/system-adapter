interface SignInAuthRequestBodyReturnType {
  auth: {
    username: string;
    password: string;
  };
}

class SignInAuthRequestBody {
  userEmailAddress: string;
  userPassword: string;

  constructor(userEmailAddress: string, userPassword: string) {
    this.userEmailAddress = userEmailAddress;
    this.userPassword = userPassword;
  }

  parse(): SignInAuthRequestBodyReturnType {
    return {
      auth: {
        username: this.userEmailAddress,
        password: this.userPassword,
      },
    };
  }
}

export default SignInAuthRequestBody;
