package com.slo.sample.rest.client;

import com.slo.sample.rest.client.model.Department;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Lukasz Slonina
 */
public class DepartmentAsyncClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String BASE_URI = "http://localhost:8080/accounting";
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BASE_URI).path("async").path("departments");
        AsyncInvoker asyncInvoker = webTarget.request(APPLICATION_JSON).async();
        Future<List<Department>> responseFuture = asyncInvoker.get(new InvocationCallback<List<Department>>() {
            @Override
            public void completed(List<Department> response) {
                System.out.println("Great success");
                client.close();
            }

            @Override
            public void failed(Throwable throwable) {
                System.out.println("Failure");
            }
        });
        System.out.println("Waiting for results");
        List<Department> departments = responseFuture.get();

        departments.forEach(System.out::println);
    }

}
