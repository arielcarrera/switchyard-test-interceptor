package org.switchyard.test.interceptor.service2.services;

import java.io.File;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;
import org.switchyard.test.interceptor.service1.services.DocumentService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * download test
 * 
 * @author Ariel Carrera
 *
 */
@Slf4j
@NoArgsConstructor
@Service(NotificationService.class)
public class NotificationServiceImpl implements NotificationService {

	@Inject
	@Reference("DocumentService")
	DocumentService docServiceRef;


	@Override
	public Response getPdf(Integer id) throws Exception {
        if (id == 5) throw new javax.ws.rs.NotFoundException();
        if (id == 6) throw new javax.ws.rs.NotAuthorizedException(Response.serverError());

		Response r = null;
		try {
			 r = docServiceRef.getPdf(id);
		} catch(javax.ws.rs.NotFoundException e){
			throw new javax.ws.rs.NotFoundException();
		} catch(javax.ws.rs.NotAuthorizedException e){
			throw new javax.ws.rs.NotAuthorizedException(Response.serverError());
		}
        
		if (r.getStatus() == HttpStatus.SC_OK) {
			BaseClientResponse<Response> baseClient = (BaseClientResponse) r;
			File readSourceFile = (File) baseClient.getEntity(File.class);
			ResponseBuilder rb = Response.ok().entity(readSourceFile);
			// asigno headers
			MultivaluedMap<String, Object> headers = r.getMetadata();
			for (String hKey : headers.keySet()) {
				if (!"Content-Disposition".equalsIgnoreCase(hKey)) {
					Object v = headers.getFirst(hKey);
					rb.header(hKey, v);
				}
			}
			rb.header("Content-Disposition", "attachment; filename=\"" + id + ".pdf\"");

			return rb.build();
		} else if (r.getStatus() == HttpStatus.SC_NO_CONTENT || r.getStatus() == HttpStatus.SC_NOT_FOUND) {
			throw new javax.ws.rs.NotFoundException();
		}

		return Response.status(r.getStatus()).entity("")
				.type(MediaType.APPLICATION_JSON).build();
	}



}
