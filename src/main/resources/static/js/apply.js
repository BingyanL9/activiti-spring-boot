define(["jquery", "bootstrap", "bootstrap_select", "bootstrap_datetimepicker"], function($, bootstrap_select, bootstrap_datetimepicker) {
$(document).ready(function(e) {
	  $('.selectpicker').selectpicker();
	  
	    $('.form_date').datetimepicker({  
	        language:  'fr',  
	        weekStart: 1,  
	        todayBtn:  1,  
	        autoclose: 1,  
	        todayHighlight: 1,  
	        startView: 2,  
	        minView: 2,  
	        forceParse: 0  
	    });
	});
});
var itemIndex = 1;
function addItem(){
	$.ajax({
		type: "GET",
		url: "/apply/iteminput/" + itemIndex++,
		success: function(responseHTML) {
			
        	$("#add-item-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get item input");
        }
	});
	$("#add-item-button").prev().find('button').remove();
}

var voucherIndex = 1;
function addVoucher(){
	$.ajax({
		type: "GET",
		url: "/apply/voucherinput/" + voucherIndex++,
		success: function(responseHTML) {
			
        	$("#add-voucher-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get item input");
        }
	});
	$("#add-voucher-button").prev().find('button').remove();
}

function deleteVoucher(){
	var deleteButton = $("#add-voucher-button").prev().find('button');
	$("#delete-voucher-button").parent().remove();
	$("#add-voucher-button").prev().append(deleteButton);
	voucherIndex--;
}

function addTrafficInput(){
	$.ajax({
		type: "GET",
		url: "/apply/trafficinput",
		success: function(responseHTML) {
			
        	$("#add-traffic-input-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get traffic input");
        }
	});
}

function deleteItem(){
	var deleteButton = $("#add-item-button").prev().find('button');
	$("#delete-item-button").parent().remove();
	$("#add-item-button").prev().append(deleteButton);
	itemIndex--;
}

function showRequiredInfo(paymode){
	if("cash" === paymode || " " === paymode){
		$(".paymentInfo").css("display","none");
	}else{
		$(".paymentInfo").css("display","");
	}
}

function showExpenseByType(expense_type){
	if ("document_expense_form" === expense_type){
		$("#document_expense_form").css("display","");
		$("#travel_expense_form").css("display","none");
		$("#city_traffic_expense_form").css("display","none");
		$("#onboard_travel_expense_form").css("display","none");
	}else if("travel_expense_form" === expense_type){
		$("#document_expense_form").css("display","none");
		$("#travel_expense_form").css("display","");
		$("#city_traffic_expense_form").css("display","none");
		$("#onboard_travel_expense_form").css("display","none");
	}else if("city_traffic_expense_form" === expense_type){
		$("#document_expense_form").css("display","none");
		$("#travel_expense_form").css("display","none");
		$("#city_traffic_expense_form").css("display","");
		$("#onboard_travel_expense_form").css("display","none");
	}else if("onboard_travel_expense_form" === expense_type){
		$("#document_expense_form").css("display","none");
		$("#travel_expense_form").css("display","none");
		$("#city_traffic_expense_form").css("display","none");
		$("#onboard_travel_expense_form").css("display","");
	}
}

function expenseTypeAction(expenseType) {
	if (expenseType === "ActivityExpense"){
		$("#activityName").css("display","");
		$("#medicalExpenseInfo").css("display","none");
	}else if(expenseType === "MedicalExpense"){
		$("#medicalExpenseInfo").css("display","");
		$("#activityName").css("display","none");
	}else{
		$("#medicalExpenseInfo").css("display","none");
		$("#activityName").css("display","none");
	}
}

function getFileUrl(_this) {
	var oFReader = new FileReader();
	var file = _this.files[0];
	oFReader.readAsDataURL(file);
	oFReader.onloadend = function(oFRevent) {
		var src = oFRevent.target.result;
		_this.nextElementSibling.value = src;
	}
} 
 
