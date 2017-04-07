package com.slo.sample.rest.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;


@Provider
public class DynamicFeatureRegister
    implements DynamicFeature
{
    @Override
    public void configure( ResourceInfo resourceInfo, FeatureContext context )
    {
        if( resourceInfo.getResourceMethod().isAnnotationPresent( RequestLogger.class ) )
        {
            context.register( RequestLoggerFilter.class );
        }
    }
}
