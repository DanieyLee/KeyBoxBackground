import { IKeyboxes } from 'app/shared/model/keyboxes.model';

export interface IUsertoken {
  id?: number;
  userid?: string;
  state?: string;
  endDate?: number;
  userids?: IKeyboxes[];
}

export const defaultValue: Readonly<IUsertoken> = {};
