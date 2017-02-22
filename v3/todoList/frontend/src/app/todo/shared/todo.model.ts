export class Todo {
  id: number;
  description: string;
  status: string;
  dateend: string;
  changed: boolean;
  user: any;
  constructor(
    id?: number,
    description?: string,
    status?: string,
    dateend?: string,
    changed?: boolean,
    user?: any
  ) { }
}
