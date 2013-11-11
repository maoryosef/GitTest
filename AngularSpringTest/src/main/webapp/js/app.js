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
        }
        });
});

var EditCtrl = function($scope, $routeParams, $location, Courses) {
    if ($routeParams.itemId !== undefined) {
        $scope.item = Courses.get({ id: $routeParams.itemId });

        $scope.save = function () {
            Courses.update({id: $scope.item.id}, $scope.item, function () {
                $location.path('/');
            });
        };
    } else {
        $scope.save = function () {
            Courses.save($scope.item, function() {
                $location.path('/');
            });
        };
    }
}

var ListCtrl = function($scope, Courses) {
    $scope.search = function() {
        Courses.query({q: $scope.query, sort: $scope.sort_order, desc: $scope.is_desc, limit: $scope.limit, offset: $scope.offset},
            function(items) {
                var cnt = items.length;
                $scope.no_more = cnt < 20;
                $scope.items = $scope.items.concat(items);
            });
    }

    $scope.delete = function() {
        var itemId = this.item.id;
        Courses.delete({id: itemId}, function() {
            $("#item_" + itemId).fadeOut();
        })
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
        $scope.limit = 20;
        $scope.offset = 0;

        $scope.search();
    };

    $scope.sort_order = "name";
    $scope.is_desc = false;

    $scope.show_more = function() {
        return !$scope.no_more;
    }

    /*$scope.cols = new Array();
    $scope.cols[0] = {name: 'id', display: 'ID'};
    $scope.cols[1] = {name: 'name', display: 'Name'};*/

    $scope.reset();
}


