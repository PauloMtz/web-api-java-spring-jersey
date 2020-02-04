package api.dao;

import api.dominio.Endereco;

public interface EnderecoDao {

	void cadastrar(Endereco endereco, String idContato);
	Endereco consultar(String idContato);
	void remover(String idContato);
}
