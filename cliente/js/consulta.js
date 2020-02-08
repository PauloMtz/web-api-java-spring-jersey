var consultarContato = function(urlContato) {
  $.ajax({
    url: urlContato,
    type: 'GET',
    async: true,
    contentType: 'application/json',
    success: function(contato) {
      $("#nome").text(contato.nome);
      $("#email").text(contato.email);
      $("#cpf").text(contato.cpf);
      $("#telefone").text(contato.telefone);
      $("#datan").text(contato.dataNascimento);
      $("#estado").text(contato.estado);
      $("#cidade").text(contato.cidade);
      $("#bairro").text(contato.bairro);
      $("#logradouro").text(contato.logradouro)
    },
    error: function() {

    }
  });
};
$(document).ready(function() {
  consultarContato(sessionStorage.getItem('urlContato'));
});
