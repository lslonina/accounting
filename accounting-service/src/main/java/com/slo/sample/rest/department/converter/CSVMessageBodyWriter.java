package com.slo.sample.rest.department.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.slo.sample.rest.department.model.Department;


@Provider
@Produces( "application/csv" )
public class CSVMessageBodyWriter
    implements MessageBodyWriter<Collection<Department>>
{

    /**
     * Ascertain if MessageBodyWriter supports a particular type.
     */
    @Override
    public boolean isWriteable(
        Class<?> type,
        Type genericType,
        Annotation[] annotations,
        MediaType mediaType )
    {
        // Is this MessageBodyWriter implementation capable of serializing the object type returned
        // by the current REST API call?
        return (Collection.class.isAssignableFrom( type ));
    }


    /**
     * Deprecated by JAX-RS 2.0 and ignored by Jersey runtime
     */
    @Override
    public long getSize(
        Collection<Department> t,
        Class<?> type,
        Type genericType,
        Annotation[] annotations,
        MediaType mediaType )
    {
        return 0;
    }


    /**
     * Converts Java to desired media type and Writes it to an HTTP response
     */
    @Override
    public void writeTo(
        Collection<Department> dataList,
        Class<?> type,
        Type genericType,
        Annotation[] annotations,
        MediaType mediaType,
        MultivaluedMap<String, Object> httpHeaders,
        OutputStream entityStream ) throws IOException, WebApplicationException
    {
        // This class uses CsvBeanWriter for converting Java to CSV. It is an open source framework
        // that writes a CSV file by mapping each field on the bean to a column in the CSV file
        // (using the supplied name mapping).

        ICsvBeanWriter writer =
            new CsvBeanWriter( new PrintWriter( entityStream ), CsvPreference.STANDARD_PREFERENCE );

        // No data then return
        if( dataList == null || dataList.size() == 0 )
        {
            return;
        }

        // Columns headers in CSV
        String[] nameMapping = { "id", "name", "location" };

        // CsvBeanWriter writes the header with the property names
        writer.writeHeader( nameMapping );

        for( Object p : dataList )
        {
            // Write each row
            writer.write( p, nameMapping );
        }

        writer.close();
    }
}
