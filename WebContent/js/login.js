var userURL = "http://localhost:8080/TechWorld/rest/users";
var user = "";
var ADMIN ="admin";
var STAFF = "staff";
var USER = "user";

var loginVar = localStorage.getItem('loginVar');
$(document).ready(function () {
	/*
	if (loginVar == 1){
		$('#login').hide();
		$('#admin').show();
		$('#user').show();
		$('#logout').show();	
	
	}else if (loginVar == 0){
		$('#login').show();
		$('#account').hide();
		$('#logout').hide();
		$('#admin').hide();
	
	}
	else{
		$('#login').show();
		$('#account').hide();
		$('#logout').hide();
		$('#admin').hide();
	}
	*/
	$('#login').click(function () {
		login();
		return false;
	});
	
	$('#logout').click(function () {
		logout();
		$('#logout').hide();
	
		return false;
	});

});