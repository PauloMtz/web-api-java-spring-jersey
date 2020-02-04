package api.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("teste")
public class TesteEndpoint {

	@GET
	public Response teste() {
		return Response.ok("Teste bem sucedido").build();
	}
}
