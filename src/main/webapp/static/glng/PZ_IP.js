//var ip = "http://47.103.35.95:8887";
var host = location.href.match(/(http[s]?\:\/\/)(.*?)\//);
var ip = "http://" + host[2];
//var ip = "http://111.231.83.143:8090";
//为ajax设置跨域带cookie
$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
	options.xhrFields = {
		withCredentials: true
	}
});
$(document).ajaxSuccess(function(event, xhr, options) {
			var data = $.parseJSON(xhr.responseText);

			if(data.hasOwnProperty("code")) {
					if(data.code == "888888") {
						layer.open({
							type: 2,
							title: '密钥过期',
							maxmin: true,
							shadeClose: true, //点击遮罩关闭层
							area: ['500px', '200px'],
							content: 'html/xtsz/xtsz_key.html'
						});
					}
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