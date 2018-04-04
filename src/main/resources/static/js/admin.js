define(["jquery", "bootstrap", "jqGrid"], function($, bootstrap, jqGrid){
	$(function(){
		var allStudentUser = [];
		var allTeacherUser = [];
		var allClubUser = [];
		var allActivity = [];
		var allProject = [];
		var allProject_respon = [];
		var allApplication = [];
		var allFeekback = [];
		
		var loadUsersTable = function() {
			$('#studentUserTable').jqGrid({
	            datatype: "local",
	            colNames: [ '学号', '用户名', '密码','邮箱', '银行卡账号', '权限', '学院','医疗预算'],
	            colModel: [
	                {
	                    name: 'userName',
	                    index: 'userName',
	                    editable:true,
	                    sorttype: 'text',
	                    width: 120,
	                    align: 'center'
	                },
	                {
	                    name: 'displayName',
	                    index: 'displayName',
	                    editable: true,
	                    width: 120,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'password',
	                    index: 'password',
	                    editable: true,
	                    width: 120,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'email',
	                    index: 'email',
	                    editable: true,
	                    width: 120,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'cardNum',
	                    index: 'cardNum',
	                    editable: true,
	                    width: 120,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'role',
	                    index: 'role',
	                    editable: true,
	                    width: 120,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
	                        value: "ordinary:普通用户;"
	                    }
	                },
	                {
	                    name: 'college',
	                    index: 'college',
	                    editable: true,
	                    width: 120,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'budget',
	                    index: 'budget',
	                    editable: true,
	                    width: 120,
	                    edittype: "text",
	                    align: 'center'
	                },
	            ],
	            //onSelectRow:editRow,
	            viewrecords: true, // show the current page, data rang and total records on the toolbar
	            height: 400,
	            rowNum: 15,
	            rownumbers: true,
	            autowidth: false,
	            shrinkToFit: true,
	            gridview: true,
	            multiselect: true,
	            pager: "#studentUserTableDiv",
	            rowList: [15, 30, 60],
	            
	        });
	        loadStudentUsersData();
	        $('#studentUserTable').jqGrid('navGrid', "#studentUserTableDiv", {
	            search: false, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#studentUserTable").jqGrid('navButtonAdd', "#studentUserTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	                loadStudentUsersData();
	            }
	        });
	        $("#studentUserTable").navButtonAdd('#studentUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-circle-plus",
	            cellsubmit: "clientArray",
	            title: "增加学生用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$(this).jqGrid('setColProp', 'userName', {editable:true});
	                addStudentUserRow();
	            },
	        });
	        $("#studentUserTable").navButtonAdd('#studentUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-pencil",
	            title: "修改学生用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$(this).jqGrid('setColProp', 'userName', {editable:false});
	            	editStudentUserRow();
	            },
	        });
	        $("#studentUserTable").navButtonAdd('#studentUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteStudentUserRow,
	            position: "last",
	            title: "删除学生用户",
	            cursor: "pointer"
	        });
		};
		
		var loadStudentUsersData = function() {
			$.ajax({
				type: "GET",
				url: "/studentusers",
				success: function(data) {
					console.log("success to load student user!");
					$('#studentUserTable').jqGrid("clearGridData");
					allStudentUser = data;
		            for (var i in allStudentUser) {
		            	allStudentUser[i].rowId = i + 1;
		                $('#studentUserTable').jqGrid('addRowData',allStudentUser[i].userName, allStudentUser[i]);
		            }
		            $('#studentUserTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load student user!");
		        }
			});
	    };
	    
	    var addStudentUserRow = function() {
	        $("#studentUserTable").jqGrid('editGridRow', "new", {
	        	url: 'clientArray',
	            cellsubmit: 'clientArray',
	            reloadAfterSubmit: false,
	            closeAfterAdd: true,
	            closeOnEscape: true,
	            modal: true,
	            width:436,
	            dataheight: 356,
	            saveicon: [false,"",""],
	            closeicon: [false,"",""],
	            onclickSubmit: function(params, postdata) {
	            	$.ajax({
	    				type: "POST",
	    				url: '/studentusers?userName=' + postdata.userName + '&displayName=' + postdata.displayName 
	    				+ '&password=' + postdata.password + '&email=' + postdata.email + '&cardNum=' 
	    				+postdata.cardNum + '&role=' + postdata.role + '&college=' + postdata.college
	    				+ '&budget=' + postdata.budget,
	    				success: function(data) {
	                        var obj = JSON.parse(data);
	                        alert(obj.result);
	                        loadStudentUsersData();
	                        $('.ui-jqdialog-titlebar-close').click();
	    		        },
	    		        error: function(data, status) {
	    		            console.log("failed to add student user!");
	    		        }
	    			});
	               
	            },
	            afterSubmit: function(response, postdata) {}
	        });
	    };
	    var editStudentUserRow = function() {
	    	var selectedRowIds = $("#studentUserTable").jqGrid("getGridParam", "selarrrow");
	        if (selectedRowIds && selectedRowIds.length == 1) {
	            $("#studentUserTable").jqGrid('editGridRow', selectedRowIds, {
	                url: 'clientArray',
	                cellsubmit: 'clientArray',
	                reloadAfterSubmit: true,
	                closeAfterEdit: true,
	                modal: true,
		            width:436,
		            dataheight: 356,
		            saveicon: [false,"",""],
		            closeicon: [false,"",""],
	                onclickSubmit: function(params, postdata) {
	                 	$.ajax({
		    				type: "PUT",
		    				url: '/studentusers/' + postdata.studentUserTable_id + '/?displayName=' + postdata.displayName
		    				+ '&password=' + postdata.password + '&email=' + postdata.email + '&cardNum=' 
		    				+postdata.cardNum + '&role=' + postdata.role + '&college=' + postdata.college
		    				+ '&budget=' + postdata.budget,
		    				success: function(data) {
		    					var obj = JSON.parse(data);
		                        alert(obj.result);
		                        loadStudentUsersData();
		                        $('.ui-jqdialog-titlebar-close').click();
		    		        },
		    		        error: function(data, status) {
		    		            console.log("failed to add student user!");
		    		        }
		    		        
		    			});
	                 	return postdata;
	                },
	                afterSubmit: function(response, postdata) {}
	            });
	        } else {
	            $('#selectModal').modal('show');
	        }
	    };
	    var deleteStudentUserRow = function(){
	        var selectedRowIds = $("#studentUserTable").jqGrid("getGridParam", "selarrrow");
	        var studentUsers = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#studentUserTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.userName){
	    	    		studentUsers.push(row.userName);
	    	    	}
	    	    }
	        console.log("id" + studentUsers);
	        $.ajax({
				type: "DELETE",
				url: '/studentusers/' + studentUsers,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadStudentUsersData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete student user!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    if ($("#studentUserTable").length) {
            loadUsersTable();
        }
	});
});