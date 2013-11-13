/**
 * Created with IntelliJ IDEA.
 * User: yosef
 * Date: 11/10/13
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */

var CourseApp = angular.module("CourseApp", ["ngRoute", "ngResource", "ui.bootstrap", 'ngAnimate']).
    config(function($routeProvider) {
        $routeProvider.
            when('/', { controller: ListCtrl, templateUrl: 'list.html' }).
            when('/new', {controller: EditCtrl, templateUrl: 'detail.html'}).
            when('/edit/:itemId', {controller: EditCtrl, templateUrl: 'detail.html'}).
            otherwise({ redirectTo: '/' });
    })
    ;

CourseApp.directive('sorted', function() {
    return {
        scope: true,
        transclude: true,
        template: '<a ng-click="do_sort()" ng-transclude></a>' +
            '<span ng-show="do_show(false)"><i class="icon-arrow-down"></i></span>' +
            '<span ng-show="(true != is_desc) && (sort_order == sort)"><i class="icon-arrow-up"></i></span>',
        controller: function($scope, $element, $attrs) {
            $scope.sort = $attrs.sorted;

            $scope.do_sort = function() {
                $scope.sort_by($scope.sort);
            };

            $scope.do_show = function(asc) {
                return (asc != $scope.is_desc) && ($scope.sort_order == $scope.sort);
            };
        }
    }
});

CourseApp.directive('controlGroup', function() {
    return {
        scope: true,
        transclude: true,
        template: '<div class="control-group" ng-class="err_class">' +
            '<label class="control-label" for="{{field}}" ng-transclude></label>' +
            '<div class="controls">' +
            '<input type="text" ng-model="item.field" id="{{field}}">' +
            '</div></div>',
        controller: function($scope, $element, $attrs) {
            console.log($attrs.controlGroup);
            $scope.field = $attrs.controlGroup;
            $scope.err_class = "{error: form." + $scope.field  + ".$invalid}";
       }
    }
});

CourseApp.factory('Courses', function($resource) {
    return $resource('/api/Test/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        query:  {   method: 'GET',
                    isArray:true,
                    headers:{'Accept':'application/json'}
        },
        DeleteMulti: { method: 'POST', url: '/api/Test/DeleteMulti'}
        });
});


