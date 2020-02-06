package api.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import api.dominio.Contato;
import api.negocio.RegrasContatos;
import api.representacao.ContatoRep;

@Path("listaDeContatos")
public class ListaContatosEndpoint {

    @Autowired
    private RegrasContatos regrasContatos;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarContato(ContatoRep contato) {

        // try {
        Contato contatoDominio = contato.converterParaDominio();
        regrasContatos.cadastrar(contatoDominio);
        ContatoRep contatoCadastrado = new ContatoRep(contatoDominio);

        return Response.ok(contatoCadastrado).build();
        // }

        // catch (IdadeContatoException e) {
        // return
        // Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        // }

        // Try catch desativado pois existe um provider atendendo a excecao
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response carregarListaContatos() {
        List<Contato> lista = regrasContatos.listarTodos();
        List<ContatoRep> representacoes = new ArrayList<>();

        for (Contato contato : lista) {
            representacoes.add(new ContatoRep(contato));
        }

        return Response.ok(representacoes).build();
    }

}
