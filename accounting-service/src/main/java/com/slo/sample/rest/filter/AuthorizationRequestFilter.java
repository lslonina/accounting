package com.slo.sample.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class AuthorizationRequestFilter
    implements ContainerRequestFilter
{
    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException
    {
        if( isSettingsService( requestContext ) )
        {
            SecurityContext securityContext = requestContext.getSecurityContext();
            if( securityContext == null || !securityContext.isUserInRole( "ADMIN" ) )
            {
                requestContext.abortWith(
                    Response
                        .status( Response.Status.UNAUTHORIZED ).entity( "Unauthorized access." )
                        .build() );
            }
        }
    }


    private boolean isSettingsService( ContainerRequestContext requestContext )
    {
        UriInfo uriInfo = requestContext.getUriInfo();
        String uri = uriInfo.getRequestUri().toString();
        return uri.contains( "/settings" );
    }
}
