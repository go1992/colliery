// (function(){
//     var timerCheck = function(){
//         console.log('98kkkkkkkk')
//         $.post(HOST+'/aqfx-csxg/query',{},function(result){
//             // console.log('glng',result);
//         });
//         window.TIMER_DID = true;
//     }
//     if(window.TIMER_DID==true){}
//     else{
//         window.setInterval(timerCheck(),5000); 
//     }
// });
$(function(){
    try {
        var user = JSON.parse(window.localStorage.ACCTOUN_USER);
        var username = user.user.yhmdd;
        $('#showUserName').html('当前账户:'+username)
    } catch (error) {
        
    }

});