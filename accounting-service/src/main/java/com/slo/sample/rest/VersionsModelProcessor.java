package com.slo.sample.rest;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceModel;


@Provider
public class VersionsModelProcessor
    implements ModelProcessor
{
    @Override
    public ResourceModel processResourceModel(
        ResourceModel resourceModel,
        Configuration configuration )
    {
        ResourceModel.Builder newResourceModelBuilder = new ResourceModel.Builder( false );

        for( final Resource resource : resourceModel.getResources() )
        {
            final Resource.Builder resourceBuilder = Resource.builder( resource );

            resourceBuilder
                .addChildResource( "version" ).addMethod( HttpMethod.GET )
                .handledBy( cr -> "version : 1.0" ).produces( MediaType.APPLICATION_JSON )
                .extended( true );

            newResourceModelBuilder.addResource( resourceBuilder.build() );

        }

        return newResourceModelBuilder.build();
    }


    @Override

    public ResourceModel processSubResource(
        ResourceModel subResourceModel,
        Configuration configuration )
    {
        return subResourceModel;
    }
}
