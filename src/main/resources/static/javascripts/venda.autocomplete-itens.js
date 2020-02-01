Lojinha = Lojinha || {};

Lojinha.Autocomplete = (function () {

    function Autocomplete() {
        this.skuOuNomeInput = $('.js-sku-nome-produto-input');
        var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
        this.template = Handlebars.compile(htmlTemplateAutocomplete);
    }

    Autocomplete.prototype.iniciar = function () {
        var options = {
            url: function (skuOuNome) {
                return '/lojinha/produtos?skuOuNome=' + skuOuNome;
            },
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
            }
        };

        this.skuOuNomeInput.easyAutocomplete(options);
    }

    return Autocomplete

}());

// Lojinha.formatarMoeda = function(valor) {
//     numeral.language('pt-br');
//     return numeral(valor).format('0,0.00');
// };

$(function () {
    var autocomplete = new Lojinha.Autocomplete();
    autocomplete.iniciar();
})