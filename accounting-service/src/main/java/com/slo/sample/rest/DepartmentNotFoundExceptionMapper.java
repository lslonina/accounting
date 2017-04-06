package com.slo.sample.rest;

import com.slo.sample.rest.department.service.DepartmentNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * @author Lukasz Slonina.
 */
@Provider
public class DepartmentNotFoundExceptionMapper
    implements ExceptionMapper<DepartmentNotFoundException>
{
    @Override
    public Response toResponse( DepartmentNotFoundException e )
    {
        return Response.status( Response.Status.NOT_FOUND ).entity( e.getMessage() ).build();
    }
}
