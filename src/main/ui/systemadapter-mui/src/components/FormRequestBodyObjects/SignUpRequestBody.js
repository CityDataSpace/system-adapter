class SignUpRequestBody {

    constructor(name, surname, emailAddress, password) {
        this.name = name;
        this.surname = surname;        
        this.emailAddress = emailAddress;
        this.password = password;
    }
}

export default SignUpRequestBody;
