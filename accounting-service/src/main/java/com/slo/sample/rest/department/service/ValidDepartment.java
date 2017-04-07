package com.slo.sample.rest.department.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * @author Lukasz Slonina.
 */
@Constraint( validatedBy = { ValidDepartmentValidator.class } )
@Target( { ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD } )
@Retention( value = RetentionPolicy.RUNTIME )
public @interface ValidDepartment
{
    String message() default "{com.slo.sample.rest.validation.deptrule}";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
}
