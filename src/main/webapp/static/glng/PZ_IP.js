//var ip = "http://47.103.35.95:8887";
var host = location.href.match(/(http[s]?\:\/\/)(.*?)\//);
var ip = "http://"+host[2]+":8090";
//var ip="http://111.231.83.143:8090";
//为ajax设置跨域带cookie
$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
	options.xhrFields = {
		withCredentials: true
	}
});
//权限设置
function fc() {
	$.ajax({
		type: "get",
		url: ip + "/auth/cover/1",
		success: function(data) {
			if(data.result == false) {
				$(".a").remove();
				alert('你没有该模块的查看权限')
			}
		}
	});
};

function fc1() {
	$.ajax({
		type: "get",
		url: ip + "/auth/cover/2",
		success: function(data) {
			if(data.result == false) {
				$(".b").remove();
				alert('你没有该模块的查看权限')
			}
		}
	});
};

function fc2() {
	$.ajax({
		type: "get",
		url: ip + "/auth/cover/3",
		success: function(data) {
			if(data.result == false) {
				$(".c").remove();
				alert('你没有该模块的查看权限')
			}
		}
	});
};

function fc3() {
	$.ajax({
		type: "get",
		url: ip + "/auth/cover/4",
		success: function(data) {
			if(data.result == false) {
				$(".d").remove();
				alert('你没有该模块的查看权限')
			}
		}
	});
};

function fc4() {
	$.ajax({
		type: "get",
		url: ip + "/auth/cover/5",
		success: function(data) {
			if(data.result == false) {
				$(".e").remove();
				alert('你没有该模块的查看权限')
			}
		}
	});
};