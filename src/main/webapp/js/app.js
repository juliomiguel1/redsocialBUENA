/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
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
 */

'use strict';

//var appName = 'AjaxStockNg';

var openAusias = angular.module('myApp', [
    'ngRoute',
    'Filters',
    'Services',
    'systemControllers',
    'documentoControllers',
    'usuarioControllers',    
    'tipodocumentoControllers',
    'tipousuarioControllers',
    'perfilControllers',
    'grupoControllers',
    'amistadControllers',
    'comentarioControllers',
    'ui.bootstrap',
    'ngSanitize' //http://stackoverflow.com/questions/9381926/insert-html-into-view-using-angularjs
]);

openAusias.config(['$routeProvider', function ($routeProvider) {
        
        $routeProvider.when('/', {templateUrl: '/home'});
        //------------
        $routeProvider.when('/home', {templateUrl: 'js/system/home.html', controller: 'HomeController'});
        //------------
        $routeProvider.when('/documento/view/:id', {templateUrl: 'js/documento/view.html', controller: 'DocumentoViewController'});
        $routeProvider.when('/documento/new', {templateUrl: 'js/documento/new.html', controller: 'DocumentoNewController'});
        $routeProvider.when('/documento/edit/:id', {templateUrl: 'js/documento/edit.html', controller: 'DocumentoEditController'});
        $routeProvider.when('/documento/remove/:id', {templateUrl: 'js/documento/remove.html', controller: 'DocumentoRemoveController'});
        $routeProvider.when('/documento/plist/:page?/:rpp?', {templateUrl: 'js/documento/plist.html', controller: 'DocumentoPListController'});

        //------------
        $routeProvider.when('/usuario/view/:id', {templateUrl: 'js/usuario/view.html', controller: 'UsuarioViewController'});
        $routeProvider.when('/usuario/new', {templateUrl: 'js/usuario/new.html', controller: 'UsuarioNewController'});
        $routeProvider.when('/usuario/edit/:id', {templateUrl: 'js/usuario/edit.html', controller: 'UsuarioEditController'});
        $routeProvider.when('/usuario/remove/:id', {templateUrl: 'js/usuario/remove.html',   controller: 'UsuarioRemoveController'});
        $routeProvider.when('/usuario/plist/:page?/:rpp?', {templateUrl: 'js/usuario/plist.html', controller: 'UsuarioPListController'});
      //  $routeProvider.when('/usuario/selection/:page/:rpp', {templateUrl: 'js/usuario/selection.html', controller: 'UsuarioSelectionController'});
        $routeProvider.when('/usuario/selection/:page/:rpp/:id_usuario?', {templateUrl: 'js/usuario/selection.html', controller: 'UsuarioSelectionController'});


        //------------
        $routeProvider.when('/perfil/view/:id', {templateUrl: 'js/perfil/view.html', controller: 'PerfilViewController'});
        $routeProvider.when('/perfil/new/:id_usuario?', {templateUrl: 'js/perfil/new.html', controller: 'PerfilNewController'});
        $routeProvider.when('/perfil/edit/:id', {templateUrl: 'js/perfil/edit.html', controller: 'PerfilEditController'});
        $routeProvider.when('/perfil/remove/:id', {templateUrl: 'js/perfil/remove.html',   controller: 'PerfilRemoveController'});
        $routeProvider.when('/perfil/plist/:page?/:rpp?', {templateUrl: 'js/perfil/plist.html', controller: 'PerfilPListController'});
        $routeProvider.when('/perfil/selection/:page/:rpp', {templateUrl: 'js/perfil/selection.html', controller: 'PerfilSelectionController'});

        //------------
        $routeProvider.when('/grupo/view/:id', {templateUrl: 'js/grupo/view.html', controller: 'GrupoViewController'});
        $routeProvider.when('/grupo/new', {templateUrl: 'js/grupo/new.html', controller: 'GrupoNewController'});
        $routeProvider.when('/grupo/edit/:id', {templateUrl: 'js/grupo/edit.html', controller: 'GrupoEditController'});
        $routeProvider.when('/grupo/remove/:id', {templateUrl: 'js/grupo/remove.html',   controller: 'GrupoRemoveController'});
        $routeProvider.when('/grupo/plist/:page?/:rpp?', {templateUrl: 'js/grupo/plist.html', controller: 'GrupoPListController'});
        $routeProvider.when('/grupo/selection/:page/:rpp', {templateUrl: 'js/grupo/selection.html', controller: 'GrupoSelectionController'});

        //------------
        $routeProvider.when('/amistad/view/:id', {templateUrl: 'js/amistad/view.html', controller: 'AmistadViewController'});
        $routeProvider.when('/amistad/new/:id_usuario?', {templateUrl: 'js/amistad/new.html', controller: 'AmistadNewController'});
        $routeProvider.when('/amistad/edit/:id', {templateUrl: 'js/amistad/edit.html', controller: 'AmistadEditController'});
        $routeProvider.when('/amistad/remove/:id', {templateUrl: 'js/amistad/remove.html',   controller: 'AmistadRemoveController'});
        //$routeProvider.when('/amistad/plist/:page?/:rpp?', {templateUrl: 'js/amistad/plist.html', controller: 'AmistadPListController'});
        $routeProvider.when('/amistad/selection/:page/:rpp/:id_usuario?', {templateUrl: 'js/amistad/selection.html', controller: 'AmistadSelectionController'});

        $routeProvider.when('/amistad/plist/:id_usuario?', {templateUrl: 'js/amistad/plist.html', controller: 'AmistadPListController'});


        //------------
        $routeProvider.when('/comentario/view/:id', {templateUrl: 'js/comentario/view.html', controller: 'ComentarioViewController'});
        $routeProvider.when('/comentario/new/:id_usuario?', {templateUrl: 'js/comentario/new.html', controller: 'ComentarioNewController'});
        $routeProvider.when('/comentario/edit/:id', {templateUrl: 'js/comentario/edit.html', controller: 'ComentarioEditController'});
        $routeProvider.when('/comentario/remove/:id', {templateUrl: 'js/comentario/remove.html',   controller: 'ComentarioRemoveController'});
      //  $routeProvider.when('/comentario/plist/:page?/:rpp?', {templateUrl: 'js/comentario/plist.html', controller: 'ComentarioPListController'});
        
        $routeProvider.when('/comentario/plist/:id_usuario?', {templateUrl: 'js/comentario/plist.html', controller: 'ComentarioPListController'});

        //------------
        $routeProvider.when('/tipodocumento/view/:id', {templateUrl: 'js/tipodocumento/view.html', controller: 'TipodocumentoViewController'});
        $routeProvider.when('/tipodocumento/selection/:page/:rpp', {templateUrl: 'js/tipodocumento/selection.html', controller: 'TipodocumentoSelectionController'});
        
        
        
        $routeProvider.when('/tipousuario/selection/:page/:rpp', {templateUrl: 'js/tipousuario/selection.html', controller: 'TipousuarioSelectionController'});        
        //------------
        $routeProvider.otherwise({redirectTo: '/'});

        //claves ajenas: usar un módulo compartido para apuntarse la url de llamada: http://stackoverflow.com/questions/12008908/how-can-i-pass-variables-between-controllers-in-angularjs
        //ejemplo claves ajenas con objeto promesa: http://stackoverflow.com/questions/14530251/angular-js-model-relationships

    }]);

var moduloSistema = angular.module('systemControllers', []);
var moduloUsuario = angular.module('usuarioControllers', []);
var moduloDocumento = angular.module('documentoControllers', []);
var moduloTipodocumento = angular.module('tipodocumentoControllers', []);
var moduloTipousuario = angular.module('tipousuarioControllers', []);
var moduloPerfil = angular.module('perfilControllers', []);
var moduloGrupo = angular.module('grupoControllers', []);
var moduloAmistad = angular.module('amistadControllers', []);
var moduloComentario = angular.module('comentarioControllers', []);



