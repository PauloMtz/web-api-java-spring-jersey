package api.negocio;

import java.util.List;

import api.dominio.Contato;

public interface RegrasContatos {

	void cadastrar(Contato contato);
	List<Contato> listarTodos();
	Contato consultar(String idContato);
	public void alterar(Contato contato);
	public void remover(String idContato);
}
