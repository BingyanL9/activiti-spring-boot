require.config({

    baseUrl: "/js",

    paths: {
        "jquery": "lib/jquery.1.11.3.min",
        "validate": "lib/jquery.validate",
        "validateAdditional": "lib/additional-methods",
        "jqueryui": "lib/jquery-ui-1.9.2.min",
        "jqte": "lib/jquery-te-1.4.0",
        "bootstrap": "lib/bootstrap.min",
        "d3": "lib/d3.v3.min",
        "c3": "lib/c3.min",
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
        }

    }

});
