package com.slo.sample.rest;

import com.slo.sample.rest.department.converter.CSVMessageBodyWriter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath( "accounting" )
public class Application
    extends ResourceConfig
{
    public Application()
    {
//        register( ValidationConfigurationContextResolver.class );
        packages( "com.slo.sample.rest" );
    }
}
