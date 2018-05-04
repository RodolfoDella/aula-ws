package br.com.wedosoft.jaxrs.mapperwapper;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Context
    private Providers providers;

    @Override
    public Response toResponse(PersistenceException exception) {
        if (exception.getCause() == null) {
            return Response.serverError().build();
        }
        Class cause = exception.getCause().getClass();
        ExceptionMapper mapper = providers.getExceptionMapper(cause);
        if (mapper == null) {
            return Response.serverError().build();
        } else {
            return mapper.toResponse(exception.getCause());
        }
    }

}
