package com.gramcha.autocompleteservice;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class AutoCompleteService extends SpringBootServletInitializer implements AsyncConfigurer {

	public AutoCompleteService()
	{
		super();
		setRegisterErrorPageFilter(false); // <- this one
	}
	
	
	  static {
	    System.setProperty("org.apache.cxf.stax.allowInsecureParser", "true");
	  }
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(AutoCompleteService.class);
	  }


	public static void main(String[] args) {
		SpringApplication.run(AutoCompleteService.class, args);
	}
	
	public Executor getAsyncExecutor() {
	    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setMaxPoolSize(10);
	    taskExecutor.setThreadNamePrefix("DEExecutor-");
	    taskExecutor.initialize();
	    return taskExecutor;
	  }
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
	    return new SimpleAsyncUncaughtExceptionHandler();
	  }
	  
}