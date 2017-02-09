package com.benchmark.psma.controller.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController{
	
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public String handleException(HttpServletRequest request, Exception e) throws Exception{
		if(request.getRequestURI().contains("rest")){
			return "redirect:/restError";
		}
		return "redirect:/error";
 
	}
}
