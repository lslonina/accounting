package com.slo.sample.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath( "/")
public class Application
    extends ResourceConfig
{
    public Application()
    {
        packages( "com.slo.sample.rest" );
    }
}
