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
    }).directive('sortheader', function() {
            return {
                restrict: 'A',
                scope: {
                    fieldname:'@fieldname',
                    fieldDisplayName: '@sortheader',
                    sort: '&'
                },
                transclude: true,
                template:   '<a ng-click="sort()">{{fieldDisplayName}}</a>' +
                            '<span ng-show="sort_order==\'{{fieldname}}\' && is_desc == true"><i class="icon-arrow-down"></i></span>' +
                            '<span ng-show="sort_order==\'{{fieldname}}\' && is_desc == false"><i class="icon-arrow-up"></i></span>',
                link: function(scope, element, attrs) {
                    scope.sort = function() { $parent.sort(scope.fieldname); }
                }
            }
        })
    ;

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
        $scope.items = Courses.query({sort: $scope.sort_order, desc: $scope.is_desc});
    }

    $scope.sort = function(col) {
        if ($scope.sort_order === col) {
            $scope.is_desc = !$scope.is_desc;
        } else {
            $scope.sort_order = col;
            $scope.is_desc = false;
        }

        $scope.search();
    }

    $scope.sort_order = "name";
    $scope.is_desc = false;

    $scope.search();
}


