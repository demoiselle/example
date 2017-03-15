export class Todo {
  id: number;
  description: string;
  status: string;
  dateEnd: string;
  changed: boolean;
  user: any;
  constructor(
    id?: number,
    description?: string,
    status?: string,
    dateEnd?: string,
    changed?: boolean,
    user?: any
  ) { }
}
