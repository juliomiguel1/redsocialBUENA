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
moduloAmistad.controller('AmistadNewController', ['$scope', '$routeParams', '$location', 'serverService', 'sharedSpaceService',
    function ($scope, $routeParams, $location, serverService, sharedSpaceService) {
        $scope.id = $routeParams.id;
        $scope.ob = 'amistad';
        $scope.result = null;
        $scope.title = "Crear un nueva amistad";
        $scope.icon = "fa-users";
        if (sharedSpaceService.getFase() == 0) {
            $scope.obj = {
                id: 0,
                id_usuario: 0,
                obj_usuario: {
                    id: 0
                },
                id_usuario2: 0,
                obj_usuario2: {
                    id: 0
                },
                id_grupo: 0,
                obj_grupo: {
                    id: 0
                }
            };
        } else {
            $scope.obj = sharedSpaceService.getObject();
            sharedSpaceService.setFase(0);
        }
        $scope.chooseOne = function (foreignObjectName,numeroUsuario) {
            sharedSpaceService.setObject($scope.obj);
            sharedSpaceService.setReturnLink('/' + $scope.ob + '/new');
            sharedSpaceService.setFase(1);
            sharedSpaceService.setNumeroUsuario=numeroUsuario;
            $location.path('/' + foreignObjectName + '/selection/1/10');
        }
        $scope.save = function () {
            console.log("save");
            console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});
            //strValues = serverService.array_identificarArray(thisObject.form_getFormValues(strClass));
            serverService.getDataFromPromise(serverService.promise_setOne($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
                $scope.result = data;
            });
        };
        $scope.$watch('obj.obj_usuario.id', function () {
            serverService.getDataFromPromise(serverService.promise_getOne('usuario', $scope.obj.obj_usuario.id)).then(function (data2) {
                $scope.obj.obj_usuario = data2.message;
            });
        });
        $scope.$watch('obj.obj_usuario2.id', function () {
            serverService.getDataFromPromise(serverService.promise_getOne('usuario', $scope.obj.obj_usuario2.id)).then(function (data2) {
                $scope.obj.obj_usuario2 = data2.message;
            });
        });
        $scope.$watch('obj.obj_grupo.id', function () {
            serverService.getDataFromPromise(serverService.promise_getOne('grupo', $scope.obj.obj_grupo.id)).then(function (data2) {
                $scope.obj.obj_grupo = data2.message;
            });
        });
       
        $scope.back = function () {
            window.history.back();
        };
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/amistad/plist');
        };

    }]);