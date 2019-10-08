import { FileNode } from './file-node';

export class ResponseBody {
  resultCode?: number;
  resultMessage?: string;
  result?: FileNode[];
}
