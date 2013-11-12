var ListCtrl = function($scope, $modal, $timeout, $animate, Courses) {
    $scope.search = function() {
        Courses.query({q: $scope.query, sort: $scope.sort_order, desc: $scope.is_desc, limit: $scope.limit, offset: $scope.offset},
            function(items) {
                var cnt = items.length;
                $scope.no_more = cnt < 20;
                $scope.items = $scope.items.concat(items);

                $timeout(function() {$animate.enabled(true); console.log("enabled")}, 1);
            });
    }

    $scope.delete = function() {
        var itemId = this.item.id;
        var itemIndex = this.$index;
        Courses.delete({id: itemId}, function() {
            $scope.items.splice(itemIndex, 1);
            $scope.setAlert({ type: 'success', msg: 'Item deleted' });
        }, function() {
            $scope.setAlert({ type: 'error', msg: 'Failed deleting item' });
        })
    }

    $scope.edit = function() {
        if (this.item !== undefined) {
            var itemId = this.item.id;
            var itemIndex = this.$index;
            console.log("Editing " + itemId);
        } else {
            console.log("Adding new course");
        }

        var modalInstance = $modal.open({
            templateUrl: 'detail.html',
            controller: EditCtrlPopUp,
            resolve: {
                itemId: function() {
                    return itemId;
                }
            }
        });

        modalInstance.result.then(function (data) {
            if (data.is_new) {
                $scope.items.push(data.course);
            } else {
                $animate.enabled(false);
                $scope.items[itemIndex] = data.course;
                //if enabling animations right away, it will display both animation once item is changed
                $timeout(function() {$animate.enabled(true); console.log("enabled")}, 1);
            }

            $scope.setAlert({ type: 'success', msg: data.message });
        });
    }

    var alertTimeoutId = undefined;

    $scope.setAlert = function(alertObj) {
        console.log("alert time out: " + alertTimeoutId);
        if (alertTimeoutId !== undefined) {
            $timeout.cancel(alertTimeoutId);
        }

        $scope.alert = {data: alertObj, show: true};
        alertTimeoutId = $timeout(function() {$scope.closeAlert()}, 3000);
    }

    $scope.closeAlert = function() {
        $scope.alert.show = false;
        if (alertTimeoutId !== undefined) {
            $timeout.cancel(alertTimeoutId);
        }
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

        $animate.enabled(false);
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