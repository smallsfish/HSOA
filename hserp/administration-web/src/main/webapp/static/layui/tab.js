
//返回顶部
//滚动条
$(function () {
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 600) {
            $('#to-top').fadeIn();
        }
        else {
            $('#to-top').fadeOut();
        }
    });
});
function pageScroll(){ 
	window.scrollBy(0,-40);
	scrolldelay=setTimeout('pageScroll()',.5);
	var sTop=document.documentElement.scrollTop+document.body.scrollTop; 
	if(sTop==0) clearTimeout(scrolldelay); 
}