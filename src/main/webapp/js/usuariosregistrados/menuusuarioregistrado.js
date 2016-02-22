$(document).ready(function(){
            
            
		setInterval(function(){
			$(".imagencorreo").animate({"top":"-10px"},500,function(){
				$(".imagencorreo").animate({"top":"0"},500);
			});
		},1000);

});