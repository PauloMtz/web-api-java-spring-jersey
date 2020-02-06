package api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import api.dominio.Endereco;

@Component
public class JdbcEnderecoDao implements EnderecoDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void cadastrar(Endereco endereco, String idContato) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into enderecos ");
        sql.append("(estado, cidade, bairro, logradouro, id_contato) ");
        sql.append("values (:estado, :cidade, :bairro, :logradouro, :idContato)");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idContato", idContato);
        parametros.put("estado", endereco.getEstado());
        parametros.put("cidade", endereco.getCidade());
        parametros.put("bairro", endereco.getBairro());
        parametros.put("logradouro", endereco.getLogradouro());

        jdbcTemplate.update(sql.toString(), parametros);
    }

    @Override
    public Endereco consultar(String idContato) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * ");
        sql.append("from enderecos ");
        sql.append("where id_contato= :id");

        MapSqlParameterSource params = new MapSqlParameterSource("id", idContato);

        return jdbcTemplate.queryForObject(sql.toString(), params, new RowMapper<Endereco>() {
            @Override
            public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
                Endereco endereco = new Endereco();
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setLogradouro(rs.getString("logradouro"));

                return endereco;
            }
        });
    }

    @Override
    public void remover(String idContato) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from enderecos ");
        sql.append("where id_contato = :idContato");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idContato", idContato);

        jdbcTemplate.update(sql.toString(), parametros);
    }

}