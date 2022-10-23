package com.onlineshop.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public class MvcConfig {
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("product-photos", registry);
		 registry
         .addResourceHandler("/webjars/**")
         .addResourceLocations("/webjars/");
		exposeDirectory("../site-logo", registry);
	}
	
	private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();
		
		String logicalPath = pathPattern.replace("../", "") + "/**";
				
		registry.addResourceHandler(logicalPath)
			.addResourceLocations("file:/" + absolutePath + "/");		
	}

	
	
}
