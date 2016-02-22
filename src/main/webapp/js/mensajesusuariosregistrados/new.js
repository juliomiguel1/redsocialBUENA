/* 
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */


'use strict';





moduloMensajes.controller('MensajesNewController', ['$scope', '$routeParams', 'serverService','$location','sharedSpaceService', '$filter', '$interval',
    function ($scope, $routeParams, serverService, $location, sharedSpaceService,$filter, $interval) {
        $scope.title = "Vista de usuario";
        $scope.icon = "fa-text";
        $scope.ob = 'comentario';
        $scope.id = $routeParams.id;
        
       // if (sharedSpaceService.getFase() == 0) {
            $scope.obj = {
                id: 0,
                texto: "",
                fecha: "",
                id_amistad: 0,
                obj_amistad: {
                    id: 0,
                    id_usuario2:0
                },
                leido: 0
            };
       /* } else {
            $scope.obj = sharedSpaceService.getObject();
            sharedSpaceService.setFase(0);
        }*/
        
        
        serverService.getDataFromPromise(serverService.promise_getAll("amistad")).then(function (data) {
            $scope.bean = data.message;
           
        });
        
         $scope.callAtInterval = function () {
            serverService.getDataFromPromise(serverService.promise_getMensajesnuevos("comentario")).then(function (data) {
                $scope.total = data.message;
             /*   if ($scope.total != 0) {
                   
                    $(".imagencorreo").animate({"top": "-10px"}, 500, function () {
                        $(".imagencorreo").animate({"top": "0"}, 500);
                    });
                   
                }*/
            });
        }

        $interval(function () {
            $scope.callAtInterval();
        }, 1000); 
        
        
        $scope.go= function(obj){
            
            var dateFechaAsString = $filter('date')(new Date(), "dd/MM/yyyy HH:mm:ss");
            $scope.obj.fecha = dateFechaAsString;
            
            serverService.getDataFromPromise(serverService.promise_setOneMensaje($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
               if(data.status == "200"){
                $scope.result = data;
                    $(".mensajeenviado").html("<p>Message send!!</p>");
                    
                }
            });
        }
        
        $scope.save = function () {          
            //console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});            
            serverService.getDataFromPromise(serverService.promise_setOne($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
                $scope.result = data;
            });
        };
        
        /*select comentariopublicaciones.* from comentariopublicaciones, publicaciones, usuario where comentariopublicaciones.id_publicaciones = publicaciones.id AND usuario.id = publicaciones.id_usuario AND usuario.id= 1*/
    }]);