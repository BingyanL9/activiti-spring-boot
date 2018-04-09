function getApplicationById(id) {
	$.ajax({
		type: "GET",
		url: "/applications/" + id,
		success: function(responseHTML) {
			var container = $("#applicationInfoInApproval");
        	container.empty();
        	$("#applicationInfoInApproval").prepend(responseHTML);
        },
        error: function(response, status) {
            console.log("failed to get application" + id);
        }
	});
}