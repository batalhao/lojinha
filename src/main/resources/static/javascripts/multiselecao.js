Lojinha = Lojinha || {};

Lojinha.MultiSelecao = (function() {

    function MultiSelecao() {
        this.statusBtn = $('.js-status-btn');
        this.selecaoCheckbox = $('.js-selecao');
    }

    MultiSelecao.prototype.iniciar = function() {
        this.statusBtn.on('click', onStatusBtnClicado.bind(this));
    }

    function onStatusBtnClicado(event) {
        var botaoClicado = $(event.currentTarget);
        var status = botaoClicado.data('status');

        var checkBoxSelecionados = this.selecaoCheckbox.filter(':checked');
        var ids = $.map(checkBoxSelecionados, function(c) {
            return $(c).data('id');
        });

        if (ids.length > 0) {
            $.ajax({
                url: '/lojinha/usuarios/status',
                method: 'PUT',
                data: {
                    ids: ids,
                    status: status
                },
                success: function() {
                    window.location.reload();
                }
            });

        }
    }

    return MultiSelecao;

}());

$(function() {
    var multiSelecao = new Lojinha.MultiSelecao();
    multiSelecao.iniciar();
});