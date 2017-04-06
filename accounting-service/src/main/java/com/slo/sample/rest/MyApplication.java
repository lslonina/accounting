package com.slo.sample.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath( "webresources")
public class MyApplication
    extends ResourceConfig
{
    public MyApplication()
    {
        packages( "com.slo.sample.rest" );
    }
}
