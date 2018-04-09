define(["jquery", "bootstrap"], function($) {

});


var page = 0;
function preMedicalBudget(){
	page = page - 1;
	$.ajax({
		type: "GET",
		url: "/medicalbudget/page/" + page,
		success: function(responseHTML) {
			var container = $("#medicalBudget");
        	container.empty();
        	$("#medicalBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get medical budget page" + page);
        }
	}); 
}

function nextMedicalBudget(){
	page = page + 1;
	$.ajax({
		type: "GET",
		url: "/medicalbudget/page/" + page,
		success: function(responseHTML) {
			var container = $("#medicalBudget");
        	container.empty();
        	$("#medicalBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get medical budget page" + page);
        }
	}); 
}

function searchMedicalBudget() {
	var id = $("#studentId").val();
	$.ajax({
		type: "GET",
		url: "/medicalbudget/" +  id,
		success: function(responseHTML) {
			var container = $("#medicalBudget");
        	container.empty();
        	$("#medicalBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get student user" + id);
        }
	});
}
var page1 = 0;
function preDailyBudget(){
	page1 = page1 - 1;
	$.ajax({
		type: "GET",
		url: "/dailybudget/page/" + page1,
		success: function(responseHTML) {
			var container = $("#dailyBudget");
        	container.empty();
        	$("#dailyBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get daily budget page" + page1);
        }
	}); 
}

function nextDailyBudget(){
	page1 = page1 + 1;
	$.ajax({
		type: "GET",
		url: "/dailybudget/page/" + page1,
		success: function(responseHTML) {
			var container = $("#dailyBudget");
        	container.empty();
        	$("#dailyBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get daily budget page" + page1);
        }
	}); 
}

function searchDailyBudget() {
	var id = $("#teacherId").val();
	$.ajax({
		type: "GET",
		url: "/dailybudget/" +  id,
		success: function(responseHTML) {
			var container = $("#dailyBudget");
        	container.empty();
        	$("#dailyBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get teacher user" + id);
        }
	});
}


var page2 = 0;
function preActivityBudgetFinance(){
	page2 = page2 - 1;
	$.ajax({
		type: "GET",
		url: "/activitybudget/page/" + page2 + "/finance",
		success: function(responseHTML) {
			var container = $("#activityBudget");
        	container.empty();
        	$("#activityBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get activity budget page" + page2);
        }
	}); 
}

function nextActivityBudgetFinance(){
	page2 = page2 + 1;
	$.ajax({
		type: "GET",
		url: "/activitybudget/page/" + page2 + "/finance",
		success: function(responseHTML) {
			var container = $("#activityBudget");
        	container.empty();
        	$("#activityBudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get activity budget page" + page2);
        }
	}); 
}


	 