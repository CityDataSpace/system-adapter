class SignUpRequestBody {
  name: string;
  surname: string;
  emailAddress: string;
  password: string;

  constructor(
    name: string,
    surname: string,
    emailAddress: string,
    password: string
  ) {
    this.name = name;
    this.surname = surname;
    this.emailAddress = emailAddress;
    this.password = password;
  }
}

export default SignUpRequestBody;
