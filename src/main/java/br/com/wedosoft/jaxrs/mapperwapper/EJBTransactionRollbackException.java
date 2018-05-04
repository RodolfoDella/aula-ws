package br.com.wedosoft.jaxrs.mapperwapper;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class EJBTransactionRollbackException implements ExceptionMapper<EJBTransactionRolledbackException> {

    @Context
    private Providers providers;

    public Response toResponse(EJBTransactionRolledbackException exception) {
        if (exception.getCausedByException() == null) {
            return Response.serverError().build();
        }

        Class<? extends Exception> cause = exception.getCausedByException().getClass();
        ExceptionMapper mapper = providers.getExceptionMapper(cause);
        if (mapper == null) {
            return Response.serverError().build();
        } else {
            return mapper.toResponse(exception.getCausedByException());
        }
    }
}
