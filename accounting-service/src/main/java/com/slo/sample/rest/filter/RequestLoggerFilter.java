package com.slo.sample.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@RequestLogger
public class RequestLoggerFilter
    implements ContainerRequestFilter
{
    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException
    {
        System.out.println( "Invoked: " + requestContext.getUriInfo() );
    }
}
