package com.slo.sample.rest;

import com.slo.sample.rest.department.converter.CSVMessageBodyWriter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;


@ApplicationPath( "accounting" )
public class Application
    extends ResourceConfig
{
    public Application()
    {
        property( ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true );
//        register( ValidationConfigurationContextResolver.class );
        packages( "com.slo.sample.rest" );
    }
}
