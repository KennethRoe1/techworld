var rootURL = "http://localhost:8080/TechWorld/rest/users/";
var user = "";
var ADMIN ="admin";
var USER = "user";

var loginVar = localStorage.getItem('loginVar');

$(document).ready(function () {
	
	if (loginVar == 1){
		$('#Login').hide();
		$('#adminTab1').show();
		$('#adminTab2').show();
		$('#userTab1').hide();
		$('#userTab2').hide();
	}
	else if (loginVar == 2 ){
		$('#Login').hide();
		$('#adminTab1').show();
		$('#adminTab2').show();
		$('#userTab1').hide();
		$('#userTab2').hide();
	}
	else if (loginVar == 0){
		$('#btnLogin').show();
		$('#users').hide();
		$('#btnLogout').hide();
	}
	else{
		$('#btnLogin').show();
		$('#users').hide();
		$('#btnLogout').hide();
	}

	
	$('#btnLogin').click(function () {
		login();
		return false;
	});

	$('#btnLogout').click(function () {
		logout();
		$('#btnLogout').hide();

		return false;	// this cancels the default action of the browser
	});
});

function logout(){
	clearEmailAndPasswordField();
	loginVar = 0;
	localStorage.setItem('loginVar', 0);
	window.location.reload(true);
};

function login(){
	
	var emailToCheck=$('#email').val();
	var passwordToCheck=$('#pass').val();
	if (!emailToCheck || !passwordToCheck){
		$('#loginError').slideDown().html('<span id="error">You must enter a email and password</span>');	
	}else{
		user = findByEmail(emailToCheck);
		if(user != null){
			var correctEmail = user.email;
			var correctPassword = user.password;
			if(passwordToCheck == correctPassword){
				userTypeLogin(user.role);
			}else{
				$('#pass').val('');
				$('#loginError').slideDown().html('<span id="error">Invalid Password</span>');	
				$('#btnLogout').hide();
			}

		}else{
			console.log("user error");
			clearEmailAndPasswordField();
			$('#loginError').slideDown().html("<span>Invalid Email</span>");
			$('#btnLogout').hide();
		}
	}
	return false;
};

var  findByEmail= function(email) {
	var userData;
	console.log('findByEmail: ' + email);
	$.ajax({
		type: 'GET',
		url: rootURL + '/'+email+'/',
		dataType: "json",
		async: false,
		success: function (data) {
			$('#btnLogout').show();
			userData = data
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("user doesnt exist error")
		}
	});
	return userData;
};

function userTypeLogin(userRole){
	console.log("userTypeLogin "+userRole)
	if (userRole=="admin"){
		user="admin"
		loginVar = 1;
		localStorage.setItem('loginVar', 1);
		console.log("admin")
		 window.location.reload();
	}else if (userRole=="user"){
		user="user";
		loginVar = 2;
		localStorage.setItem('loginVar', 2);
		 window.location.reload();
	}
};

function clearEmailAndPasswordField(){
	$('#email').val('');
	$('#pass').val('');
};

function loadUserTemplate(userRole){
	hideAll();
	$('#other').show();
	$('#wrapper').empty();
	$("#wrapper").load("templates/"+userRole+".html", function(responseTxt, statusTxt, xhr){
		if(statusTxt == "success")
			console.log(userRole+"sheet loaded");
		if(statusTxt == "error")
			console.log("error on sheet load");
	});
	$('#homePage').show();
	fillSidebar();
	clearEmailAndPasswordField();
};
