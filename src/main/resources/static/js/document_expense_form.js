define(["jquery", "bootstrap", "bootstrap_select"], function($, bootstrap_select) {
$(document).ready(function(e) {
	  $('.selectpicker').selectpicker();
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

function deleteItem(){
	$("#delete-item-button").parent().remove();
}