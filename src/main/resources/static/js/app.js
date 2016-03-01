var mainApp = angular.module('main', [ 'ngRoute']);
mainApp.config(

function($routeProvider, $httpProvider, $locationProvider, $provide) {

	$routeProvider.when('/', {
		templateUrl : 'public/template/login',
		controller : IndexController
	}).when('/registr', {
		templateUrl : 'public/template/registration',
		controller : IndexController
	}).when('/home', {
		templateUrl : 'user/template/home',
		controller : IndexController
	}).otherwise('/');

	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});

	/**
	 * i18n configuration.
	 */

	$httpProvider.defaults.withCredentials = true;
	$httpProvider.interceptors.push(function($q, $location) {
		return {
			'responseError' : function(rejection) {
				var defer = $q.defer();
				if (rejection.status == 401) {
					location.reload();
					console.log("401 redirecting to login");
					$location.path("/index.html");
					console.dir(rejection);
				}
				defer.reject(rejection);
				return defer.promise;
			}
		};
	});
});

mainApp.controller('IndexController', IndexController);


mainApp.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;
			element.bind('change', function() {
				scope.file_name = element[0].files[0].name;
				console.log(element[0].files[0].name);
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);



mainApp.run(function($rootScope, $location, $http, $window, $timeout, $filter) {
	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"newestOnTop" : false,
		"progressBar" : true,
		"positionClass" : "toast-top-right",
		"preventDuplicates" : true,
		"onclick" : null,
		"showDuration" : "3000",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
});
