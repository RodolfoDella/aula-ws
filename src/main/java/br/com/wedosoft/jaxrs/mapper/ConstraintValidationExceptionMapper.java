package br.com.wedosoft.jaxrs.mapper;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

@Provider
public class ConstraintValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Erros de validação na entidade.");

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String attribute = String.format("%s.%s", violation.getRootBeanClass().getSimpleName(),
                    violation.getPropertyPath().toString());
            errorMessage.addDetailMessage(attribute, violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

}