package org.switchyard.test.interceptor.service2.resources;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Notification interface
 *
 * @author Ariel Carrera
 */
@Path("/notifications")
public interface NotificationResource {

	@GET
	@Path("/{id}/pdf")
    @Produces({ "application/pdf" })
	public Response getPdf(@Valid @NotNull @Min(0) @PathParam("id") int id) throws Exception;

}
