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

function addItem(){
	$.ajax({
		type: "GET",
		url: "/apply/iteminput",
		success: function(responseHTML) {
			
        	$("#add-item-button").before("<div>" + responseHTML + "</div>");
        },
        error: function(response, status) {
            console.log("failed to get item input");
        }
	});
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
	$("#delete-item-button").parent().remove();
}

function showOnlineRequiredInfo(paymode){
	if ("online-payment" === paymode){
		$("#onlineRequiredInfo").css("display","");
	}else{
		$("#onlineRequiredInfo").css("display","none");
	}
}

function showTravelOnlineRequiredInfo(paymode){
	if ("online-payment" === paymode){
		$("#travelOnlineRequiredInfo").css("display","");
	}else{
		$("#travelOnlineRequiredInfo").css("display","none");
	}
}

function showonboardOnlineRequiredInfo(paymode){
	if ("online-payment" === paymode){
		$("#onboardOnlineRequiredInfo").css("display","");
	}else{
		$("#onboardOnlineRequiredInfo").css("display","none");
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