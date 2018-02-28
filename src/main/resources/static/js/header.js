function collapseAction(_this) {

	var userManager = _this.id;
	var userFunctionList = $("#" + userManager).next();
	var userFunctionListIcon = $("#" + userManager).children("span");
	if (userFunctionList.hasClass("in")) {
		userFunctionList.removeClass("collapse in");
		userFunctionList.addClass("collapse");
		userFunctionListIcon.removeClass("glyphicon-chevron-down");
		userFunctionListIcon.addClass("glyphicon-chevron-left");

	} else {
		userFunctionList.addClass("collapse in");
		userFunctionList.removeClass("collapse");
		userFunctionListIcon.removeClass("glyphicon-chevron-left");
		userFunctionListIcon.addClass("glyphicon-chevron-down");
	}
}
