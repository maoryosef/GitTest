var ListCtrl = function($scope, $modal, $timeout, $animate, Tickets) {
    $scope.search = function() {
        Tickets.query({q: $scope.query, sort: $scope.sort_order, desc: $scope.is_desc, limit: $scope.limit, offset: $scope.offset},
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
        Tickets.delete({id: itemId}, function() {
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
                $timeout(function() {$animate.enabled(true); }, 1);
            }

            $scope.setAlert({ type: 'info', msg: data.message });
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

    $scope.isCourseMarked = function() {
        return $scope.markedCourses.indexOf(this.item.id) > -1;
    }

    $scope.toggleCourseMarking = function() {
        var index = $scope.markedCourses.indexOf(this.item.id);

        if (index == -1) {
            $scope.markedCourses.push(this.item.id);
        } else {
            $scope.markedCourses.splice(index, 1);
        }
    }

    $scope.toggleMarkAll = function() {
        var allMarked = $scope.isAllMarked();
        $scope.markedCourses = [];

        if (!allMarked) {
            for (i = 0; i < $scope.items.length; i++) {
                $scope.markedCourses.push($scope.items[i].id);
            }
        }
    }

    $scope.isAllMarked = function() {
        return $scope.markedCourses.length == $scope.items.length;
    }

    $scope.deleteSelected = function() {
        Courses.DeleteMulti({ids: $scope.markedCourses}, function() {
            for (i = 0; i < $scope.items.length; i++) {
                if ($scope.markedCourses.indexOf($scope.items[i].id) > -1){
                    $scope.items.splice(i, 1);
                    i--;
                }
            }

            $scope.markedCourses = [];
            $scope.setAlert({ type: 'success', msg: 'Items deleted' });
        }, function() {
            $scope.setAlert({ type: 'error', msg: 'Failed deleting items' });
        })
    }

    $scope.$watch('items.length == 0', function(newValue, oldValue) {
        console.log("old: " + oldValue + ", new: " + newValue);
        if (oldValue == false && newValue == true) {
            $scope.getMore();
        }

        console.log("done");
    })

    $scope.getMore = function() {
        $scope.offset = $scope.offset + $scope.limit;
        $scope.search();
    }

    $scope.markedCourses = [];
    /*$scope.cols = new Array();
     $scope.cols[0] = {name: 'id', display: 'ID'};
     $scope.cols[1] = {name: 'name', display: 'Name'};*/

    $scope.reset();
}