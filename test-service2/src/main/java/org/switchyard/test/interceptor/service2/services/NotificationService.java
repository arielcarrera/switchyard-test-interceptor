package org.switchyard.test.interceptor.service2.services;

import javax.ws.rs.core.Response;

/**
 * 
 * Notification Service interface
 * 
 * @author Ariel Carrera
 *
 */
public interface NotificationService {
	
	public Response getPdf(Integer id) throws Exception;

}
