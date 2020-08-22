package com.infrrd.file.Configuration;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.infrrd.file.setup.FileIntializer;



@Configuration


@EnableWebMvc
@ComponentScan(basePackages ="com.infrrd") 
 class BeanConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	private ServletContext context;
	@PostConstruct
	public void loadConfig()
	{
		FileIntializer fileinit = new FileIntializer(context);
		fileinit.copyTempFileToAbsPath();
	}
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
