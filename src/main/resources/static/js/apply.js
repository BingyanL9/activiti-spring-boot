define(["jquery", "bootstrap", "bootstrap_select", "bootstrap_datetimepicker"], function($, bootstrap_select, bootstrap_datetimepicker) {

	dateConfiguration = function(){
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
	};	
	
	$(document).ready(function(e) {
	  $('.selectpicker').selectpicker();
	  dateConfiguration();

	});
	
	addTrafficInput = function(){
		$.ajax({
			type: "GET",
			url: "/apply/trafficinput/" + itemIndex++,
			success: function(responseHTML) {
	        	$("#add-traffic-input-button").before("<div>" + responseHTML + "</div>");
	        },
	        error: function(response, status) {
	            console.log("failed to get traffic input");
	        }
		});
		$("#add-traffic-input-button").prev().find('button').remove();
	};
	
});

var itemIndex = 1;
var EXPENSETYPE;
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

function addTrafficVoucher(){
	$.ajax({
		type: "GET",
		url: "/apply/voucherinput/" + voucherIndex++,
		success: function(responseHTML) {
			
        	$("#add-traffic-voucher-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get item input");
        }
	});
	$("#add-traffic-voucher-button").prev().find('button').remove();
}

function addTravelVoucher(){
	$.ajax({
		type: "GET",
		url: "/apply/voucherinput/" + voucherIndex++,
		success: function(responseHTML) {
			
        	$("#add-travel-voucher-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get item input");
        }
	});
	$("#add-travel-voucher-button").prev().find('button').remove();
}


function addTravelInput(){
	$.ajax({
		type: "GET",
		url: "/apply/travelinput/" + itemIndex++,
		success: function(responseHTML) {
        	$("#add-travel-input-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get travel input");
        }
	});
	$("#add-travel-input-button").prev().find('button').remove();
}

var accommodationIndex = 1;
function addAccommodationInput() {
	$.ajax({
		type: "GET",
		url: "/apply/accommodationinput/" + accommodationIndex++,
		success: function(responseHTML) {
        	$("#add-accommodation-input-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get accommodation input");
        }
	});
	$("#add-accommodation-input-button").prev().find('button').remove();
}
var otherItem = 1;
function addOtherInput() {
	$.ajax({
		type: "GET",
		url: "/apply/otherinput/" + otherItem++,
		success: function(responseHTML) {
        	$("#add-other-input-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get other input");
        }
	});
	$("#add-other-input-button").prev().find('button').remove();
}

var allowanceItem = 2;
function addAllowanceInput() {
	$.ajax({
		type: "GET",
		url: "/apply/allowanceinput/" + allowanceItem++,
		success: function(responseHTML) {
        	$("#add-allowance-input-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get other input");
        }
	});
	$("#add-allowance-input-button").prev().find('button').remove();
}

function deleteVoucher(){
	if (EXPENSETYPE === 'document_expense_form'){
	 var deleteButton = $("#add-voucher-button").prev().find('button');
	 $("#delete-voucher-button").parent().remove();
	 $("#add-voucher-button").prev().append(deleteButton);
	}else if(EXPENSETYPE === 'city_traffic_expense_form'){
	var deleteTrafficButton = $("#add-traffic-voucher-button").prev().find('button');
	$("#delete-voucher-button").parent().remove();
	$("#add-traffic-voucher-button").prev().append(deleteButton);
	}else if(EXPENSETYPE === 'travel_expense_form'){
	var deleteTravelButton = $("#add-travel-voucher-button").prev().find('button');
	$("#delete-voucher-button").parent().remove();
	$("#add-travel-voucher-button").prev().append(deleteTravelButton);	
	}
	voucherIndex--;
}


function deleteItem(){
	var deleteButton = $("#add-item-button").prev().find('button');
	$("#delete-item-button").parent().remove();
	$("#add-item-button").prev().append(deleteButton);
	itemIndex--;
}

function deleteTrafficItem() {
	var deleteButton = $("#add-traffic-input-button").prev().find('button');
	$("#delete-traffic-item-button").parent().remove();
	$("#add-traffic-input-button").prev().append(deleteButton);
	itemIndex--;
}

function deleteTravelItem() {
	var deleteButton = $("#add-travel-input-button").prev().find('button');
	$("#delete-travel-item-button").parent().remove();
	$("#add-travel-input-button").prev().append(deleteButton);
	itemIndex--;
}

function deleteAccommodationItem() {
	var deleteButton = $("#add-accommodation-input-button").prev().find('button');
	$("#delete-accommodation-item-button").parent().remove();
	$("#add-accommodation-input-button").prev().append(deleteButton);
	accommodationIndex--;
}

function deleteOtherItem() {
	var deleteButton = $("#add-other-input-button").prev().find('button');
	$("#delete-other-item-button").parent().remove();
	$("#add-other-input-button").prev().append(deleteButton);
	accommodationIndex--;
}

function deleteAllowanceItem() {
	var deleteButton = $("#add-allowance-input-button").prev().find('button');
	$("#delete-allowance-item-button").parent().remove();
	$("#add-allowance-input-button").prev().append(deleteButton);
	accommodationIndex--;
}

function showRequiredInfo(paymode){
	if("cash" === paymode || " " === paymode){
		$(".paymentInfo").css("display","none");
		$("#payeePerson").css("display","");
	}else{
		$(".paymentInfo").css("display","");
		$("#payeePerson").css("display","none");
	}
}

function showExpenseByType(expense_type){
	EXPENSETYPE = expense_type;
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
 
