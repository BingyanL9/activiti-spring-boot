requirejs(['./require-common'], function (common) {
	requirejs(['bootstrap','jquery','bootstrap_select','bootstrap_fileupload']);
	requirejs(['/js/header.js']);
	requirejs(['/js/apply.js']);
	requirejs(['/js/admin.js']);
});
