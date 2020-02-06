package api.representacao;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import api.dominio.Contato;
import api.dominio.Endereco;
import api.negocio.IdadeContatoException;

public class ContatoRep {

    private String id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String dataNascimento;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;

    public ContatoRep() {

    }

    public ContatoRep(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.email = contato.getEmail();
        this.cpf = contato.getCpf();
        this.telefone = contato.getTelefone();
        this.dataNascimento = serializarData(contato.getDataNascimento());

        if (contato.getEndereco() != null) {
            this.estado = contato.getEndereco().getEstado();
            this.cidade = contato.getEndereco().getCidade();
            this.bairro = contato.getEndereco().getBairro();
            this.logradouro = contato.getEndereco().getLogradouro();
        }
    }

    public Contato converterParaDominio() {
        Contato contato = new Contato();
        contato.setId(this.id);
        contato.setNome(this.nome);
        contato.setEmail(this.email);
        contato.setCpf(this.cpf);
        contato.setTelefone(this.telefone);

        Date dataN = converterData(this.dataNascimento);
        contato.setDataNascimento(dataN);

        Endereco endereco = new Endereco();
        endereco.setEstado(this.estado);
        endereco.setCidade(this.cidade);
        endereco.setBairro(this.bairro);
        endereco.setLogradouro(this.logradouro);
        contato.setEndereco(endereco);
        return contato;
    }

    private Date converterData(String dataTextual) {
        if (StringUtils.isEmpty(dataTextual)) {
            throw new IdadeContatoException("Data de nascimento nao pode ser nula");
        }

        try {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime dataConvertida = dtf.parseDateTime(dataTextual);
            return dataConvertida.toDate();
        } catch (Exception e) {
            throw new IdadeContatoException("Data de nascimento invalida");
        }
    }

    private String serializarData(Date data) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        LocalDateTime dt = new LocalDateTime(data, DateTimeZone.UTC);
        return dtf.print(dt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

}
