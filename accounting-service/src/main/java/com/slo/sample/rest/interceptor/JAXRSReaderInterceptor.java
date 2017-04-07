package com.slo.sample.rest.interceptor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;


@Provider
public class JAXRSReaderInterceptor
    implements ReaderInterceptor
{
    @Override
    public Object aroundReadFrom( ReaderInterceptorContext context )
        throws IOException,
            WebApplicationException
    {
        List<String> header = context.getHeaders().get( "Content-Encoding" );

        if( header != null && header.contains( "gzip" ) )
        {
            InputStream originalInputStream = context.getInputStream();
            context.setInputStream( new GZIPInputStream( originalInputStream ) );
        }

        return context.proceed();
    }
}
