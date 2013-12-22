var EditCtrl = function($scope, $routeParams, $location, Tickets) {
    if ($routeParams.itemId !== undefined) {
        $scope.item = Tickets.get({ id: $routeParams.itemId });

        $scope.save = function () {
            Tickets.update({id: $scope.item.id}, $scope.item, function () {
                $location.path('/');
            });
        };
    } else {
        $scope.save = function () {
            Tickets.save($scope.item, function() {
                $location.path('/');
            });
        };
    }
}