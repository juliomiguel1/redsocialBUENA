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

angular.module('Filters', []).
        filter('interpolate', ['version', function (version) {
                return function (text) {
                    return String(text).replace(/\%VERSION\%/mg, version);
                }
            }]);


angular.module('Filters').filter('clipString', function ($filter)
{
    return function (input)
    {
        if (input == null) {
            return "";
        }

        if (input.length > 14) {
            return input.substr(0, 10).trim() + " ...";

        }

    };
});
    
angular.module('Filters').filter('booleanizate', function ($filter)
{
    return function (input)
    {
        if (input == null) {
            return "";
        }

        if (input==true) {
            return '<i class="fa fa-check"></i>';
        }else{
            return '<i class="fa fa-times"></i>';
        }

    };
});

angular.module('Filters').filter('reverse', function ($filter)
{
    return function(items) {
    	return items.slice().reverse();
    };
});


//angular.module('Filters').filter('uppercaseo', function ($filter)
//{
//    return function (input)
//    {
//        if (input == null) {
//            return "";
//        }
//
//
//
//        return input.toUpperCase();
//
//    };
//});

//angular.module('myApp.filters', []).
//        filter('uppercaseo', ['version', function (version) {
//                return function (input)
//                {
//                    if (input == null) {
//                        return "";
//                    }
//                    return input.toUpperCase();
//                };
//            }]);
