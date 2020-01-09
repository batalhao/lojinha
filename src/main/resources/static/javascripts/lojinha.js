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

Lojinha.MaskPhoneNumber = (function () {

	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}

	MaskPhoneNumber.prototype.enable = function () {
		var BRMaskBehavior = function (val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		}; 

		var brOptions = {
			onKeyPress: function (val, e, field, options) {
				field.mask(BRMaskBehavior.apply({}, arguments), options);
			}
		};

		this.inputPhoneNumber.mask(BRMaskBehavior, brOptions);
	}

	return MaskPhoneNumber;

}());

Lojinha.MaskDate = (function () {
	
	function MaskDate () {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function () {
		this.inputDate.mask('00/00/0000'); 
		this.inputDate.datepicker({
			orientation : 'bottom',
			language : 'pt-BR',
			autoclose : true
		});
	}
	
	return MaskDate;
}());

$(function () {
	var maskMoney = new Lojinha.MaskMoney();
	maskMoney.enable();

	var maskPhoneNumber = new Lojinha.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskDate = new Lojinha.MaskDate();
	maskDate.enable();
}); 