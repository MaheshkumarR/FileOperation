package com.infrrd.file.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import com.infrrd.file.Constants.Constants;


public class Utils {
	  private Utils() {
		    throw new IllegalStateException("Utility class");
		  }

public static Path getFilePath(ServletContext context)
{
	String dataDirectory = context.getRealPath("/WEB-INF/downloads/");
	return Paths.get(dataDirectory, Constants.fileName);
}
}
