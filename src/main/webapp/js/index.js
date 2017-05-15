var name_empty = "<span style='COLOR:#ff0000'>  × 用户名不能为空!</span>";
var pwd_empty = "<span style='COLOR:#ff0000'>  × 密码不能为空!</span>";
var NP_invalid = "<span style='COLOR:#ff0000'>  × 用户名或密码错误!</span>";

function pageTest() {
	$("#div1").text("hello this is  test index.js");

}

/**
 * 
 */



$(function() {
	$(".btn_loging").on('click', function(event) {
		event.preventDefault();
		var user = $('#user').val();
		var pass = $('#password').val();
		var isLogin = false;

		// 判断自动登录框是否被选中
		if ($("#isLogin").is(":checked")) {
			isLogin = true;
		}
		if (user == '') {
			showInfo("msg", name_empty);
			return false;
		}
		if (pass == '') {
			showInfo("msg", pwd_empty);
			return false;
		}
		$.ajax({
			url : getRootPath() + "rest/user/login",
			type : 'POST',
			dataType : 'json',
			data : {
				username : user,
				password : pass,
				isLogin : isLogin
			},
			success : function(json) {
				if (json.flag) { // 登录成功 ?username='+ $('#user').val()
//					alert("准备跳转:page/welcome");
					window.location.href = getRootPath() + 'rest/page/welcome';
				} else {
					showInfo("msg", NP_invalid);
				}
			}
		});
	});
});

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
