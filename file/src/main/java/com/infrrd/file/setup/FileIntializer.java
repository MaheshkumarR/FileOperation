package com.infrrd.file.setup;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

import com.infrrd.file.Constants.Constants;
import com.infrrd.file.Model.FileSrc;
import com.infrrd.file.Utils.Utils;
public class FileIntializer {
private ServletContext context;
	public  FileIntializer(ServletContext context)
	{
		this.context = context;
		File filepath = new File(Constants.DEFAULTFILEPATH);
		if(!filepath.exists())
		{
			filepath.mkdirs();
		}
	}
	public void copyTempFileToAbsPath()
	{
		Path src_file = Utils.getFilePath(context);
		String file_dest = Constants.DEFAULTFILEPATH+"\\"+src_file.getFileName();
		FileSrc.getInstance().setFiledestination(file_dest);
		Path dest_folder = Paths.get(file_dest);
		try {
			Files.copy(src_file, dest_folder, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (NoSuchFileException e) {
		System.out.println(e.getMessage());
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
