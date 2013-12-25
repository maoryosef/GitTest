var EditCtrlPopUp = function($scope, $modalInstance, Tickets, itemId) {
    console.log(itemId);
    if (itemId !== undefined) {
        $scope.item = Tickets.get({ id: itemId });

        $scope.save = function () {
            Tickets.update({id: $scope.item.id}, $scope.item, function () {
                var data = {message: 'Updated ticket with id: ' + $scope.item.id, is_new: false, course: $scope.item}
                $modalInstance.close(data);
            });
        };

        $scope.actionName = "Edit";
    } else {
        $scope.item = {name: '', openDate: '', description: ''};
        $scope.save = function () {
            Tickets.save($scope.item, function(res) {
                $scope.item.id = res.id;
                var data = {message: 'Added new course (' + $scope.item.id + ')', is_new: true, course: $scope.item}
                $modalInstance.close(data);
            });
        };

        $scope.actionName = "Add";
    }

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}