export interface ResponseBodyInterface {
  code?: string;
  result?: {
    columns: Array<string>,
    values: {}
  };
  message?: string;
}
