$(document).ready(function(){

		setInterval(function(){
			$(".imagencorreo").animate({"top":"-10px"},1000,function(){
				$(".imagencorreo").animate({"top":"0"},1000);
			});
		},2000);

	});