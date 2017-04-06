/*
 * Copyright (c) 2017 Nokia Networks. All rights reserved.
 */
package com.slo.sample.rest;

import java.util.Collection;


/**
 * @author Lukasz Slonina.
 */
public interface DepartmentsRepository
{
    Collection<Department> getDepartments();

    void createDepartment( Department department );

    void updateDepartment( Integer id, Department department );

    void removeRepository( Integer id );
}
