package com.benchmark.psma.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.psma.common.security.RestClientAuthenticationHandler;
import com.benchmark.psma.controller.restful.RestError;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuthenticationInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationInterceptor.class);
	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        //if returned false, we need to make sure 'response' is sent
        String param = request.getHeader("accessToken");
        if(!RestClientAuthenticationHandler.isAuthnticated(param)){
        	writeAuthenticationError(response);
        	logger.info("Login failed, access token = " + param);
            return false;
        }
        
        // return true for authenticated clients
        return true;
    }
	
	private void writeAuthenticationError(HttpServletResponse response) throws Exception{
		RestError error = new RestError();
		error.setErrorCode("22");
		error.setErrorMessage("authentication failed");
		String errorNode = new ObjectMapper().writeValueAsString(error);
		new StringHttpMessageConverter().write(errorNode, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
	}
 
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
            
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
