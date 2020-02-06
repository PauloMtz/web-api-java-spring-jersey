package api.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.negocio.IdadeContatoException;

@Provider
public class IdadeContatoExceptionHandler implements ExceptionMapper<IdadeContatoException> {

    @Override
    public Response toResponse(IdadeContatoException exception) {
        return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }

}
