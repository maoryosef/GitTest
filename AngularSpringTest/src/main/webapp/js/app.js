/**
 * Created with IntelliJ IDEA.
 * User: yosef
 * Date: 11/10/13
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */

var CourseApp = angular.module("CourseApp", ["ngRoute", "ngResource"]).
    config(function($routeProvider) {
        $routeProvider.
            when('/', { controller: ListCtrl, templateUrl: 'list.html' }).
            otherwise({ redirectTo: '/' });
    })
    ;

CourseApp.directive('sorted', function() {
    return {
        scope: true,
        transclude: true,
        template: '<a ng-click="do_sort()" ng-transclude></a>' +
            '<span ng-show="do_show(true)"><i class="icon-arrow-down"></i></span>' +
            '<span ng-show="(false != is_desc) && (sort_order == sort)"><i class="icon-arrow-up"></i></span>',
        controller: function($scope, $element, $attrs) {
            $scope.sort = $attrs.sorted;

            $scope.do_sort = function() {
                $scope.sort_by($scope.sort);
                $scope.anotherFunc();
            };

            $scope.do_show = function(asc) {
                return (asc != $scope.is_desc) && ($scope.sort_order == $scope.sort);
            };
        }
    }
});

CourseApp.factory('Courses', function($resource) {
    return $resource('/api/Test/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        query:  {   method: 'GET',
                    isArray:true,
                    headers:{'Accept':'application/json'}
        }
        });
});

var ListCtrl = function($scope, Courses) {
    $scope.search = function() {
        Courses.query({q: $scope.query, sort: $scope.sort_order, desc: $scope.is_desc},
            function(items) {
                $scope.items = $scope.items.concat(items);
            });
    }

    $scope.sort_by = function(col) {
        if ($scope.sort_order === col) {
            $scope.is_desc = !$scope.is_desc;
        } else {
            $scope.sort_order = col;
            $scope.is_desc = false;
        }

        $scope.reset();
    }

    $scope.reset = function() {
        $scope.items = [];

        $scope.search();
    };

    $scope.anotherFunc = function() {
        console.log("success");
    }

    $scope.sort_order = "name";
    $scope.is_desc = false;

    /*$scope.cols = new Array();
    $scope.cols[0] = {name: 'id', display: 'ID'};
    $scope.cols[1] = {name: 'name', display: 'Name'};*/

    $scope.reset();
}


