package api.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.negocio.ContatoNaoEncontradoException;

@Provider
public class ContatoNaoEncontradoExceptionHandler implements ExceptionMapper<ContatoNaoEncontradoException> {

    @Override
    public Response toResponse(ContatoNaoEncontradoException exception) {
        return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
    }

}
