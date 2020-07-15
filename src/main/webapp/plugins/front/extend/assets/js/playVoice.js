//======================播放语音===========================================
var timeoutindex=null;//bofang的控制器
var voiceArr =null;//播放语音列表
var palay = true;//控制是否播放语音
$(function () {
    $('#voiceSwitch').click(function () {
        if (palay) {
            palay = false;
            $(this).children("img").attr("src",'../plugins/front/extend/assets/images/icon-mute.png').attr('title',"打开语音播报");
        }else{
            palay = true;
            $(this).children('img').attr("src",'../plugins/front/extend/assets/images/icon-play.png').attr('title',"关闭语音播报");
        }
    })
})
function controlVoicePlay(arr){
    //判断播放的列表是否相等，相等不需要重新赋值，不相等重置
    if (voiceArr==null || voiceArr.toString()!= arr.toString()){
        clearInterval(timeoutindex);
        timeoutindex=null;
        index=0;//播放坐标归0
        voiceArr = arr;
    }
    if (timeoutindex == null) {
        //默认延迟3000秒 给操作结果播报语音留出时间
        noticeBofang(3000);
    }
}
function noticeBofang(delayTime) {
    timeoutindex = setTimeout("bofang()",delayTime);
}
var index=0;//播放坐标
function bofang(){
    if (palay){
        playVoice("../plugins/front/voice/"+voiceArr[index]+".wav");
    }
    if (index<voiceArr.length-1){
        //回调播放 判断end 防止出现 语音叠加
        if(voiceArr[index] == 'end') {
            noticeBofang(3000);
        }else  {
            noticeBofang(500);
        }
        index++;
    }else{
        index=0;//播放坐标归0
        timeoutindex=null;
    }
}
function playVoice(audioSrc){
    var borswer = window.navigator.userAgent.toLowerCase();
    if ( borswer.indexOf( "ie" ) >= 0 ){
        var player  = document.createElement('bgsound');
        player.src = audioSrc;
        player.setAttribute('autostart', 'true');
        player.setAttribute('loop', 'false');
        document.body.appendChild(player);
    } else{
        var audio = new Audio(audioSrc);
        audio.play();
    }
}
//=======================播放语音=========================================