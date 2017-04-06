package com.slo.sample.rest.department.service;

import com.slo.sample.rest.department.model.Department;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author Lukasz Slonina.
 */
public class ValidDepartmentValidator
    implements ConstraintValidator<ValidDepartment, Department>
{

    @Override
    public void initialize( ValidDepartment validDepartment )
    {

    }


    @Override
    public boolean isValid(
        Department department,
        ConstraintValidatorContext constraintValidatorContext )
    {
        return !isDeptExistingForLocation(
            department.getId(), department.getName(), department.getLocation() );
    }


    private boolean isDeptExistingForLocation( Integer id, String name, String location )
    {
        System.out.println( "Validating: " + name );
        return name.equals( "IT" );
    }
}
