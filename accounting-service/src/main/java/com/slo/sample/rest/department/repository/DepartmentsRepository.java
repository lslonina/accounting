package com.slo.sample.rest.department.repository;

import com.slo.sample.rest.department.model.Department;

import java.util.Collection;


/**
 * @author Lukasz Slonina.
 */
public interface DepartmentsRepository
{
    Collection<Department> getDepartments();


    void createDepartment( Department department );


    void updateDepartment( Integer id, Department department );


    void removeDepartment( Integer id );


    Department getDepartment( Integer id );
}
