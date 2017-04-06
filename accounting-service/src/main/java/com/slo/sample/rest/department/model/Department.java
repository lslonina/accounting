package com.slo.sample.rest.department.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


/**
 * @author Lukasz Slonina.
 */
public class Department
{
    @Min( value = 1, message = "Department Id must be a positive value" )
    private Integer id;

    @Size( min = 1, max = 20, message = "Department Name length must be between 1 and 20 characters." )
    private String name;

    @Size( min = 1, max = 20, message = "Department Location length must be between 1 and 20 characters." )
    private String location;


    public Integer getId()
    {
        return id;
    }


    public void setId( Integer id )
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName( String name )
    {
        this.name = name;
    }


    public String getLocation()
    {
        return location;
    }


    public void setLocation( String location )
    {
        this.location = location;
    }


    @Override
    public String toString()
    {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
