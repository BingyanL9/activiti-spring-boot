define(["jquery", "bootstrap", "jqGrid"], function($, bootstrap, jqGrid){
	$(function(){
		var allStudentUser = [];
		var allTeacherUser = [];
		var allClubUser = [];
		var allActivity = [];
		var allProject = [];
		var allApplication = [];
		var allFeedback = [];
		
		var loadStudentUsersTable = function() {
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
	            search: true, // show search button on the toolbar
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
	        $('#studentUserTable').jqGrid('navButtonAdd', "#studentUserTableDiv", {
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
	        $('#studentUserTable').jqGrid('navButtonAdd', "#studentUserTableDiv", {
	            caption: "",
	            buttonicon: "ui-icon-pencil",
	            title: "修改学生用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$("#studentUserTable").jqGrid('setColProp', 'userName', {editable:false});
	            	editStudentUserRow();
	            },
	        });
	        $('#studentUserTable').jqGrid('navButtonAdd', "#studentUserTableDiv",{
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            position: "last",
	            onClickButton: deleteStudentUserRow,
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
	    	loadStudentUsersTable();
        }
	    
		var loadTeacherUsersTable = function() {
			$('#teacherUserTable').jqGrid({
	            datatype: "local",
	            colNames: [ '工号', '用户名', '密码','邮箱', '银行卡账号', '权限', '学院','职位','日常预算','领导'],
	            colModel: [
	                {
	                    name: 'userName',
	                    index: 'userName',
	                    editable:true,
	                    sorttype: 'text',
	                    width: 100,
	                    align: 'center'
	                },
	                {
	                    name: 'displayName',
	                    index: 'displayName',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'password',
	                    index: 'password',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'email',
	                    index: 'email',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'cardNum',
	                    index: 'cardNum',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'role',
	                    index: 'role',
	                    editable: true,
	                    width: 100,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
	                        value: "ordinary:普通用户;admin:管理员用户;" +
	                        		"medical_group:医疗组;finance_group:财务组;" +
	                        		"liberary:图书馆工作人员;asset_processing_office:资产管理办公室"
	                    }
	                },
	                {
	                    name: 'college',
	                    index: 'college',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'title',
	                    index: 'title',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'budget',
	                    index: 'budget',
	                    editable: true,
	                    width: 100,
	                    edittype: "text",
	                    align: 'center'
	                },
	                {
	                    name: 'leader_userName',
	                    index: 'leader_userName',
	                    editable: true,
	                    width: 100,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
                          value: getTeacherLeader
	                    }
	                }
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
	            pager: "#teacherUserTableDiv",
	            rowList: [15, 30, 60],
	            
	        });
	        loadTeacherUsersData();
	        $('#teacherUserTable').jqGrid('navGrid', "#teacherUserTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#teacherUserTable").jqGrid('navButtonAdd', "#teacherUserTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	                loadTeacherUsersData();
	            }
	        });
	        $("#teacherUserTable").navButtonAdd('#teacherUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-circle-plus",
	            cellsubmit: "clientArray",
	            title: "增加教师用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$('#teacherUserTable').jqGrid('setColProp', 'userName', {editable:true});
	                addTeacherUserRow();
	            },
	        });
	        $("#teacherUserTable").navButtonAdd('#teacherUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-pencil",
	            title: "修改教师用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$('#teacherUserTable').jqGrid('setColProp', 'userName', {editable:false});
	            	editTeacherUserRow();
	            },
	        });
	        $("#teacherUserTable").navButtonAdd('#teacherUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteTeacherUserRow,
	            position: "last",
	            title: "删除教师用户",
	            cursor: "pointer"
	        });
		};
		
		var loadTeacherUsersData = function() {
			$.ajax({
				type: "GET",
				url: "/teacherusers",
				success: function(data) {
					console.log("success to load teacher user!");
					$('#teacherUserTable').jqGrid("clearGridData");
					allTeacherUser = data;
		            for (var i in allTeacherUser) {
		            	allTeacherUser[i].rowId = i + 1;
		                $('#teacherUserTable').jqGrid('addRowData',allTeacherUser[i].userName, allTeacherUser[i]);
		            }
		            $('#teacherUserTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load teacher user!");
		        }
			});
	    };
	    
	    var addTeacherUserRow = function() {
	        $("#teacherUserTable").jqGrid('editGridRow', "new", {
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
	    				url: '/teacherusers?userName=' + postdata.userName + '&displayName=' + postdata.displayName 
	    				+ '&password=' + postdata.password + '&email=' + postdata.email + '&cardNum=' 
	    				+postdata.cardNum + '&role=' + postdata.role + '&college=' + postdata.college
	    				+ '&budget=' + postdata.budget + '&title=' + postdata.title + '&leader_userName=' + postdata.leader_userName,
	    				success: function(data) {
	                        var obj = JSON.parse(data);
	                        alert(obj.result);
	                        loadTeacherUsersData();
	                        $('.ui-jqdialog-titlebar-close').click();
	    		        },
	    		        error: function(data, status) {
	    		            console.log("failed to add teacher user!");
	    		        }
	    			});
	               
	            },
	            afterSubmit: function(response, postdata) {}
	        });
	    };
	    var editTeacherUserRow = function() {
	    	var selectedRowIds = $("#teacherUserTable").jqGrid("getGridParam", "selarrrow");
	        if (selectedRowIds && selectedRowIds.length == 1) {
	            $("#teacherUserTable").jqGrid('editGridRow', selectedRowIds, {
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
		    				url: '/teacherusers/' + postdata.teacherUserTable_id + '/?displayName=' + postdata.displayName
		    				+ '&password=' + postdata.password + '&email=' + postdata.email + '&cardNum=' 
		    				+postdata.cardNum + '&role=' + postdata.role + '&college=' + postdata.college
		    				+ '&budget=' + postdata.budget + '&title=' + postdata.title + '&leader_userName=' + postdata.leader_userName,
		    				success: function(data) {
		    					var obj = JSON.parse(data);
		                        alert(obj.result);
		                        loadTeacherUsersData();
		                        $('.ui-jqdialog-titlebar-close').click();
		    		        },
		    		        error: function(data, status) {
		    		            console.log("failed to add teacher user!");
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
	    var deleteTeacherUserRow = function() {
	        var selectedRowIds = $("#teacherUserTable").jqGrid("getGridParam", "selarrrow");
	        var teacherUsers = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#teacherUserTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.userName){
	    	    		teacherUsers.push(row.userName);
	    	    	}
	    	    }
	        console.log("id" + teacherUsers);
	        $.ajax({
				type: "DELETE",
				url: '/teacherusers/' + teacherUsers,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadTeacherUsersData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete teacher user!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    var getTeacherLeader = function() {
	    	var str="";
	    	$.ajax({
	    	type:"GET",
	    	async:false,
	    	url:"/teacherusers",
	    	success:function(data){
	    	if (data != null) {
	    		 console.log(data);
	    	        var jsonobj=eval(data);
	    	        var length=jsonobj.length;
	    	        for(var i=0;i<length;i++){
	    	            if(i!=length-1){
	    	             str += jsonobj[i].userName+":"+jsonobj[i].userName+";";
	    	            }else{
	    	               str += jsonobj[i].userName+":"+jsonobj[i].userName;
	    	            }
	    	         }   
	    	     }
	    	},
	    	 error: function(data, status) {
		            console.log("failed to get teacher user leader!");
		        }
	    	});
	    	 return str;
	    };
	    
	    if ($("#teacherUserTable").length) {
            loadTeacherUsersTable();
        }
	    
	    
	    var loadClubUsersTable = function() {
			$('#clubUserTable').jqGrid({
	            datatype: "local",
	            colNames: [ '编号', '社团名', '密码','邮箱', '银行卡账号', '权限', '学院','所属社团'],
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
	                        value: "ordinary:普通用户"
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
	                    name: 'leaderClubName',
	                    index: 'leaderClubName',
	                    editable: true,
	                    width: 120,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
                          value: getClubLeader
	                    }
	                }
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
	            pager: "#clubUserTableDiv",
	            rowList: [15, 30, 60],
	            
	        });
	        loadClubUsersData();
	        $('#clubUserTable').jqGrid('navGrid', "#clubUserTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#clubUserTable").jqGrid('navButtonAdd', "#clubUserTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	                loadClubUsersData();
	            }
	        });
	        $("#clubUserTable").navButtonAdd('#clubUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-circle-plus",
	            cellsubmit: "clientArray",
	            title: "增加社团用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$('#clubUserTable').jqGrid('setColProp', 'userName', {editable:true});
	                addClubUserRow();
	            },
	        });
	        $("#clubUserTable").navButtonAdd('#clubUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-pencil",
	            title: "修改社团用户",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$('#clubUserTable').jqGrid('setColProp', 'userName', {editable:false});
	            	editClubUserRow();
	            },
	        });
	        $("#clubUserTable").navButtonAdd('#clubUserTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteClubUserRow,
	            position: "last",
	            title: "删除社团用户",
	            cursor: "pointer"
	        });
		};
		
		var loadClubUsersData = function() {
			$.ajax({
				type: "GET",
				url: "/clubusers",
				success: function(data) {
					console.log("success to load club user!");
					$('#clubUserTable').jqGrid("clearGridData");
					allClubUser = data;
		            for (var i in allClubUser) {
		            	allClubUser[i].rowId = i + 1;
		                $('#clubUserTable').jqGrid('addRowData',allClubUser[i].userName, allClubUser[i]);
		            }
		            $('#clubUserTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load club user!");
		        }
			});
	    };
	    
	    var addClubUserRow = function() {
	        $("#clubUserTable").jqGrid('editGridRow', "new", {
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
	    				url: '/clubusers?userName=' + postdata.userName + '&displayName=' + postdata.displayName 
	    				+ '&password=' + postdata.password + '&email=' + postdata.email + '&cardNum=' 
	    				+postdata.cardNum + '&role=' + postdata.role + '&college=' + postdata.college
	    				+ '&leaderClubName=' + postdata.leaderClubName,
	    				success: function(data) {
	                        var obj = JSON.parse(data);
	                        alert(obj.result);
	                        loadClubUsersData();
	                        $('.ui-jqdialog-titlebar-close').click();
	    		        },
	    		        error: function(data, status) {
	    		            console.log("failed to add club user!");
	    		        }
	    			});
	               
	            },
	            afterSubmit: function(response, postdata) {}
	        });
	    };
	    var editClubUserRow = function() {
	    	var selectedRowIds = $("#clubUserTable").jqGrid("getGridParam", "selarrrow");
	        if (selectedRowIds && selectedRowIds.length == 1) {
	            $("#clubUserTable").jqGrid('editGridRow', selectedRowIds, {
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
		    				url: '/clubusers/' + postdata.clubUserTable_id + '/?displayName=' + postdata.displayName
		    				+ '&password=' + postdata.password + '&email=' + postdata.email + '&cardNum=' 
		    				+postdata.cardNum + '&role=' + postdata.role + '&college=' + postdata.college
		    				+ '&leaderClubName=' + postdata.leaderClubName,
		    				success: function(data) {
		    					var obj = JSON.parse(data);
		                        alert(obj.result);
		                        loadClubUsersData();
		                        $('.ui-jqdialog-titlebar-close').click();
		    		        },
		    		        error: function(data, status) {
		    		            console.log("failed to add club user!");
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
	    var deleteClubUserRow = function() {
	        var selectedRowIds = $("#clubUserTable").jqGrid("getGridParam", "selarrrow");
	        var clubUsers = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#clubUserTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.userName){
	    	    		clubUsers.push(row.userName);
	    	    	}
	    	    }
	        console.log("id" + clubUsers);
	        $.ajax({
				type: "DELETE",
				url: '/clubusers/' + clubUsers,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadClubUsersData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete club user!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    var getClubLeader = function() {
	    	var str="";
	    	$.ajax({
	    	type:"GET",
	    	async:false,
	    	url:"/clubusers",
	    	success:function(data){
	    	if (data != null) {
	    		 console.log(data);
	    	        var jsonobj=eval(data);
	    	        var length=jsonobj.length;
	    	        for(var i=0;i<length;i++){
	    	            if(i!=length-1){
	    	             str += jsonobj[i].userName+":"+jsonobj[i].userName+";";
	    	            }else{
	    	               str += jsonobj[i].userName+":"+jsonobj[i].userName;
	    	            }
	    	         }   
	    	     }
	    	},
	    	 error: function(data, status) {
		            console.log("failed to get club user leader!");
		        }
	    	});
	    	 return str;
	    };
	    
	    if ($("#clubUserTable").length) {
            loadClubUsersTable();
        }
	    
	    
	    var loadActivityTable = function() {
			$('#activityTable').jqGrid({
	            datatype: "local",
	            colNames: [ '编号', '活动名', '预算','开始日期', '结束日期', '负责社团'],
	            colModel: [
	                {
	                    name: 'id',
	                    index: 'id',
	                    sorttype: 'text',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'activityName',
	                    index: 'activityName',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'budget',
	                    index: 'budget',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'starting_date',
	                    index: 'starting_date',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'end_time',
	                    index: 'end_time',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'chargeClub',
	                    index: 'chargeClub',
	                    width: 150,
	                    align: 'center'
	                }
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
	            pager: "#activityTableDiv",
	            rowList: [15, 30, 60],
	            
	        });
			loadActivitesData();
	        $('#activityTable').jqGrid('navGrid', "#activityTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#activityTable").jqGrid('navButtonAdd', "#activityTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	            	loadActivitesData();
	            }
	        });
	        $("#activityTable").navButtonAdd('#activityTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteActivityRow,
	            position: "last",
	            title: "删除活动",
	            cursor: "pointer"
	        });
		};
		
		var loadActivitesData = function() {
			$.ajax({
				type: "GET",
				url: "/activities",
				success: function(data) {
					console.log("success to load activities!");
					$('#activityTable').jqGrid("clearGridData");
					allActivity = data;
		            for (var i in allActivity) {
		            	allActivity[i].rowId = i + 1;
		                $('#activityTable').jqGrid('addRowData',allActivity[i].id, allActivity[i]);
		            }
		            $('#activityTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load activity!");
		        }
			});
	    };
	    
	    var deleteActivityRow = function() {
	        var selectedRowIds = $("#activityTable").jqGrid("getGridParam", "selarrrow");
	        var activities = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#activityTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.id){
	    	    		activities.push(row.id);
	    	    	}
	    	    }
	        console.log("id" + activities);
	        $.ajax({
				type: "DELETE",
				url: '/activities/' + activities,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadActivitesData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete activity!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    if ($("#activityTable").length) {
	    	loadActivityTable();
        }
	    
	    
	    var loadProjectTable = function() {
			$('#projectTable').jqGrid({
	            datatype: "local",
	            colNames: [ '编号', '项目名', '预算','经费卡号','开始日期', '结束日期'],
	            colModel: [
	                {
	                    name: 'id',
	                    index: 'id',
	                    sorttype: 'text',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'project_name',
	                    index: 'project_name',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'budget',
	                    index: 'budget',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'cardNum',
	                    index: 'cardNum',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'starting_date',
	                    index: 'starting_date',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'end_time',
	                    index: 'end_time',
	                    width: 150,
	                    align: 'center'
	                }
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
	            pager: "#projectTableDiv",
	            rowList: [15, 30, 60],
	            
	        });
			loadProjectsData();
	        $('#projectTable').jqGrid('navGrid', "#projectTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#projectTable").jqGrid('navButtonAdd', "#projectTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	            	loadProjectsData();
	            }
	        });
	        $("#projectTable").navButtonAdd('#projectTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteProjectRow,
	            position: "last",
	            title: "删除活动",
	            cursor: "pointer"
	        });
		};
		
		var loadProjectsData = function() {
			$.ajax({
				type: "GET",
				url: "/projects",
				success: function(data) {
					console.log("success to load projects!");
					$('#projectTable').jqGrid("clearGridData");
					allProject = data;
		            for (var i in allProject) {
		            	allProject[i].rowId = i + 1;
		                $('#projectTable').jqGrid('addRowData',allProject[i].id, allProject[i]);
		            }
		            $('#projectTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load projects!");
		        }
			});
	    };
	    
	    var deleteProjectRow = function() {
	        var selectedRowIds = $("#projectTable").jqGrid("getGridParam", "selarrrow");
	        var projects = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#projectTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.id){
	    	    		projects.push(row.id);
	    	    	}
	    	    }
	        console.log("id" + projects);
	        $.ajax({
				type: "DELETE",
				url: '/projects/' + projects,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadProjectsData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete project!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    if ($("#projectTable").length) {
	    	loadProjectTable();
        }
	    
	    var loadApplicationTable = function() {
			$('#applicationTable').jqGrid({
	            datatype: "local",
	            colNames: [ '编号','创建日期','描述','操作'],
	            colModel: [
	                {
	                    name: 'id',
	                    index: 'id',
	                    sorttype: 'text',
	                    width: 200,
	                    align: 'center'
	                },
	                {
	                    name: 'createtime',
	                    index: 'createtime',
	                    width: 200,
	                    align: 'center'
	                },
	                {
	                    name: 'description',
	                    index: 'description',
	                    width: 300,
	                    align: 'center'
	                },
	                {
	                	 name: 'act',
	                	 template: 'actions', 
	                	 align: 'center', 
	                	 width: 35
	                }
	            ],
	            actionsNavOptions: {
					editbutton: false,
					delbutton: false,
					lookApplicationicon: "ui-icon-folder-open",
					lookApplicationtitle: "查看",
					custom: [
						{ action: "lookApplication", position: "first", onClick: lookApplication},
					]
				},
	            viewrecords: true, // show the current page, data rang and total records on the toolbar
	            height: 400,
	            rowNum: 15,
	            rownumbers: true,
	            autowidth: false,
	            shrinkToFit: true,
	            gridview: true,
	            multiselect: true,
	            pager: "#applicationTableDiv",
	            rowList: [15, 30, 60],
	        });
			loadApplicationsData();
	        $('#applicationTable').jqGrid('navGrid', "#applicationTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#applicationTable").jqGrid('navButtonAdd', "#applicationTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	            	loadApplicationsData();
	            }
	        });
	        $("#applicationTable").navButtonAdd('#applicationTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteApplicationRow,
	            position: "last",
	            title: "删除申请单",
	            cursor: "pointer"
	        });
		};
		var lookApplication = function(options) {
			var selectedRowId = $("#applicationTable").jqGrid("getGridParam", "selrow");
			 console.debug("View application info, rowid=" + selectedRowId);
			 $.ajax({
					type: "GET",
					url: "/applications/" + selectedRowId,
					async:false,
					success: function(responseHTML) {
						$('#applicationModal').modal('show');
						var container = $("#applicationInfo");
			        	container.empty();
			        	$("#applicationInfo").prepend(responseHTML);
			        },
			        error: function(response, status) {
			            console.log("failed to get application" + selectedRowId);
			        }
				});
			 $("#applicationTable").jqGrid('setSelection',selectedRowId);
		}
		var loadApplicationsData = function() {
			$.ajax({
				type: "GET",
				url: "/applications",
				success: function(data) {
					console.log("success to load applications!");
					$('#applicationTable').jqGrid("clearGridData");
					allApplication = data;
		            for (var i in allApplication) {
		            	allApplication[i].rowId = i + 1;
		                $('#applicationTable').jqGrid('addRowData',allApplication[i].id, allApplication[i]);
		            }
		            $('#applicationTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load applications!");
		        }
			});
	    };
	    
	    var deleteApplicationRow = function() {
	        var selectedRowIds = $("#applicationTable").jqGrid("getGridParam", "selarrrow");
	        var applications = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#applicationTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.id){
	    	    		applications.push(row.id);
	    	    	}
	    	    }
	        console.log("id" + applications);
	        $.ajax({
				type: "DELETE",
				url: '/applications/' + applications,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadApplicationsData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete application!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    if ($("#applicationTable").length) {
	    	loadApplicationTable();
        }
	    
	    var loadFeedbackTable = function() {
			$('#feedbackTable').jqGrid({
	            datatype: "local",
	            colNames: [ '编号','反馈日期','意见','申请单编号'],
	            colModel: [
	                {
	                    name: 'id',
	                    index: 'id',
	                    sorttype: 'text',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'feedback_time',
	                    index: 'feedback_time',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'suggest',
	                    index: 'suggest',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'applicationId',
	                    index: 'applicationId',
	                    width: 150,
	                    align: 'center'
	                },
	           
	            ],
	            viewrecords: true, // show the current page, data rang and total records on the toolbar
	            height: 400,
	            rowNum: 15,
	            rownumbers: true,
	            autowidth: false,
	            shrinkToFit: true,
	            gridview: true,
	            multiselect: true,
	            pager: "#feedbackTableDiv",
	            rowList: [15, 30, 60],
	        });
			loadFeedbacksData();
	        $('#feedbackTable').jqGrid('navGrid', "#feedbackTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#feedbackTable").jqGrid('navButtonAdd', "#feedbackTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	            	loadFeedbacksData();
	            }
	        });
	        $("#feedbackTable").navButtonAdd('#feedbackTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteFeedbackRow,
	            position: "last",
	            title: "删除申请单",
	            cursor: "pointer"
	        });
		};
		
		var loadFeedbacksData = function() {
			$.ajax({
				type: "GET",
				url: "/feedbacks",
				success: function(data) {
					console.log("success to load feedbacks!");
					$('#feedbackTable').jqGrid("clearGridData");
					allFeedback = data;
		            for (var i in allFeedback) {
		            	allFeedback[i].rowId = i + 1;
		                $('#feedbackTable').jqGrid('addRowData',allFeedback[i].id, allFeedback[i]);
		            }
		            $('#feedbackTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load feedbacks!");
		        }
			});
	    };
	    
	    var deleteFeedbackRow = function() {
	        var selectedRowIds = $("#feedbackTable").jqGrid("getGridParam", "selarrrow");
	        var feedbacks = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#feedbackTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.id){
	    	    		feedbacks.push(row.id);
	    	    	}
	    	    }
	        console.log("id" + feedbacks);
	        $.ajax({
				type: "DELETE",
				url: '/feedbacks/' + feedbacks,
				success: function(data) {
					var obj = JSON.parse(data);
                    alert(obj.result);
                    loadFeedbacksData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete feedback!");
		        }
			});
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    if ($("#feedbackTable").length) {
	    	loadFeedbackTable();
        }
	});
});