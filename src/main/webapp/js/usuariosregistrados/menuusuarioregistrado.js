
'use strict';


moduloMenuusuarioregistrado.controller('MenuusuairoregistradoController', ['$scope', '$routeParams', 'serverService','$location','sharedSpaceService', '$filter',
    function ($scope, $routeParams, serverService, $location, sharedSpaceService,$filter) {
       
        $scope.ob = 'comentario';
        $scope.id = $routeParams.id;
        $scope.total = 0;
        
        $scope.callAtInterval1 = function () {
            serverService.getDataFromPromise(serverService.promise_getMensajesnuevos('comentario')).then(function (data) {
                $scope.total = data.message;

            });
        }

        $interval(function () {
            $scope.callAtInterval1();
        }, 1000);        
        
        
        /*select comentariopublicaciones.* from comentariopublicaciones, publicaciones, usuario where comentariopublicaciones.id_publicaciones = publicaciones.id AND usuario.id = publicaciones.id_usuario AND usuario.id= 1*/
    }]);