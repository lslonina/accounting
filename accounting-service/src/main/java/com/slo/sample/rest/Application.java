package com.slo.sample.rest;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;


@ApplicationPath( "accounting" )
public class Application
    extends ResourceConfig
{
    public Application( @Context ServletContext context )
    {
        property( ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true );
        packages( "com.slo.sample.rest" );

        String configSysInfoParam = context.getInitParameter( "system.info.allow" );

        boolean allowViewServerInfo =
            (configSysInfoParam == null) ? true : Boolean.valueOf( configSysInfoParam );

        if( allowViewServerInfo )
        {
            Resource.Builder resourceBuilder = Resource.builder();
            resourceBuilder.path( "server/info" );

            ResourceMethod.Builder methodBuilder = resourceBuilder.addMethod( "GET" );

            methodBuilder.produces( MediaType.APPLICATION_JSON ).handledBy(
                new Inflector<ContainerRequestContext, String>()
                {
                    @Override
                    public String apply( ContainerRequestContext containerRequestContext )
                    {
                        // Return object containing server details in
                        // response to REST request HTTP GET /server/info
                        // SystemInfo is not shown to save space
                        return String.valueOf( Runtime.getRuntime().availableProcessors() );
                    }
                } );

            Resource resource = resourceBuilder.build();
            registerResources( resource );
        }
    }
}
