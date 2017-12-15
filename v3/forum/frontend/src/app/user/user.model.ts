export class User {
    id: string;
    description: string;
    email: string;
    pass: string;
    foto: string;
    perfil: any;

    constructor(
      id?: string,
      description?: string,
      email?: string,
      pass?: string,
      foto?: string,
      perfil?: any
    ) { }
}
