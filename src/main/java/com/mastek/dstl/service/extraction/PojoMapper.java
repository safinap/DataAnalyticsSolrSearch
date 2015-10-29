package com.mastek.dstl.service.extraction;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;



public final class PojoMapper
{
    
    /**
     * Instantiates a new pojo mapper.
     */
    private PojoMapper()
    {
        
    }
    
    /** The m. */
    private static ObjectMapper m = new ObjectMapper();
    
    /**
     * Converts String json into Object.
     *
     * @param <T>
     *            the generic type
     * @param jsonAsString
     *            the json as string
     * @param pojoClass
     *            the pojo class
     * @return Object
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) throws IOException
    {
        return m.readValue(jsonAsString, pojoClass);
    }
    
    /**
     * Gets the objectfrom json.
     *
     * @param <T>
     *            the generic type
     * @param jsonAsString
     *            the json as string
     * @param pojoClass
     *            the pojo class
     * @return the objectfrom json
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static <T> Object getObjectfromJson(Object jsonAsString, Class<T> pojoClass) throws IOException
    {
        return fromJson(createJsonString(jsonAsString), pojoClass);
    }
    
    /**
     * Creates the json string.
     *
     * @param object
     *            the object
     * @return the string
     */
    public static String createJsonString(Object object)
    {
        StringWriter sw = new StringWriter();
        try
        {
            // serialize
            ObjectMapper mapper = new ObjectMapper();
            MappingJsonFactory jsonFactory = new MappingJsonFactory();
            JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
            mapper.writeValue(jsonGenerator, object);
            sw.close();
        }
        catch (Exception e)
        {
            
        }
        return sw.getBuffer().toString();
    }
}
