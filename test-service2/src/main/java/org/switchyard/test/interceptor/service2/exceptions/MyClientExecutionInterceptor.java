package org.switchyard.test.interceptor.service2.exceptions;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

@Provider
@ClientInterceptor
public class MyClientExecutionInterceptor implements ClientExecutionInterceptor {

    public ClientResponse execute(ClientExecutionContext ctx) throws Exception {
        ClientRequest request = ctx.getRequest();
        ClientResponse response = null;

        response = ctx.proceed();
        if ((response.getResponseStatus() != null) && (response.getResponseStatus().getStatusCode() == 404)) {
            BaseClientResponse r = (BaseClientResponse) response;
            MultivaluedMap<String, String> headers = r.getHeaders();
            headers.add("full-path", request.getUri());
            r.setHeaders(headers);
        }
        return response;
    }
}