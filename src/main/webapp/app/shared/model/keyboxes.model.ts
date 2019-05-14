export interface IKeyboxes {
  id?: number;
  name?: string;
  login?: string;
  passwordtext?: string;
  levelpasswordtext?: string;
  address?: string;
  createDate?: number;
  other?: string;
  usertokenId?: number;
}

export const defaultValue: Readonly<IKeyboxes> = {};
