package com.slo.sample.rest.department.service;

import com.slo.sample.rest.department.model.Department;
import com.slo.sample.rest.department.repository.DepartmentsRepository;
import com.slo.sample.rest.department.repository.InMemoryDepartmentsRepository;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;


/**
 * @author Lukasz Slonina.
 */
@Path( "departments" )
public class DepartmentService
{
    private final DepartmentsRepository departmentsRepository;


    public DepartmentService()
    {
        this( new InMemoryDepartmentsRepository() );
    }


    public DepartmentService( DepartmentsRepository repository )
    {
        departmentsRepository = repository;
    }


    @GET
    @Produces( { MediaType.APPLICATION_JSON, "application/csv" } )
    public Collection<Department> findAllDepartments()
    {
        return departmentsRepository.getDepartments();
    }


    @GET
    @Path( "{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response findDepartmentById(
        @PathParam( "id" ) Integer deptId,
        @Context Request request ) throws DepartmentNotFoundException
    {
        Department department = departmentsRepository.getDepartment( deptId );

        if( department == null )
        {
            throw new DepartmentNotFoundException( "Department does not exist: " + deptId );
        }

        LocalDateTime lastModifiedDate = department.getModifiedDate();
        Date from = Date.from( lastModifiedDate.toInstant( ZoneOffset.UTC ) );

        Response.ResponseBuilder builder = request.evaluatePreconditions( from );

        if( builder == null )
        {
            builder = Response.ok( department );
            CacheControl cc = new CacheControl();
            cc.setMaxAge( 86400 );
            cc.setPrivate( true );

            builder.lastModified( from );

            builder.cacheControl( cc );
        }

        return builder.build();
    }


    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public void createDepartment( @Valid @ValidDepartment Department department )
    {
        departmentsRepository.createDepartment( department );
    }


    @PUT
    @Path( "{id}" )
    @Consumes( MediaType.APPLICATION_JSON )
    public void editDepartment( @PathParam( "id" ) Integer id, Department department )
    {
        departmentsRepository.updateDepartment( id, department );
    }


    @DELETE
    @Path( "{id}" )
    public void removeDepartment( @PathParam( "id" ) Integer id ) throws DepartmentNotFoundException
    {
        Department department = departmentsRepository.getDepartment( id );
        if( department != null )
        {
            departmentsRepository.removeDepartment( id );
        }
        throw new DepartmentNotFoundException( "Department does not exist: " + id );
    }

}
