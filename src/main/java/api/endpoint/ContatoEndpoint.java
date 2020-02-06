package api.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import api.dominio.Contato;
import api.negocio.RegrasContatos;
import api.representacao.ContatoRep;

@Path("contato")
public class ContatoEndpoint {

    @Autowired
    private RegrasContatos regrasContatos;

    @QueryParam("idContato")
    private String idContato;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterContato() {
        Contato contato = regrasContatos.consultar(idContato);
        return Response.ok(new ContatoRep(contato)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarContato(ContatoRep contato) {
        contato.setId(idContato);
        Contato contatoDominio = contato.converterParaDominio();
        regrasContatos.alterar(contatoDominio);
        return Response.ok(new ContatoRep(contatoDominio)).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response removerContato() {
        regrasContatos.remover(idContato);
        return Response.ok("Contato removido com sucesso").build();
    }

}
