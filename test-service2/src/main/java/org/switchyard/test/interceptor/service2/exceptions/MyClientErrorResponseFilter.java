package org.switchyard.test.interceptor.service2.exceptions;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class MyClientErrorResponseFilter implements ClientResponseFilter {
	
    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
//    	Family statusFamily = Family.familyOf(responseContext.getStatus());
//    	if (!Family.SUCCESSFUL.equals(statusFamily)){
    	if (responseContext.getStatus() >= 400){
                    
	            throw new WebApplicationException();
            
        }
    }
}