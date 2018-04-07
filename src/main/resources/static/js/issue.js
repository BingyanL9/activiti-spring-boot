function getMessageDelete(id) {
	$.ajax({
		type: "GET",
		url: "/messages/" + id,
		success: function(responseHTML) {
			var container = $("#messageDelete");
        	container.empty();
        	$("#messageDelete").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get message" + id);
        }
	});
}

var deletePage = 0;
function getPreviousDelete(){
	deletePage = deletePage -1 ;
	$.ajax({
		type: "GET",
		url: "/messages/page/" +  deletePage + "/issue",
		success: function(responseHTML) {
			var container = $("#messageListDelete");
        	container.empty();
        	$("#messageListDelete").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get messagelist page" + deletePage);
        }
	});
}

function getNextDelete(){
	deletePage = deletePage + 1;
	$.ajax({
		type: "GET",
		url: "/messages/page/" + deletePage + "/issue",
		success: function(responseHTML) {
			var container = $("#messageListDelete");
        	container.empty();
        	$("#messageListDelete").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get messagelist page" + deletePage);
        }
	});
}

function deleteMessage(id) {
 $.ajax({
		type: "DELETE",
		url: '/messages/' + id,
		success: function(responseHTML) {
			var container = $("#messageListDelete");
        	container.empty();
        	$("#messageListDelete").prepend(responseHTML);
        },
        error: function(data, status) {
            console.log("failed to delete messages:" + id);
        }
	});
}