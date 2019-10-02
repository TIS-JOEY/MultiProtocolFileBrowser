package com.tsmc.filebrowser.model;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.tsmc.filebrowser.entity.FileInfoEntity;
public interface StorageOperatorInterface {
	
	public List<FileInfoEntity> listItems(String path) throws IOException;
	public boolean renameItem(String oldPath, String newPath);
	public boolean moveItems(List<String> itemPaths, String newPath) throws IOException;
	public boolean copyItem(String itemPath, String newPath, String newItemName);
	public boolean copyItems(List<String> itemPaths, String newPath, String newItemName);
	public boolean removeItems(List<String> itemPaths);
	public boolean editItem(String itemPath, String itemContent);
	public String getItemContent(String path);
	public boolean createFolder(String newPath);
	public boolean changePermissions(List<String> itemsPath, String perms, String permsCode, boolean recursive);
	public boolean compressItem(List<String> itemsPath, String destination, String compressedFilename);
	public boolean extractItem(String destination, String itemPath, String extractName);
	public boolean uploadItem(String fileName, InputStream uploadInputStream);
	public byte[] downloadItem(String path);
	
}
