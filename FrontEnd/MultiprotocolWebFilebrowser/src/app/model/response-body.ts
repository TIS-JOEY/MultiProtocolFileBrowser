import { FileNode } from './file-node';

export interface ResponseBody {
  resultCode?: number;
  resultMessage?: string;
  result?: FileNode[];
}
