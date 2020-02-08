var consultarContato = function(urlContato) {
  $.ajax({
    url: urlContato,
    type: 'GET',
    async: true,
    contentType: 'application/json',
    success: function(contato) {
      $("#nome").val(contato.nome);
      $("#email").val(contato.email);
      $("#cpf").val(contato.cpf);
      $("#telefone").val(contato.telefone);
      $("#datan").val(contato.dataNascimento);
      $("#estado").val(contato.estado);
      $("#cidade").val(contato.cidade);
      $("#bairro").val(contato.bairro);
      $("#logradouro").val(contato.logradouro)
    },
    error: function() {}
  });
};

var alterar = function(urlContato) {
  var dadosContato = {
    nome: $("#nome").val(),
    email: $("#email").val(),
    cpf: $("#cpf").val(),
    telefone: $("#telefone").val(),
    dataNascimento: $("#datan").val(),
    estado: $("#estado").val(),
    cidade: $("#cidade").val(),
    bairro: $("#bairro").val(),
    logradouro: $("#logradouro").val()
  };
  $.ajax({
    url: urlContato,
    type: 'PUT',
    async: true,
    contentType: 'application/json',
    data: JSON.stringify(dadosContato),
    success: function() {
      $("#resultado").empty();
      $("#resultado").append("Contato alterado com sucesso")
    },
    error: function(xhr, status, error) {
      $("#resultado").empty();
      $("#resultado").append("Erro ao alterar: " + xhr.responseText)
    }
  });
};
$(document).ready(function() {
  consultarContato(sessionStorage.getItem('urlContato'));
  $("#botaoAlterar").click(function() {
    alterar(sessionStorage.getItem('urlContato'));
  });
});
