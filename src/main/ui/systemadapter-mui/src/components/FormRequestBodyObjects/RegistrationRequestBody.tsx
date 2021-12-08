class RegistrationRequestBody {
  userId: number;
  databaseType: number;

  constructor(userId: number, databaseType: number) {
    this.userId = userId;
    this.databaseType = databaseType;
  }
}

export default RegistrationRequestBody;
