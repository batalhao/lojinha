$(function () {
	var decimal = $('.js-decimal');
	decimal.maskMoney({ precision: 2, thousands: ".", decimal: "," });

	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0, thousands: "." });
});