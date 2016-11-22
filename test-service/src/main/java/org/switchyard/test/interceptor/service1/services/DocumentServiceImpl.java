package org.switchyard.test.interceptor.service1.services;

import java.io.File;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.switchyard.component.bean.Service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Document Service implementation
 * 
 * @author Ariel Carrera
 *
 */
@Slf4j
@NoArgsConstructor
@Service(DocumentService.class)
public class DocumentServiceImpl implements DocumentService {

	
	@Override
	public Response getPdf(Integer id)  {
		log.info("TEST GET PDF {}", id);
        if (id == 2) throw new javax.ws.rs.NotFoundException();
        if (id == 3) throw new javax.ws.rs.NotAuthorizedException(Response.serverError());
        
		return this.getDocumentResponse();
	}
	
	
	private Response getDocumentResponse() {
		File file = new File("test.pdf");
		if (!file.exists()) throw new javax.ws.rs.NotFoundException();
		ResponseBuilder response = Response.ok(file);
		response.header("Content-Disposition", "attachment; filename=\"test.pdf\"");
		response.header("Content-Type", "application/pdf");

		return response.build();
	}
}
