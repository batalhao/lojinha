Lojinha = Lojinha || {};

Lojinha.Autocomplete = (function () {

    function Autocomplete() {
        this.skuOuNomeInput = $('.js-sku-nome-produto-input');
        var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
        this.template = Handlebars.compile(htmlTemplateAutocomplete);
        this.emitter = $({});
        this.on = this.emitter.on.bind(this.emitter);
    }

    Autocomplete.prototype.iniciar = function () {
        var options = {
            url: function (skuOuNome) {
                return this.skuOuNomeInput.data('url') + '?skuOuNome=' + skuOuNome;
            }.bind(this),
            getValue: 'nome',
            minCharNumber: 3,
            requestDelay: 300,
            ajaxSettings: {
                contentType: 'application/json'
            },
            template: {
                type: 'custom',
                method: function (nome, produto) {
                    produto.valorFormatado = Lojinha.formatarMoeda(produto.precoVenda);
                    return this.template(produto);
                }.bind(this)
            },
            list: {
                onChooseEvent: onItemSelecionado.bind(this)
            }
        };

        this.skuOuNomeInput.easyAutocomplete(options);
    }

    function onItemSelecionado() {
        this.emitter.trigger('item-selecionado', this.skuOuNomeInput.getSelectedItemData());
        this.skuOuNomeInput.val('');
        this.skuOuNomeInput.focus();
    }

    return Autocomplete

}());

// Lojinha.formatarMoeda = function(valor) {
//     numeral.language('pt-br');
//     return numeral(valor).format('0,0.00');
// };

/* CODIGO MOVIDO PARA venda.tabela-itens.js */
// $(function () {
//     var autocomplete = new Lojinha.Autocomplete();
//     autocomplete.iniciar();
// })