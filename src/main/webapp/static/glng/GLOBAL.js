var host = location.href.match(/(http[s]?\:\/\/)(.*?)\//);
// var HOST = 'http://30.30.30.177:8090/apiv1';
var HOST = "http://"+host[2]+"/apiv1";
// var HOST = 'http://127.0.0.1:8090/apiv1';
var SUCCESS = 'success';
var FAILED = 'failed';
//var UN_LODIN_OUT_FUNC = function(json){
//  if(!!json&&!!json.responseText&&json.responseText=='c8e3a961-3408-4e8d-bcbb-09a5639e0e05'){
//      // if(RegExp(/\/html\/h5/).test(window.location.href)){
//      // 	window.location.href='/share_health_web/html/h5/authentication.html';
//      //     return;
//      // }
//      if(window.location.href.indexOf('/yhgl/layui/web/')==-1){
//      }else{
//          window.location.href='../../../../bigdatalogin.html';
//          return false;
//      }
//      window.location.href='./bigdatalogin.html';
//  }
//}
$.ajaxSetup({
    xhrFields: {
        withCredentials: true
    },
//  complete: UN_LODIN_OUT_FUNC
});

var DEPTS_REF_FUNC = function (){
    $.ajax({
        type:"POST",
        url:HOST+'/xtgn-qyfj/query',
        crossDomain: true,
        async:false,
        data:{},
        xhrFields: {
            withCredentials: true
        },
        success:function(result){
            if(result&&result.length){

                var userDepts = result;
                // console.log(userDepts)
    
                var deptsKeyValue = {};
                var deptsKeyValueAll = {};
                for(var key in userDepts){
                    var eachByKey = userDepts[key];
                    if(!!eachByKey&&eachByKey.id){
                        deptsKeyValue[eachByKey.id] = eachByKey.qymdd;
                        deptsKeyValueAll[eachByKey.id] = eachByKey;
                    }
                }
    
                var resultDeptsKeyValue = {};
                for(var key in userDepts){
                    var eachByKey = userDepts[key];
                    if(!!eachByKey&&eachByKey.id){
                        resultDeptsKeyValue[eachByKey.id] = eachByKey.qymdd;
                        var parentids = eachByKey.parentids.split(',');
                        for(var i = parentids.length-1; i >= 0; i--){
                            var eachByKeyParen = parentids[i];
                            var parentName = deptsKeyValue[eachByKeyParen];
                            // eachByKey.qymdd = parentName?parentName+'-'+eachByKey.qymdd:eachByKey.qymdd;
                            var currentName = resultDeptsKeyValue[eachByKey.id];
                            resultDeptsKeyValue[eachByKey.id] = parentName?parentName+'-'+currentName:currentName;
                        }
                    }
                }
                
                window.localStorage.DEPTS_KEY_VALUE = JSON.stringify(resultDeptsKeyValue);
                window.localStorage.DEPTS_ALL = JSON.stringify(deptsKeyValueAll);
            }

        },
        error:function(jqXHR){
        },
    });	


}