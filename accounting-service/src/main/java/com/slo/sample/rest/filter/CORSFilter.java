package com.slo.sample.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class CORSFilter
    implements ContainerResponseFilter
{
    @Override
    public void filter( ContainerRequestContext requestContext, ContainerResponseContext cres )
        throws IOException
    {
        // Specify CORS headers: * represents allow all values
        cres.getHeaders().add( "Access-Control-Allow-Origin", "*" );
        cres.getHeaders().add( "Access-Control-Allow-Headers", "*" );
        cres.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        cres.getHeaders().add(
            "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD" );
        cres.getHeaders().add( "Access-Control-Max-Age", "1209600" );
    }
}
