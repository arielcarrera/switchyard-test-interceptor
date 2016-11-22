package org.switchyard.test.interceptor.service2.exceptions;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.client.core.ClientErrorInterceptor;


@Provider
class MyClientErrorInterceptor implements ClientErrorInterceptor {

    public void handle(ClientResponse response) throws RuntimeException {
        try {
            BaseClientResponse r = (BaseClientResponse) response;

            InputStream stream = r.getStreamFactory().getInputStream();
            if (stream != null) {
                stream.reset();
            }
            if ((response.getResponseStatus() != null) && (response.getResponseStatus().getStatusCode() == 404)
                && !(r.getException() instanceof javax.ws.rs.NotFoundException)) {
                throw new WebApplicationException(Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("")
                .build());
            }

        } catch (IOException e){
        }
    }
}