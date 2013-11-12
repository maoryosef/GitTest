var EditCtrlPopUp = function($scope, $modalInstance, Courses, itemId) {
    console.log(itemId);
    if (itemId !== undefined) {
        $scope.item = Courses.get({ id: itemId });

        $scope.save = function () {
            Courses.update({id: $scope.item.id}, $scope.item, function () {
                var data = {message: 'Updated course with id: ' + $scope.item.id, is_new: false, course: $scope.item}
                $modalInstance.close(data);
            });
        };

        $scope.actionName = "Edit";
    } else {
        $scope.item = {name: '', startDate: ''};
        $scope.save = function () {
            Courses.save($scope.item, function(res) {
                $scope.item.id = res.id;
                var data = {message: 'Added new course', is_new: true, course: $scope.item}
                $modalInstance.close(data);
            });
        };

        $scope.actionName = "Add";
    }

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}