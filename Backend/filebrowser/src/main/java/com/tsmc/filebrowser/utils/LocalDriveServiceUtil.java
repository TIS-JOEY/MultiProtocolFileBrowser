package com.tsmc.filebrowser.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

import com.tsmc.filebrowser.domain.FileNode;

public class LocalDriveServiceUtil {
	
	private static SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	public static FileNode getFileNode(String targetPath) throws IOException {
		
		FileNode fileNode = new FileNode();
		File target = new File(targetPath);
		
		BasicFileAttributes basicFileAttr = Files.readAttributes(Paths.get(targetPath), BasicFileAttributes.class);
		fileNode.setName(target.getName());
		fileNode.setPath(targetPath);
		fileNode.setIsDir(target.isDirectory());
		fileNode.setCreatedTime(df.format(basicFileAttr.creationTime().toMillis()));
		fileNode.setModifiedTime(df.format(basicFileAttr.lastModifiedTime().toMillis()));
		fileNode.setParentPath(target.getParentFile().getAbsolutePath());
		fileNode.setSize(String.valueOf(basicFileAttr.size()));
		
		return fileNode;
	}
	
	public static void deleteDir(String targetPath) throws IOException {
		
		Path directory = Paths.get(targetPath);
		
		try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			   @Override
			   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			       Files.delete(file);
			       return FileVisitResult.CONTINUE;
			   }

			   @Override
			   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			       Files.delete(dir);
			       return FileVisitResult.CONTINUE;
			   }
			});
		} catch (IOException e) {		
			throw e;
		}
	}

	public static void copyFildeNode(File source, File target) throws IOException
    {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (source.isDirectory())
        {
            //Verify if destinationFolder is already present; If not then create it
            if (!target.exists())
            {
                target.mkdir();
            }
             
            //Get all files from source directory
            String files[] = source.list();
             
            //Iterate over all files and copy them to destinationFolder one by one
            for (String file : files)
            {
                File srcFile = new File(source, file);
                File destFile = new File(target, file);
                 
                //Recursive function call
                copyFildeNode(srcFile, destFile);
            }
        }
        else
        {
            //Copy the file content from one place to another
            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
