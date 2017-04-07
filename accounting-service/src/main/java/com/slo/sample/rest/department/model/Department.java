package com.slo.sample.rest.department.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author Lukasz Slonina.
 */
//@XmlRootElement
public class Department
{
    @Min( value = 1, message = "Department Id must be a positive value" )
    private Integer id;

    @Size( min = 1, max = 20, message = "Department Name length must be between 1 and 20 characters." )
    private String name;

    @Size( min = 1, max = 20, message = "Department Location length must be between 1 and 20 characters." )
    private String location;

//    @XmlElement( name = "link" )
//    @XmlJavaTypeAdapter( XmlAdapter.class )
//    public Link getEmployeeLink()
//    {
//        return Link.fromUri( "{id}/employees" ).rel( "employees" ).build( id );
//    }
//

    @XmlTransient
    private LocalDateTime modifiedDate;


    public Integer getId()
    {
        return id;
    }


    public void setId( Integer id )
    {
        this.id = id;
        updateModifiedDate();
    }


    public String getName()
    {
        return name;
    }


    public void setName( String name )
    {
        this.name = name;
        updateModifiedDate();
    }


    public String getLocation()
    {
        return location;
    }


    public void setLocation( String location )
    {
        this.location = location;
        updateModifiedDate();
    }


    @Override
    public String toString()
    {
        return "Department{" + "id=" + id + ", name='" + name + '\'' + '}';
    }


    public LocalDateTime getModifiedDate()
    {
        return modifiedDate;
    }


    private void updateModifiedDate()
    {
        modifiedDate = LocalDateTime.now();
    }
}
