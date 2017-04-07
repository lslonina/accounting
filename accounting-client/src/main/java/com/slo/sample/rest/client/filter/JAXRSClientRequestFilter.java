package com.slo.sample.rest.client.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class JAXRSClientRequestFilter
    implements ClientRequestFilter
{
    @Override
    public void filter( ClientRequestContext requestContext ) throws IOException
    {
        if( requestContext.getHeaders().get( "Client-Application" ) == null )
        {
            requestContext.abortWith(
                Response
                    .status( Response.Status.BAD_REQUEST )
                    .entity( "Client-Application header is required." ).build() );
        }
    }
}
