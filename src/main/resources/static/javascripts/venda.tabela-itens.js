Lojinha.TabelaItens = (function () {

    function TabelaItens(autocomplete) {
        this.autocomplete = autocomplete;
        this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
    }

    TabelaItens.prototype.iniciar = function () {
        this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
    }

    function onItemSelecionado(evento, item) {
        var resposta = $.ajax({
            url: 'item',
            method: 'POST',
            data: {
                idProduto: item.id
            }
        });

        resposta.done(function (html) {
            this.tabelaProdutosContainer.html(html);
        }.bind(this));
    }

    return TabelaItens;

}());

$(function () {
    var autocomplete = new Lojinha.Autocomplete();
    autocomplete.iniciar();

    var tabelaItens = new Lojinha.TabelaItens(autocomplete);
    tabelaItens.iniciar();
});
