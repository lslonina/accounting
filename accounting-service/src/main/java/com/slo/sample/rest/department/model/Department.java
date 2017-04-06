package com.slo.sample.rest.department.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Lukasz Slonina.
 */
public class Department
{
    @NotNull
    @DecimalMin( "1" )
    private Integer id;

    @Size( min = 1, max = 20 )
    private String name;


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


    @Override
    public String toString()
    {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
