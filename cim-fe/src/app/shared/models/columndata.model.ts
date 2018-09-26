/**
 * Created by anuradha on 6/6/2017.
 */
export interface ColumnData {
  field: string;
  header: string;
  property?: string;
  style?: string;
  sortable: boolean;
  type: ColumnType;
  activeActions?: Action[];
  inactiveActions?: Action[];
}

export interface Action {
  name: string;
  icon: string;
}

export enum ColumnType {
  String,
  Boolean,
  Hyperlink,
  Button
}
