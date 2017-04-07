package com.slo.sample.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@PreMatching
public class JAXRSContainerPrematchingRequestFilter
    implements ContainerRequestFilter
{
    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException
    {
        if( requestContext.getMethod().equals( "POST" ) )
        {
            System.out.println("POST: \n" + requestContext.getRequest().toString());
//            requestContext.setMethod( "PUT" );
        }
    }
}
