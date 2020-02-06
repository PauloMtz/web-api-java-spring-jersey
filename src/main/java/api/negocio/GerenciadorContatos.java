package api.negocio;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import api.dao.ContatoDao;
import api.dao.EnderecoDao;
import api.dominio.Contato;
import api.dominio.Endereco;

@Component
public class GerenciadorContatos implements RegrasContatos {

    private final int IDADE_MINIMA = 18;

    @Autowired
    private ContatoDao contatoDao;

    @Autowired
    private EnderecoDao enderecoDao;

    @Override
    @Transactional
    public void cadastrar(Contato contato) {
        validarDataNascimento(contato.getDataNascimento());
        String idContato = UUID.randomUUID().toString();
        contato.setId(idContato);
        contatoDao.cadastrar(contato);
        enderecoDao.cadastrar(contato.getEndereco(), idContato);

    }

    private void validarDataNascimento(Date dataNascimento) {
        DateTime dateTimeDn = new DateTime(dataNascimento);
        DateTime hoje = new DateTime();
        int idade = Years.yearsBetween(dateTimeDn, hoje).getYears();
        if (idade < IDADE_MINIMA) {
            String msgErro = "Contato com menos de %s anos";
            msgErro = String.format(msgErro, IDADE_MINIMA);
            throw new IdadeContatoException(msgErro);
        }
    }

    @Override
    public List<Contato> listarTodos() {
        return contatoDao.listarTodos();
    }

    @Override
    public Contato consultar(String idContato) {
        Contato contato = contatoDao.consultar(idContato);
        Endereco endereco = enderecoDao.consultar(idContato);
        contato.setEndereco(endereco);
        return contato;
    }

    @Override
    @Transactional
    public void alterar(Contato contato) {
        validarDataNascimento(contato.getDataNascimento());
        contatoDao.alterar(contato);
        enderecoDao.remover(contato.getId());
        enderecoDao.cadastrar(contato.getEndereco(), contato.getId());
    }

    @Override
    @Transactional
    public void remover(String idContato) {
        enderecoDao.remover(idContato);
        contatoDao.remover(idContato);
    }

}
