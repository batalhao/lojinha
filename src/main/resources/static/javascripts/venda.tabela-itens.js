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

        // resposta.done(function (html) {
        //     this.tabelaProdutosContainer.html(html);
        //     $('.js-tabela-produto-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
        // }.bind(this));

        resposta.done(onItemAtualizadoNoServidor.bind(this));
    }

    function onItemAtualizadoNoServidor(html) {
        this.tabelaProdutosContainer.html(html);
        $('.js-tabela-produto-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
    }

    function onQuantidadeItemAlterado(evento) {
        var input = $(evento.target);
        var quantidade = input.val();
        var idProduto = input.data('id-produto');

        var resposta = $.ajax({
            url: 'item/' + idProduto,
            method: 'PUT',
            data: {
                quantidade: quantidade
            }
        });

        resposta.done(onItemAtualizadoNoServidor.bind(this));
    }

    return TabelaItens;

}());

$(function () {
    var autocomplete = new Lojinha.Autocomplete();
    autocomplete.iniciar();

    var tabelaItens = new Lojinha.TabelaItens(autocomplete);
    tabelaItens.iniciar();
});
