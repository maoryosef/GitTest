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
    });

CourseApp.factory('Courses', function($resource) {
    return $resource('/api/Test/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        query:  {   method: 'GET',
                    isArray:false,
                    headers:{'Accept':'application/json'}
        }
        });
});

var ListCtrl = function($scope, Courses) {
    $scope.items = Courses.query();
}


