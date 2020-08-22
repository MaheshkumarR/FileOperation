/**
 * 
 */
package com.infrrd.file.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author mrkumar
 *
 */
public class WebServletConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webctx = new AnnotationConfigWebApplicationContext();
		webctx.register(BeanConfiguration.class);
		webctx.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("fileservlet",getDispatcherServletObj(webctx));
		servlet.setLoadOnStartup(100);
	    servlet.addMapping("/");
		
	}
	private DispatcherServlet getDispatcherServletObj(AnnotationConfigWebApplicationContext webctx)
	{
		DispatcherServlet serv = new DispatcherServlet(webctx);
		serv.setThrowExceptionIfNoHandlerFound(true);
		return serv;
	}

}
