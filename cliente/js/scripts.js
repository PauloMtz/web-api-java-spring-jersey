// ------------------------------------------------------------------------------------
// 						*** LISTAR REGISTROS ***
// ------------------------------------------------------------------------------------

var listarTodosContatos = function() {
  pathLista = $('#apiPath').val()+"/listaDeContatos";
  pathContato = $('#apiPath').val()+"/contato?idContato=";

  $.ajax({
    url: pathLista,
    type: 'GET',
    async: true,
    contentType: 'application/json',
    success: function(contatos) {
      limparTabela();
      $.each(contatos, function(index, contato) {
        var novaLinha =
          '<tr>' +
          '<td>' + contato.nome + '</td>' +
          '<td>' + contato.email + '</td>' +
          '<td>' + contato.cpf + '</td>' +
          '<td><a class="btn btn-default" style="border:1px solid #ccc;width:150px" href="#" onclick=consultar("'+pathContato+contato.id+'")>consultar</a></td>' +
          '<td><a class="btn btn-primary" style="width:150px" href="#" onclick=alterar("'+pathContato+contato.id+ '")>alterar</a></td>' +
          '<td><a class="btn btn-danger" style="width:150px" href="#" onclick=remover("'+pathContato+contato.id+ '")>remover</a></td>' +
          '</tr>';
        $("#tabelaContatos tr:last").after(novaLinha);
      });
    },
    error: function() {

    }
  });
};

var limparTabela = function() {
	$("#tabelaContatos").find("tr:gt(0)").remove();
}

var consultar = function(urlContato) {
	sessionStorage.setItem("urlContato", urlContato);
	window.location.href = "consulta.html";
}

var alterar = function(urlContato) {
	sessionStorage.setItem("urlContato", urlContato);
	window.location.href = "alteracao.html";
}

// ** REMOVER REGISTRO **
// ** --------------------- **
var remover = function(urlContato) {
	$.ajax({
		url : urlContato,
		type : 'DELETE',
		async : true,
		success : function() {
			listarTodosContatos();
		}
	});
}
// ** --------------------- **

$(document).ready(function() {
	$("#botaoListarTodos").click(function() {
		listarTodosContatos();
	});

	$("#botaoCadastrar").click(function() {
		window.location.href = "cadastro.html";
	});
});

// ------------------------------------------------------------------------------------
// *** CADASTRAR REGISTROS ***
// ------------------------------------------------------------------------------------

var cadastrar = function() {
	var dadosContato = {
		nome : $("#nome").val(),
		email : $("#email").val(),
		cpf : $("#cpf").val(),
		telefone : $("#telefone").val(),
		dataNascimento : $("#datan").val(),
		estado : $("#estado").val(),
		cidade : $("#cidade").val(),
		bairro : $("#bairro").val(),
		logradouro : $("#logradouro").val()
	};
	$.ajax({
		url : "http://localhost:8080/agenda-api/listaDeContatos",
		type : 'POST',
		async : true,
		contentType : 'application/json',
		data : JSON.stringify(dadosContato),
		success : function() {
			$("#resultado").empty();
			$("#resultado").append("Contato cadastrado com sucesso")

		},
		error : function(xhr, status, error) {
			$("#resultado").empty();
			$("#resultado").append("Erro ao cadastrar: " + xhr.responseText)
		}
	});
};
$(document).ready(function() {
	$("#botaoCadastrar").click(function() {
		cadastrar();
	});
});
