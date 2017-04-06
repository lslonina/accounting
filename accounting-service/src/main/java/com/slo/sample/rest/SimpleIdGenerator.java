package com.slo.sample.rest;

/**
 * @author Lukasz Slonina.
 */
public class SimpleIdGenerator implements IdGenerator
{
    private static Integer ID = 1;


    @Override
    public Integer generateId()
    {
        return ID++;
    }
}
