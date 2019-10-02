package com.tsmc.filebrowser.service.operation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tsmc.filebrowser.entity.FileInfoEntity;
import com.tsmc.filebrowser.model.StorageOperatorInterface;
import com.tsmc.filebrowser.util.LocalDriveServiceUtil;

@Service
public class LocalDriveService implements StorageOperatorInterface {

	@Override
	public List<FileInfoEntity> listItems(String path) throws IOException {
		
		List<FileInfoEntity> fileInfoEntityList = null;
		try {
			for(File file: _listItems(path)) {
				fileInfoEntityList.add(LocalDriveServiceUtil.getFileInfoEntity(path+"\\"+file.getName()));
			}
			
		}catch(IOException ex) {
			throw ex;
		}
		
		return fileInfoEntityList;
		
	}
	
	public File[] _listItems(String path) throws IOException {
		
		File targetDirectory = new File(path);
		if(targetDirectory.isDirectory()) {
			return targetDirectory.listFiles();
		}
		return null;
	}

	@Override
	public boolean renameItem(String oldPath, String newPath) {
		
		File oldFile = new File(oldPath);
		File newFile = new File(newPath);
		
		if(newFile.getParentFile().getAbsoluteFile()!=oldFile.getParentFile().getAbsoluteFile()) {
			// Must in the same directory
			return false;
		}
		
		if(newFile.exists()) {
			// Duplicate file name
			return false;
		}
		
		boolean renameResult = oldFile.renameTo(newFile);
		if(renameResult == false) {
			// Failed to rename a file
			return false;
		}
		return true;
		
	}

	@Override
	public boolean moveItems(List<String> itemPaths, String newPath) throws IOException {
		
		File destinationItem = new File(newPath);
		List<String> destinationFolderChildNames = new ArrayList<String>();
		boolean result = true;
		
		if(!destinationItem.isDirectory()) {
			// Destination is not a directory
			return false;
		}
		
		destinationFolderChildNames = LocalDriveServiceUtil.getItemListNames(_listItems(newPath));
		for(String itemPath: itemPaths) {
			
			File sourceItem = new File(itemPath);
			if(destinationFolderChildNames.contains(sourceItem.getName())){
				// "Duplicate file name"
				result = false;
				continue;
			}
			
			destinationItem = new File(newPath + "\\" + sourceItem.getName());
			boolean renameResult = sourceItem.renameTo(destinationItem);
			
			if(renameResult==false) {
				// move failed
				result = false;
				continue;
			} else {
				destinationFolderChildNames.add(sourceItem.getName());
			}
		}
		
		return result;

	}

	@Override
	public boolean copyItem(String itemPath, String newPath, String newItemName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean copyItems(List<String> itemPaths, String newPath, String newItemName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeItems(List<String> itemPaths) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editItem(String itemPath, String itemContent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getItemContent(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createFolder(String newPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePermissions(List<String> itemsPath, String perms, String permsCode, boolean recursive) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean compressItem(List<String> itemsPath, String destination, String compressedFilename) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean extractItem(String destination, String itemPath, String extractName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uploadItem(String fileName, InputStream uploadInputStream) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] downloadItem(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
