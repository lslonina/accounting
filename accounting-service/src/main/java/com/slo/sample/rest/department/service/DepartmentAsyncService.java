package com.slo.sample.rest.department.service;

import com.slo.sample.rest.department.model.Department;
import com.slo.sample.rest.department.repository.DepartmentsRepository;
import com.slo.sample.rest.department.repository.InMemoryDepartmentsRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @author Lukasz Slonina
 */
@Path( "async" )
public class DepartmentAsyncService
{

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final DepartmentsRepository departmentsRepository;


    public DepartmentAsyncService()
    {
        this.departmentsRepository = new InMemoryDepartmentsRepository();
    }


    @GET
    @Path( "departments" )
    @Produces( MediaType.APPLICATION_JSON )
    public void findAllDepartments( @Suspended
    final AsyncResponse asyncResponse )
    {
        // Set time out for the request
        asyncResponse.setTimeout( 10, TimeUnit.SECONDS );
        Runnable longRunningDeptQuery = () -> {
            try
            {
                Thread.sleep( 2000 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            Collection<Department> depts = departmentsRepository.getDepartments();
            GenericEntity<Collection<Department>> entity =
                new GenericEntity<Collection<Department>>( depts )
                {
                };
            asyncResponse.resume( Response.ok().entity( entity ).build() );
        };
        executorService.execute( longRunningDeptQuery );
    }


    @GET
    @Path( "departments/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response findDepartmentWithCacheValidationWithEtag(
        @PathParam( "id" ) Integer deptId,
        @Context Request request ) throws DepartmentNotFoundException
    {
        Department department = departmentsRepository.getDepartment( deptId );

        if( department == null )
        {
            throw new DepartmentNotFoundException( "Department does not exist: " + deptId );
        }

        // Builds ETag for department resource
        EntityTag etag = new EntityTag( Integer.toString( department.hashCode() ) );
        // Checks whether client-cached resource has changed
        // by checking ETag value present in request with value
        // generated on server
        // If changed, sends new resource with new ETag
        Response.ResponseBuilder builder = request.evaluatePreconditions( etag );
        if( builder == null )
        {
            builder = Response.ok( department );
            builder.tag( etag );
        }
        return builder.build();
    }
}
