package com.domain.colectag.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/applicationContext.xml")
public class ApplicationContextProvider{
	
	private String message; 
	
	public String getMessage(){
		return message;		
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
