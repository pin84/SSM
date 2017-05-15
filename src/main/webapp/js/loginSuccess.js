function longintest() {
	$("#div1").text("hello this is  test");
}





/*
 * 以下为共用程序
 */

function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName
			.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'
			+ webName + '/';
}

function showInfo(target, Infos) {
	document.getElementById(target).innerHTML = Infos;
}
function showclass(target, Infos) {
	document.getElementById(target).className = Infos;
}