package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import api.dominio.Contato;
import api.negocio.ContatoNaoEncontradoException;

@Component
public class JdbcContatoDao implements ContatoDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void cadastrar(Contato contato) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into contatos ");
        sql.append("(id, nome, email, cpf, telefone, data_nascimento) ");
        sql.append("values (:id, :nome, :email, :cpf, :tel, :dataN)");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", contato.getId());
        parametros.put("nome", contato.getNome());
        parametros.put("email", contato.getEmail());
        parametros.put("cpf", contato.getCpf());
        parametros.put("tel", contato.getTelefone());
        parametros.put("dataN", contato.getDataNascimento());

        jdbcTemplate.update(sql.toString(), parametros);
    }

    @Override
    public List<Contato> listarTodos() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from contatos");

        return jdbcTemplate.query(sql.toString(), new RowMapper<Contato>() {
            @Override
            public Contato mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contato contato = new Contato();
                contato.setId(rs.getString("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setCpf(rs.getString("cpf"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setDataNascimento(rs.getDate("data_nascimento"));
                return contato;
            }
        });
    }

    @Override
    public Contato consultar(String idContato) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * ");
        sql.append("from contatos ");
        sql.append("where id = :id");

        MapSqlParameterSource params = new MapSqlParameterSource("id", idContato);

        try {

            return jdbcTemplate.queryForObject(sql.toString(), params, new RowMapper<Contato>() {
                @Override
                public Contato mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Contato contato = new Contato();
                    contato.setId(rs.getString("id"));
                    contato.setNome(rs.getString("nome"));
                    contato.setEmail(rs.getString("email"));
                    contato.setCpf(rs.getString("cpf"));
                    contato.setTelefone(rs.getString("telefone"));
                    contato.setDataNascimento(rs.getDate("data_nascimento"));
                    return contato;
                }
            });
        } catch (IncorrectResultSizeDataAccessException e) {
            String msgErro = "Contato nao encontrado";
            throw new ContatoNaoEncontradoException(msgErro);

        }
    }

    @Override
    public void alterar(Contato contato) {
        StringBuilder sql = new StringBuilder();
        sql.append("update contatos set ");
        sql.append("nome= :nome, ");
        sql.append("email= :email, ");
        sql.append("cpf= :cpf, ");
        sql.append("telefone= :telefone, ");
        sql.append("data_nascimento= :dataNascimento ");
        sql.append("where id=:id");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", contato.getId());
        parametros.put("nome", contato.getNome());
        parametros.put("email", contato.getEmail());
        parametros.put("cpf", contato.getCpf());
        parametros.put("telefone", contato.getTelefone());
        parametros.put("dataNascimento", contato.getDataNascimento());

        int update = jdbcTemplate.update(sql.toString(), parametros);

        if (update == 0) {
            throw new ContatoNaoEncontradoException("Contato nao encontrado");
        }
    }

    @Override
    public void remover(String idContato) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from contatos ");
        sql.append("where id = :id");

        MapSqlParameterSource params = new MapSqlParameterSource("id", idContato);

        int removido = jdbcTemplate.update(sql.toString(), params);

        if (removido == 0) {
            String msgErro = "Contato nao encontrado";
            throw new ContatoNaoEncontradoException(msgErro);
        }
    }

}