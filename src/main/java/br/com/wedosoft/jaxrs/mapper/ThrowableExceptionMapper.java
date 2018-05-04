package br.com.wedosoft.jaxrs.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Apenas para depuração do ExceptionMapper
 */
//@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        System.out.println(getClass().getSimpleName() + " entrou");
        return Response.serverError().build();
    }

}
