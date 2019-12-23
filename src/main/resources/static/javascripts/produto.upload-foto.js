$(function () {
	var settings = {
		type: 'json',
		filelimit: 1,
		allow: '*.(jpg|jpeg|png)',
		action: $('.js-container-foto-produto').data('url-fotos'), /* '/lojinha/fotos', */
		complete: function (resposta) {
			/* console.log("resposta", resposta); */

			/* $('input[name=foto]').val(resposta.nome);
			$('input[name=contentType]').val(resposta.contentType); */

			var inputNomeFoto = $('input[name=foto]');
			var inputContentType = $('input[name=contentType]');

			var htmlFotoProdutoTemplate = $('#foto-produto').html();
			var template = Handlebars.compile(htmlFotoProdutoTemplate);
			var htmlFotoProduto = template({ nomeFoto: resposta.nome });

			var containerFotoProduto = $('.js-container-foto-produto');

			var uploadDrop = $('#upload-drop');

			inputNomeFoto.val(resposta.nome);
			inputContentType.val(resposta.contentType);

			uploadDrop.addClass('hidden');
			containerFotoProduto.append(htmlFotoProduto);

			$('.js-remove-foto').on('click', function () {
				$('.js-foto-produto').remove();
				uploadDrop.removeClass('hidden');
				inputNomeFoto.val('');
				inputContentType.val('');
			});

		}
	};

	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop($('#upload-drop'), settings);
});