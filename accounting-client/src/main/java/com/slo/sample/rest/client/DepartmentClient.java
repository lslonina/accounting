package com.slo.sample.rest.client;

import com.slo.sample.rest.client.model.Department;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;


/**
 * @author Lukasz Slonina.
 */
public class DepartmentClient
{

    public static void main( String[] args )
    {
        Client client = javax.ws.rs.client.ClientBuilder.newClient();
        String BASE_URI = "http://localhost:8080/webresources";
        WebTarget webTarget = client.target( BASE_URI );
        // Append departments URI path to Base URI
        WebTarget resource = webTarget.path( "departments" );
        // Build appropriate request type by specifying the content
        // type for the response
        Invocation.Builder builder =
            resource.request( javax.ws.rs.core.MediaType.APPLICATION_JSON );
        // Build a GET request invocation
        Invocation invocation = builder.buildGet();
        // Invoke the request and receive the response in
        // specified format type.
        GenericType<List<Department>> responseType = new GenericType<List<Department>>()
        {
        };

        List<Department> depts = invocation.invoke( responseType );

        depts.forEach( System.out::println );
    }

}
