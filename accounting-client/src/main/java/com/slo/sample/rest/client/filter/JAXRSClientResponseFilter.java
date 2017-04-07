package com.slo.sample.rest.client.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;


public class JAXRSClientResponseFilter
    implements ClientResponseFilter
{
    @Override
    public void filter( ClientRequestContext reqContext, ClientResponseContext respContext )
        throws IOException
    {
        if( respContext.getStatus() != 200 )
        {
            logError( respContext );
        }
    }


    private void logError( ClientResponseContext respContext )
    {
        System.err.println( "Bad thing happened: " + respContext.getStatus() );
    }
}
