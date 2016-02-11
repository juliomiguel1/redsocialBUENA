var app =  angular.module('chatApp', ['firebase']);
 
	app.controller('chatController', ['$scope','Message','$filter', function($scope,Message,$filter){
			$scope.date = new Date();
			$scope.result = null;
 			var s= new Date();

 			$scope.$watch('fecha', function () {

                $scope.result = $filter('date')(new Date(), "dd/MM/yyyy HH:mm:ss");

            }, true);

			$scope.messages= Message.all;
			//message.date= new Date();
			$scope.insertar = function(mensaje){
				mensaje.fecha = $scope.result
                Message.create(mensaje);
				mensaje.text = " ";
			};
	}]);


 	
	app.filter('reverse', function() {
  		return function(items) {
    	return items.slice().reverse();
  		};
	});

	

