$(function () {

	var modal = $('#modalCadastroRapidoFabricante');
	var botaoSalvar = modal.find('.js-modal-cadastro-fabricante-salvar-botao');
	var form = modal.find('form');
	var inputNomeFabricante = $('#nomeFabricante');
	var inputDocumentoFabricante = $('#documentoFabricante');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-fabricante');

	form.on('submit', function (event) { event.preventDefault() });

	var url = form.attr('action');

	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', OnModalClose);
	botaoSalvar.on('click', onBotaoSalvarClick);

	function onModalShow() {
		inputNomeFabricante.focus();
	}

	function OnModalClose() {
		inputNomeFabricante.val('');
		inputDocumentoFabricante.val('');

		document.getElementById('tipoPessoaF').checked = false;
		document.getElementById('tipoPessoaJ').checked = false;

		containerMensagemErro.removeClass('hidden').addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		var nomeFabricante = inputNomeFabricante.val().trim();
		var documentoFabricante = inputDocumentoFabricante.val().trim();

		if (document.getElementById('tipoPessoaJ').checked) {
			tipoFabricante = 'J';
		} else if (document.getElementById('tipoPessoaF').checked) {
			tipoFabricante = 'F';
		} else {
			tipoFabricante = '';
		}


		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ 'nome': nomeFabricante, 'documento': documentoFabricante, 'tipoPessoa': tipoFabricante, 'ativo': true }),
			error: onErrorSalvandoFabricante,
			success: OnFabricanteSaldo
		});
	}

	function onErrorSalvandoFabricante(obj) {
		containerMensagemErro.removeClass('hidden');

		var mensagemErro = "";
		for (var i = 0; i < obj.responseJSON.length; i++) {
			mensagemErro += '<div><i class="fa  fa-exclamation-circle"></i> <span>' + obj.responseJSON[i] + '</span></div>';
		}

		containerMensagemErro.html(mensagemErro);
		// form.find('.form-group').addClass('has-error');
	}

	function OnFabricanteSaldo(fabricante) {
		var comboFabricante = $('#fabricante');
		comboFabricante.append('<option value=' + fabricante.id + '>' + fabricante.nome + '</option>');
		comboFabricante.val(fabricante.id);
		modal.modal('hide');
	}

});