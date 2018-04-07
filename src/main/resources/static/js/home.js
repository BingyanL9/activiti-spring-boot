function getMessage(id) {
	$.ajax({
		type: "GET",
		url: "/messages/" + id,
		success: function(responseHTML) {
			var container = $("#message");
        	container.empty();
        	$("#message").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get message" + id);
        }
	});
}

var page = 0;
function getPrevious(){
	page = page -1 ;
	$.ajax({
		type: "GET",
		url: "/messages/page/" + page +"/home",
		success: function(responseHTML) {
			var container = $("#messageList");
        	container.empty();
        	$("#messageList").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get messagelist page" + page);
        }
	});
}

function getNext(){
	page = page + 1;
	$.ajax({
		type: "GET",
		url: "/messages/page/" + page +"/home",
		success: function(responseHTML) {
			var container = $("#messageList");
        	container.empty();
        	$("#messageList").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get messagelist page" + page);
        }
	});
}
