function IndexController($scope,$rootScope, $http, $location){
	$scope.$on("$viewContentLoaded",function(){
		$scope.hello="Hello World";
	})
	
	$scope.login=function(){
		$http.post("/login", $scope.user).success(function(data){
			$rootScope.user = data;
			$location.path("/home");
		})
	}
	
	$scope.registrate=function(){
		$http.post("/registrate", $scope.user).success(function(){
			$location.path = "/";
		})
	}
}