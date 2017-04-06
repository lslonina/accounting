package com.slo.sample.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


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
    @Produces( MediaType.APPLICATION_JSON )
    public Collection<Department> findAllDepartments()
    {
        return departmentsRepository.getDepartments();
    }


    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public void createDepartment( Department department )
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
    public void removeDepartment( @PathParam( "id" ) Integer id )
    {
        departmentsRepository.removeRepository( id );
    }

}
