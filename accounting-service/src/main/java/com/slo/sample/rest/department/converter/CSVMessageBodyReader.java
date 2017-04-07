package com.slo.sample.rest.department.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.slo.sample.rest.department.model.Department;


@Provider
@Consumes( "application/csv" )
public class CSVMessageBodyReader
    implements MessageBodyReader<List<Department>>
{

    /**
     * Ascertain if the MessageBodyReader can produce an instance of a particular type.
     */
    @Override
    public boolean isReadable(
        Class<?> type,
        Type genericType,
        Annotation[] annotations,
        MediaType mediaType )
    {
        return Collection.class.isAssignableFrom( type );
    }


    /**
     * Read a type from InputStream.
     */
    @Override
    public List<Department> readFrom(
        Class<List<Department>> type,
        Type genericType,
        Annotation[] annotations,
        MediaType mediaType,
        MultivaluedMap<String, String> httpHeaders,
        InputStream entityStream ) throws IOException, WebApplicationException
    {
        List<Department> list = new ArrayList<>();

        // Following code uses Super CSV lib for reading CSV data
        // Define the type for each column in CSV
        final CellProcessor[] processors = { new NotNull( new ParseInt() ), // departmentId
                                             new NotNull(), // departmentName
                                             new NotNull( new ParseInt() ), // locationId
        };

        // Reads CSV input stream

        ICsvBeanReader beanReader = new CsvBeanReader(
            new InputStreamReader( entityStream ), CsvPreference.STANDARD_PREFERENCE );

        // Start building object from CVS

        String[] header = beanReader.getHeader( false );

        Department obj = null;

        while( (obj = beanReader.read( Department.class, header, processors )) != null )
        {
            list.add( obj );

        }
        return list;
    }
}
