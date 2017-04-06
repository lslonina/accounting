package com.slo.sample.rest.department.service;

/**
 * @author Lukasz Slonina.
 */
public class DepartmentNotFoundException
    extends Exception
{
    public DepartmentNotFoundException( String message )
    {
        super( message );
    }
}
