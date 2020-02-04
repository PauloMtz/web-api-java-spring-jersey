package api.dao;

import java.util.List;

import api.dominio.Contato;

public interface ContatoDao {

	void cadastrar(Contato contato);
	void alterar(Contato contato);
	void remover(String idContato);
	Contato consultar(String idContato);
	List<Contato> listarTodos();
}
