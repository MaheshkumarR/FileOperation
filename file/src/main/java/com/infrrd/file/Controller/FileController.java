/**
 * 
 */
package com.infrrd.file.Controller;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infrrd.file.Constants.Constants;
import com.infrrd.file.Model.FileSrc;

import io.swagger.annotations.Api;

/**
 * @author mrkumar
 *
 */
@Api(tags = {"File API"},description= "")
@RestController
public class FileController {
	protected static Logger logger = Logger.getLogger(FileController.class.getName());
	@GetMapping(value = "download")
	public ResponseEntity<String> downloadFile(HttpServletRequest request,HttpServletResponse response)
	{
		 Path file =Paths.get(FileSrc.getInstance().getFiledestination());
		  if (Files.exists(file)) 
	        {
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition", "attachment; filename="+file.getFileName());
	            try
	            {
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            } 
	            catch (IOException ex) {
	            	logger.error(ex.getMessage());
	            }
	
	            return new ResponseEntity<>("downloading file",HttpStatus.OK);
	        }
		  return new ResponseEntity<>("file not found",HttpStatus.NOT_FOUND);
	}
	@DeleteMapping(value = "delete")
	public String deleteFile(HttpServletRequest request)
	{
		 Path file =Paths.get(FileSrc.getInstance().getFiledestination());
		 if (Files.exists(file)) 
	        {
			 try {
				Files.deleteIfExists(file);
			}
			 catch(NoSuchFileException e) 
		        { 
				 logger.error("No such file/directory exists");
		        } 
		        catch(DirectoryNotEmptyException e) 
		        { 
		        	logger.error("Directory is not empty.");
		        } catch (IOException e) {
		        	logger.error("Error."+e.getMessage());
			}
			 return "success";
	        }
		return "file doesn't exists";
	}
@PostMapping(value="copy")
public ResponseEntity<String> copyFile()
{
	 Path src_file =Paths.get(FileSrc.getInstance().getFiledestination());
	 String filestr = src_file.getFileName().toString();
	 String file_ext = filestr.substring(filestr.lastIndexOf('.'),filestr.length());
	 String fileNameWithOutExt = src_file.getFileName().toString().replaceFirst("[.][^.]+$", "");
	 fileNameWithOutExt = fileNameWithOutExt+"_copy_"+file_ext;
	 Path dest_file =Paths.get(Constants.DEFAULTFILEPATH+"\\"+fileNameWithOutExt);
try {
	if(!Files.exists(dest_file,LinkOption.NOFOLLOW_LINKS))
	{
		Files.copy(src_file, dest_file, StandardCopyOption.REPLACE_EXISTING);
	}
	else
	{
		return new ResponseEntity<>("copy of file already exists",HttpStatus.CONFLICT);
	}
	
	return new ResponseEntity<>("success",HttpStatus.OK);
} catch (IOException e) {
	logger.error("Error."+e.getMessage());
}
return new ResponseEntity<>("failed",HttpStatus.INTERNAL_SERVER_ERROR);
}

}
