package br.com.wedosoft.jaxrs.mapper;

import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintValidationExceptionMapperHibernate implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Erros de validação na entidade.");

        String violations = e.getConstraintName();


        errorMessage.addDetailMessage("Constraint Error", violations);
        errorMessage.addDetailMessage("Localização do erro:", e.getLocalizedMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

}
