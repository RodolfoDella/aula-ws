/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class PartialJsonObjectPatchReader implements MessageBodyReader<ObjectPatch> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations,
                              MediaType mediaType) {
        return ObjectPatch.class == type && MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType);
    }

    @Override
    public ObjectPatch readFrom(Class<ObjectPatch> type, Type genericType, Annotation[] annotations,
                                MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {

        JsonNode patch = OBJECT_MAPPER.readTree(entityStream);
        PartialJsonObjectPatch parser = new PartialJsonObjectPatch();
        parser.setObjectMapper(OBJECT_MAPPER);
        parser.setPatch(patch);
        return parser;
    }

}