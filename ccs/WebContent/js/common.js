$(function(){
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); 

});