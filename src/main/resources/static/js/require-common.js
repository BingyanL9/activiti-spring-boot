require.config({

    baseUrl: "/js/lib/",

    paths: {
        "jquery": "jquery.1.11.3.min",
        "validate": "jquery.validate",
        "validateAdditional": "additional-methods",
        "jqueryui": "jquery-ui-1.9.2.min",
        "jqte": "jquery-te-1.4.0",
        "bootstrap": "bootstrap.min",
        "d3": "d3.v3.min",
        "c3": "c3.min",
        "jqGrid": "jqGrid/jquery.jqgrid.min",
        "bootstrap_select":"bootstrap-select.min", 
        "bootstrap_datetimepicker":"bootstrap-datetimepicker",
        "bootstrap_fileupload":"bootstrap-fileupload"
    },

    shim: {
        "jquery": {
            exports: "jquery"
        },
        "jqte": {
            exports: "jqte",
            deps: ['jquery']
        },
        "validate": {
            exports: "validate",
            deps: ['jquery']
        },
        "validateAdditional": {
            exports: "validateAdditional",
            deps: ['validate']
        },
        "jqueryui": {
            exports: "jqueryui",
            deps: ['jquery']
        },
        "bootstrap": {
            exports: "bootstrap",
            deps: ['jquery']
        },
        "d3": {
            exports: "d3"
        },
        "c3": {
        	deps: ['d3']
        },
        "jqGrid": {
            exports: "jqGrid",
            deps: ['jquery']
        },
        "bootstrap_select": {
        	deps: ['bootstrap_select'],
        	deps: ['jquery', 'bootstrap']
        },
        "bootstrap_datetimepicker": {
        	deps: ['bootstrap_datetimepicker'],
        	deps: ['jquery', 'bootstrap']
        },
        "bootstrap_fileupload": {
        	deps: ['bootstrap_fileupload'],
        	deps: ['jquery', 'bootstrap']
        }

    }

});
