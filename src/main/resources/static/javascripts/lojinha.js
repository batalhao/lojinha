var Lojinha = Lojinha || {};

Lojinha.MaskMoney = (function () {

	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}

	MaskMoney.prototype.enable = function () {
		this.decimal.maskMoney({ precision: 2, thousands: ".", decimal: ",", allowZero: true, allowEmpty: true });
		this.plain.maskMoney({ precision: 0, thousands: "", allowZero: true, allowEmpty: true });
	}

	return MaskMoney;

}());

$(function () {
	var maskMoney = new Lojinha.MaskMoney();
	maskMoney.enable();
}); 