define(["jquery", "bootstrap", "jqGrid","bootstrap_datetimepicker"], function($, bootstrap, jqGrid, bootstrap_datetimepicker){
	$(function(){
		var allProject = [];
		var allProjectRespon = [];
		
		 var loadProjectTable = function() {
				$('#projectInfoTable').jqGrid({
		            datatype: "local",
		            colNames: [ '编号', '项目名', '预算','经费卡号','未领取现金','开始日期', '结束日期'],
		            colModel: [
		                {
		                    name: 'id',
		                    index: 'id',
		                    editable: false,
		                    sorttype: 'text',
		                    width: 150,
		                    align: 'center'
		                },
		                {
		                    name: 'project_name',
		                    index: 'project_name',
		                    editable: true,
		                    edittype: "text",
		                    width: 150,
		                    align: 'center'
		                },
		                {
		                    name: 'budget',
		                    index: 'budget',
		                    editable: true,
		                    edittype: "text",
		                    width: 150,
		                    align: 'center'
		                },
		                {
		                    name: 'cardNum',
		                    index: 'cardNum',
		                    editable: true,
		                    edittype: "text",
		                    width: 150,
		                    align: 'center',
		                },
		                {
		                    name: 'cash',
		                    index: 'cash',
		                    editable: true,
		                    edittype: "text",
		                    width: 150,
		                    align: 'center',
		                },
		                {
		                    name: 'starting_date',
		                    index: 'starting_date',
		                    editable: true,
		                    edittype: "text",
		                    width: 150,
		                    align: 'center',
		                    editoptions: {
		                    	readonly: true,
		                    	dataInit:function(element){
		                    		$(element).datetimepicker(
		                    			{
		                    			    language:  'fr',  
		                    			    weekStart: 1,  
		                    			    todayBtn:  1,  
		                    			    autoclose: 1,  
		                    			    todayHighlight: 1,  
		                    			    startView: 2,  
		                    			    minView: 2,  
		                    			    forceParse: 0,
		                    			    format: 'yyyy-mm-dd'
		                    			 
		                    			})
		                    		}
		                   }
		                },
		                {
		                    name: 'end_time',
		                    index: 'end_time',
		                    editable: true,
		                    edittype: "text",
		                    width: 150,
		                    align: 'center',
		                    editoptions: {
		                    	readonly: true,
		                    	dataInit:function(element){
		                    		$(element).datetimepicker(
		                    			{
		                    			    language:  'fr',  
		                    			    weekStart: 1,  
		                    			    todayBtn:  1,  
		                    			    autoclose: 1,  
		                    			    todayHighlight: 1,  
		                    			    startView: 2,  
		                    			    minView: 2,  
		                    			    forceParse: 0,
		                    			    format: 'yyyy-mm-dd'
		                    			   
		                    			})
		                    		}
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
		            pager: "#projectInfoTableDiv",
		            rowList: [15, 30, 60],
		            
		        });
				loadProjectsData();
		        $('#projectInfoTable').jqGrid('navGrid', "#projectInfoTableDiv", {
		            search: true, // show search button on the toolbar
		            refresh: false,
		            add: false,
		            edit: false,
		            del: false
		        });
		        $("#projectInfoTable").jqGrid('navButtonAdd', "#projectInfoTableDiv", {
		            caption: "",
		            title: "刷新",
		            buttonicon: "ui-icon-refresh",
		            onClickButton: function() {
		            	loadProjectsData();
		            }
		        });
		        $("#projectInfoTable").navButtonAdd('#projectInfoTableDiv', {
		            caption: "",
		            buttonicon: "ui-icon-circle-plus",
		            cellsubmit: "clientArray",
		            title: "增加项目",
		            cursor: "pointer",
		            onClickButton: function() {
		              $("#projectInfoTable").jqGrid('setColProp', 'cash', {editable:false});
		                addProjectRow();
		            },
		        });
		        $("#projectInfoTable").navButtonAdd('#projectInfoTableDiv', {
		            caption: "",
		            buttonicon: "ui-icon-pencil",
		            title: "修改项目",
		            cursor: "pointer",
		            onClickButton: function() {
		            	$("#projectInfoTable").jqGrid('setColProp', 'cash', {editable:true});
		            	editProjectRow();
		            },
		        });
		        $("#projectInfoTable").navButtonAdd('#projectInfoTableDiv', {
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
						$('#projectInfoTable').jqGrid("clearGridData");
						allProject = data;
			            for (var i in allProject) {
			            	allProject[i].rowId = i + 1;
			                $('#projectInfoTable').jqGrid('addRowData',allProject[i].id, allProject[i]);
			            }
			            $('#projectInfoTable').trigger("reloadGrid");
			        },
			        error: function(data, status) {
			            console.log("failed to load projects!");
			        }
				});
		    };
		    
		    var addProjectRow = function() {
		        $("#projectInfoTable").jqGrid('editGridRow', "new", {
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
		    				url: '/projects?project_name=' + postdata.project_name + '&cardNum=' + postdata.cardNum 
		    				+ '&budget=' + postdata.budget + '&starting_date=' + postdata.starting_date 
		    				+ '&end_time=' + postdata.end_time,
		    				success: function(data) {
		                        var obj = JSON.parse(data);
		                        alert(obj.result);
		                        loadProjectsData();
		                        $('.ui-jqdialog-titlebar-close').click();
		    		        },
		    		        error: function(data, status) {
		    		            console.log("failed to add project!");
		    		        }
		    			});
		               
		            },
		            afterSubmit: function(response, postdata) {}
		        });
		    };
		    var editProjectRow = function() {
		    	var selectedRowIds = $("#projectInfoTable").jqGrid("getGridParam", "selarrrow");
		        if (selectedRowIds && selectedRowIds.length == 1) {
		            $("#projectInfoTable").jqGrid('editGridRow', selectedRowIds, {
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
			    				url: '/projects/' + postdata.projectInfoTable_id + '/?project_name=' + postdata.project_name
			    				+ '&cardNum=' + postdata.cardNum + '&budget=' + postdata.budget + '&starting_date=' 
			    				+ postdata.starting_date + '&end_time=' + postdata.end_time + '&cash=' + postdata.cash,
			    				success: function(data) {
			    					var obj = JSON.parse(data);
			                        alert(obj.result);
			                        loadProjectsData();
			                        $('.ui-jqdialog-titlebar-close').click();
			    		        },
			    		        error: function(data, status) {
			    		            console.log("failed to edit project!");
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
		    
		    var deleteProjectRow = function() {
		        var selectedRowIds = $("#projectInfoTable").jqGrid("getGridParam", "selarrrow");
		        var projects = [];
		        if (selectedRowIds && selectedRowIds.length > 0) {
		        	for (var i=0;i<selectedRowIds.length;i++) {
		    	    	var row = $("#projectInfoTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
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
		    
		    if ($("#projectInfoTable").length) {
		    	loadProjectTable();
	        }
	
	 var loadProject_responTable = function() {
			$('#projectResponTable').jqGrid({
	            datatype: "local",
	            colNames: [ '编号', '项目名', '负责人','负责等级'],
	            colModel: [
	                {
	                    name: 'id',
	                    index: 'id',
	                    editable: false,
	                    sorttype: 'text',
	                    width: 150,
	                    align: 'center'
	                },
	                {
	                    name: 'projectName',
	                    index: 'projectName',
	                    editable: true,
	                    width: 150,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
                          value: getProjects
	                    }
	                },
	                {
	                    name: 'charge',
	                    index: 'charge',
	                    editable: true,
	                    width: 150,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
                          value: getTeacherLeader
	                    }
	                },
	                {
	                    name: 'level',
	                    index: 'level',
	                    editable: true,
	                    width: 150,
	                    edittype: "select",
	                    align: 'center',
	                    formatter: 'select',
	                    editoptions: {
                          value: "1:一级领导;2:二级领导;" +
                  		         "3:三级领导;4:四级领导;" +
                		          "5:五级领导"
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
	            pager: "#projectResponTableDiv",
	            rowList: [15, 30, 60],
	            
	        });
			loadProjectResponsData();
	        $('#projectResponTable').jqGrid('navGrid', "#projectResponTableDiv", {
	            search: true, // show search button on the toolbar
	            refresh: false,
	            add: false,
	            edit: false,
	            del: false
	        });
	        $("#projectResponTable").jqGrid('navButtonAdd', "#projectResponTableDiv", {
	            caption: "",
	            title: "刷新",
	            buttonicon: "ui-icon-refresh",
	            onClickButton: function() {
	            	loadProjectResponsData();
	            }
	        });
	        $("#projectResponTable").navButtonAdd('#projectResponTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-circle-plus",
	            cellsubmit: "clientArray",
	            title: "增加项目",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$("#projectResponTable").jqGrid('setColProp', 'level', {edittype: "select"});
	                addProjectResponRow();
	            },
	        });
	        $("#projectResponTable").navButtonAdd('#projectResponTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-pencil",
	            title: "修改项目",
	            cursor: "pointer",
	            onClickButton: function() {
	            	$("#projectResponTable").jqGrid('setColProp', 'level', {edittype: "text",readOnly:true});
	            	editProjectResponRow();
	            },
	        });
	        $("#projectResponTable").navButtonAdd('#projectResponTableDiv', {
	            caption: "",
	            buttonicon: "ui-icon-trash",
	            onClickButton: deleteProjectResponRow,
	            position: "last",
	            title: "删除活动",
	            cursor: "pointer"
	        });
		};
		
		var getProjects = function(){
			var str="";
	    	$.ajax({
	    	type:"GET",
	    	async:false,
	    	url:"/projects",
	    	success:function(data){
	    	if (data != null) {
	    		 console.log(data);
	    	        var jsonobj=eval(data);
	    	        var length=jsonobj.length;
	    	        for(var i=0;i<length;i++){
	    	            if(i!=length-1){
	    	             str += jsonobj[i].id+":"+jsonobj[i].project_name +";";
	    	            }else{
	    	               str += jsonobj[i].id+":"+jsonobj[i].project_name;
	    	            }
	    	         }   
	    	     }
	    	},
	    	 error: function(data, status) {
		            console.log("failed to get projects!");
		        }
	    	});
	    	 return str;
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
	    	             str += jsonobj[i].userName+":"+jsonobj[i].college + jsonobj[i].displayName + ";";
	    	            }else{
	    	               str += jsonobj[i].userName+":"+jsonobj[i].college + jsonobj[i].displayName;
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
		
		var loadProjectResponsData = function() {
			$.ajax({
				type: "GET",
				url: "/projectrespons",
				success: function(data) {
					console.log("success to load projectrespons!");
					$('#projectResponTable').jqGrid("clearGridData");
					allProjectRespon = data;
		            for (var i in allProjectRespon) {
		            	allProjectRespon[i].rowId = i + 1;
		                $('#projectResponTable').jqGrid('addRowData',allProjectRespon[i].id, allProjectRespon[i]);
		            }
		            $('#projectResponTable').trigger("reloadGrid");
		        },
		        error: function(data, status) {
		            console.log("failed to load projectRespon!");
		        }
			});
	    };
	    
	    var addProjectResponRow = function() {
	        $("#projectResponTable").jqGrid('editGridRow', "new", {
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
	    				url: '/projectrespons?projectName=' + postdata.projectName + '&charge=' + postdata.charge 
	    				+ '&level=' + postdata.level,
	    				success: function(data) {
	                        var obj = JSON.parse(data);
	                        alert(obj.result);
	                        loadProjectResponsData();
	                        $('.ui-jqdialog-titlebar-close').click();
	    		        },
	    		        error: function(data, status) {
	    		            console.log("failed to add project respon!");
	    		        }
	    			});
	               
	            },
	            afterSubmit: function(response, postdata) {}
	        });
	    };
    	var isContinousNumber = function (levels) {
    		var result;
    	
    		if (levels.length > 1) {
    			levels.sort(function(a,b) {return a > b ? 1 : -1});
    			var pre = levels[0];
    			for (var i=1;i<levels.length;i++) {
    				if (levels[i] == pre + 1) {
    					pre = levels[i];
    					levels
    				}
    				else {
    					result = false;
    					break;
    				}
    				result = true;
    			}
    		}
    		else
    			result = true;    		
    		return result;
    	};
	    var editProjectResponRow = function() {
	    	var selectedRowIds = $("#projectResponTable").jqGrid("getGridParam", "selarrrow");
	        if (selectedRowIds && selectedRowIds.length == 1) {
	            $("#projectResponTable").jqGrid('editGridRow', selectedRowIds, {
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
		    				url: '/projectrespons/' + postdata.projectResponTable_id + '/?projectName=' + postdata.projectName
		    				+ '&charge=' + postdata.charge + '&level=' + postdata.level,
		    				success: function(data) {
		    					var obj = JSON.parse(data);
		                        alert(obj.result);
		                        loadProjectResponsData();
		                        $('.ui-jqdialog-titlebar-close').click();
		    		        },
		    		        error: function(data, status) {
		    		            console.log("failed to edit project respon!");
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
	    
	    var deleteProjectResponRow = function() {
	        var selectedRowIds = $("#projectResponTable").jqGrid("getGridParam", "selarrrow");
	        var projectRespons = [];
	        var levels = [];
	        if (selectedRowIds && selectedRowIds.length > 0) {
	        	for (var i=0;i<selectedRowIds.length;i++) {
	    	    	var row = $("#projectResponTable").jqGrid("getRowData", selectedRowIds[i]);	    	   
	    	    	if (row.id){
	    	    		projectRespons.push(row.id);
	    	    		levels.push(row.level);
	    	    	}
	    	    }
//	        if(isContinousNumber(levels)){
	        console.log("id" + projectRespons);
	        $.ajax({
				type: "DELETE",
				url: '/projectrespons/' + projectRespons,
				success: function(data) {
					var obj = JSON.parse(data);
                 alert(obj.result);
                 loadProjectResponsData();
		        },
		        error: function(data, status) {
		            console.log("failed to delete projectRespons!");
		        }
			});
//	        }else{
//	        	alert("必须选择连续的负责等级删除!");
//	        }	
	        }else{
	        	$('#selectModal').modal('show');	
	        }
	    };
	    
	    if ($("#projectResponTable").length) {
	    	loadProject_responTable();
     }
  });
});	