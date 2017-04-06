package com.slo.sample.rest;

import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;

import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


/**
 * Custom configuration of validation. This configuration defines custom:
 * <ul>
 * <li>ConstraintValidationFactory - so that validators are able to inject Jersey
 * providers/resources.</li>
 * <li>ParameterNameProvider - if method input parameters are invalid, this class returns actual
 * parameter names instead of the default ones ({@code arg0, arg1, ..})</li>
 * </ul>
 *
 * From: <link>https://jersey.java.net/documentation/latest/bean-validation.html</link>
 */
public class ValidationConfigurationContextResolver
    implements ContextResolver<ValidationConfig>
{

    @Context
    private ResourceContext resourceContext;


    @Override
    public ValidationConfig getContext( final Class<?> type )
    {
        final ValidationConfig config = new ValidationConfig();
        config.constraintValidatorFactory(
            resourceContext.getResource( InjectingConstraintValidatorFactory.class ) );
        config.parameterNameProvider( new CustomParameterNameProvider() );
        return config;
    }


    /**
     * See ContactCardTest#testAddInvalidContact.
     */
    private class CustomParameterNameProvider
        implements ParameterNameProvider
    {

        private final ParameterNameProvider nameProvider;


        public CustomParameterNameProvider()
        {
            nameProvider =
                Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
        }


        @Override
        public List<String> getParameterNames( final Constructor<?> constructor )
        {
            return nameProvider.getParameterNames( constructor );
        }


        @Override
        public List<String> getParameterNames( final Method method )
        {
            // See ContactCardTest#testAddInvalidContact.
            if( "addContact".equals( method.getName() ) )
            {
                return Arrays.asList( "contact" );
            }
            return nameProvider.getParameterNames( method );
        }
    }
}
