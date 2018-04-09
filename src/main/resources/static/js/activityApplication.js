var page = 0;
function preActivityBudget(){
	page = page - 1;
	$.ajax({
		type: "GET",
		url: "/activitybudget/page/" + page + "/club",
		success: function(responseHTML) {
			var container = $("#activitybudget");
        	container.empty();
        	$("#activitybudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get activity budget page" + page);
        }
	}); 
}

function nextActivityBudget(){
	page = page + 1;
	$.ajax({
		type: "GET",
		url: "/activitybudget/page/" + page + "/club",
		success: function(responseHTML) {
			var container = $("#activitybudget");
        	container.empty();
        	$("#activitybudget").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get activity budget page" + page);
        }
	}); 
}
