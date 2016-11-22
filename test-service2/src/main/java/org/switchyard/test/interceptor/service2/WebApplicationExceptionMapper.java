package org.switchyard.test.interceptor.service2;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException ex) {
		Response orig = ex.getResponse();
		
		if (orig == null)
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("").type(MediaType.APPLICATION_JSON)
					.build();

		return Response.
				status(orig.getStatus()).entity("")
				.type(MediaType.APPLICATION_JSON).build();	

	}

}