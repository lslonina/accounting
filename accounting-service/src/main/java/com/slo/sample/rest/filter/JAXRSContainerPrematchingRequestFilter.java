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
            System.out.println("POST: \n\t" + requestContext.getRequest().toString());
            System.out.println("\t" + requestContext.getMediaType());
//            requestContext.setMethod( "PUT" );
        }
    }
}
