export class Mensagem {
  
    id: string;
    usuario: string;
    topico: string;
    description: string;
    datahora: string;

  constructor(
    id?: string,
    usuario?: string,
    topico?: string,
    description?: string,
    datahora?: string
  ) { }
}
