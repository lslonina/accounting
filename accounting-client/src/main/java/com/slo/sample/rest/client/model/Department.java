package com.slo.sample.rest.client.model;

/**
 * @author Lukasz Slonina.
 */
public class Department
{
    private Integer id;
    private String name;
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


    @Override
    public String toString()
    {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
