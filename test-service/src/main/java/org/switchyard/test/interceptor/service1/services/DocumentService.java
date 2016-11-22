package org.switchyard.test.interceptor.service1.services;

import javax.ws.rs.core.Response;

/**
 * 
 * Document Service interface
 * 
 * @author Ariel Carrera
 *
 */

public interface DocumentService {
		
	public Response getPdf(Integer id) throws Exception;
}
