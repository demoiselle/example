export class Todo {
  id: number;
  description: string;
  status: string;
  dateend: string;
  changed: boolean;
  constructor(
    id?: number,
    description?: string,
    status?: string,
    dateend?: string,
    changed?: boolean
  ) { }
}
