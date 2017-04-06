package com.slo.sample.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Lukasz Slonina.
 */
public class InMemoryDepartmentsRepository
    implements DepartmentsRepository
{
    private static Map<Integer, Department> departmentsById = new HashMap<>();
    private IdGenerator idGenerator = new SimpleIdGenerator();


    @Override
    public Collection<Department> getDepartments()
    {
        System.out.println( "GET " + departmentsById.size() );
        Collection<Department> values = departmentsById.values();
        values.forEach( System.out::println );
        return values;

    }

    @Override
    public void createDepartment( Department department )
    {
        Integer key = idGenerator.generateId();
        System.out.println( "key: " + key );
        System.out.println( department );
        department.setId( key );
        departmentsById.put( key, department );
        System.out.println( "Size after: " + departmentsById.size() );

    }


    @Override
    public void updateDepartment( Integer id, Department department )
    {
        departmentsById.put( id, department );
    }


    @Override
    public void removeRepository( Integer id )
    {
        departmentsById.remove( id );
    }
}
