/**
 * Created by anuradha on 6/6/2017.
 */
export interface FilterData {
  sortProperty?: string;
  sortOrder?: number;
  startIndex?: number;
  pageSize?: number;
  showInactive: boolean;
}

export class EventData {
  actionName: string;
  eventData: any;
}

export interface FilterResult {
  totalRecords: number,
  result: any[]
}
