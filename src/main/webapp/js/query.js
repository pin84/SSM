var userCount;// 符合查找条件的用户总页数，分页参考
var pageIndex = 1;// 当前页，默认为1
var pageSize = 3;// 每页显示个数 可自定义
var pageCount = 1;// 页码数

function pageTest() {
	$("#div1").text("hello this is  test");
}

 $(function() {
	 searchUser(pageIndex, pageSize);
 });


// 新增用户(和注册用户同一controller)


// 查询 (只能显示第一页查到的用户，如果有多个同名用户，则分页的功能还没做出来)
$(document).on('click', '.qButton', function(name) {
	var name = $(".iname").val();
	queryUser(name);
	
});

// 编辑
$(document).on("click",".edit",function(){
	var id = $(this).closest("tr").find("td:eq(0)").html();
	var name = $(this).closest("tr").find("td:eq(1)").html();
	var pwd = $(this).closest("tr").find("td:eq(2)").html();
	var email = $(this).closest("tr").find("td:eq(3)").html();
	var province = $(this).closest("tr").find("td:eq(4)").html();
	var city = $(this).closest("tr").find("td:eq(5)").html();
	
	layer.open({
		type: 1,
		skin: 'layui-layer-rim', // 加上边框
		area: ['420px', '240px'], // 宽高
		content: '<form name="a" action="rest/user/edit" method="POST">'+
		'<input type="text" name="id" value="'+id+'" readonly>'+
		'<input type="text" name="ename" value="'+name+'"/>'+
		'<input type="text" name="epwd" value="'+pwd+'"/>'+
		'<input type="text" name="email" value="'+email+'"/>'+
		'<input type="text" name="province" value="'+province+'"/>'+
		'<input type="text" name="city" value="'+city+'"/><br>'+
		'<input type="submit" value="保存"/>'+
		'<input type="button" value="取消" onclick="layer.closeAll()"/>'+
		'</form>'
	});	

})

// 相询用户
function queryUser(name){
	$.ajax({
		type : "POST",
		url : getRootPath() + "rest/user/query",
		dataType : 'json',
		data : {
			iname:name,
			pageIndex : pageIndex - 1,
			pageSize : pageSize
		},
		success : function(json) {
			if(json.length != 0){
				$("#seachResult").empty();
				buildList(json, $("#seachResult"));
				buildPageCount($("#userPage"));
			} else {
				$("#seachResult").empty();
				layer.msg('无此用户', {icon: 1});
			}
		}
	});
}

// 删除
$(document).on("click", ".did", function() {
	var id = $(this).closest("tr").find("td:eq(0)").html();
	// 询问框
	layer.confirm('确定删除？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			type : "post",
			url : getRootPath() + "rest/user/delete",
// dataType : 'json',
			data : {
				id:id
			},
			success : function(i) {
				if (i == 1) {
					layer.msg('已删除', {icon: 1});
					$("#seachResult").empty();
					searchUser(pageIndex, pageSize);
				}
			}
		});
	});
})

// 分面显示用户
function searchUser(index, size) {
	$.ajax({
		type : "POST",
		url : getRootPath() + "rest/user/list",
		cache : false,
		data : {
			pageIndex : index - 1,
			pageSize : size
		},
		async : true,
		error : function() {
			alert("网络异常！");
		},
		success : function(json) {
			var jsonObj = eval("(" + json + ")");
			buildList(jsonObj, $("#seachResult"));
			buildPageCount($("#userPage"));
		}
	});
}

// 建表. json: 后台返回的数据, location: 表格添加的位置 ,userCount:分页的页码数
function buildList(json, location) {
	$.each(json, function(index, item) {
		userCount = Math.ceil(item.userCount / pageSize);
	});
	$.each(json, function(index, item) {
		userCount = Math.ceil(item.userCount / pageSize);// 分页的页码数
		var list = "<tr><td>" + item.id + "</td><td>" + item.name + "</td><td>"
				+ item.pwd + "</td><td>" + item.email + "</td><td>"
				+ item.province + "</td><td>" + item.city + "</td><td>"
				+ '<a href="javascript:void" class="edit" >Edit' + '</a> |'
				+ '<a href="javascript:void"  class="did" > Delete </a>'
				+ "</td></tr>";
		location.append(list);
	});
}
// 建立底部分页按钮, location: 分页按钮添加的位置
function buildPageCount(location) {
	var page = '<div id="userPage" colspan="0">共'
			+ userCount
			+ '页 第'
			+ pageCount
			+ '页   <a href="javascript:void" onclick="GoToFirstPage()" id="aFirstPage" >   首页    </a>'
			+ '<a href="javascript:void" onclick="GoToPrePage()" id="aPrePage">上一页    </a>'
			+ '<a href="javascript:void" onclick="GoToNextPage()" id="aNextPage">下一页   </a>'
			+ '<a href="javascript:void" onclick="GoToEndPage()" id="aEndPage">末页</a></div>';
	location.empty();
	location.append(page);
}

// 首页
function GoToFirstPage() {
	pageCount = 1;
	pageIndex = 1;
	$("#seachResult").empty();
	searchUser(pageIndex, pageSize);
}
// 前一页
function GoToPrePage() {
	if (pageCount - 1 >= 1) {
		pageCount -= 1;
		pageIndex -= pageSize;
		pageIndex = pageIndex >= 1 ? pageIndex : 1;
	}
	$("#seachResult").empty();
	searchUser(pageIndex, pageSize);
}
// 后一页
function GoToNextPage() {
	if (pageCount + 1 <= userCount) {
		pageIndex += pageSize;
		pageCount += 1;
	}
	$("#seachResult").empty();
	searchUser(pageIndex, pageSize);
}
// 尾页
function GoToEndPage() {
	pageCount = userCount;
	if (pageCount == 1) {
		pageIndex = 1;
	} else {
		pageIndex = (Math.ceil(userCount * pageSize)) - pageSize + 1;
	}
	$("#seachResult").empty();
	searchUser(pageIndex, pageSize);
}
// 获取项目根目录
function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName
			.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'
			+ webName + '/';
}
