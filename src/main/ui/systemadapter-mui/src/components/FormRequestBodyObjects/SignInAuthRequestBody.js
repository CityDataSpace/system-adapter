class SignInAuthRequestBody {

    constructor(userEmailAddress, userPassword) {
        this.userEmailAddress = userEmailAddress;
        this.userPassword = userPassword;
    }

    parse() {
        return {
            auth: {
                username: this.userEmailAddress,
                password: this.userPassword
            }
        }
    }
}

export default SignInAuthRequestBody;
