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