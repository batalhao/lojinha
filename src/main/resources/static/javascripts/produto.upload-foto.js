var Lojinha = Lojinha || {};

Lojinha.UploadFoto = (function () {

	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');

		this.htmlFotoProdutoTemplate = $('#foto-produto').html();
		this.template = Handlebars.compile(this.htmlFotoProdutoTemplate);

		this.containerFotoProduto = $('.js-container-foto-produto');

		this.uploadDrop = $('#upload-drop');
	}

	UploadFoto.prototype.iniciar = function () {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerFotoProduto.data('url-fotos'),
			complete: onUploadCompleto.bind(this)
		}

		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);

		if (this.inputNomeFoto.val()) {
			onUploadCompleto.call(this, {
				nome: this.inputNomeFoto.val(),
				contentType: this.inputContentType.val()
			});
		}
	}

	function onUploadCompleto(resposta) {
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);

		this.uploadDrop.addClass('hidden');
		var htmlFotoProduto = this.template({ nomeFoto: resposta.nome });
		this.containerFotoProduto.append(htmlFotoProduto);

		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}

	function onRemoverFoto() {
		$('.js-foto-produto').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}

	return UploadFoto;

})();

$(function () {
	var uploadFoto = new Lojinha.UploadFoto();
	uploadFoto.iniciar();
});
