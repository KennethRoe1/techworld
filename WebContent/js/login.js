var userURL = "http://localhost:8080/TechWorld/rest/users";
var user;
var ADMIN ="admin";
var CUSTOMER = "user";

var loginVar = localStorage.getItem('loginVar');
var userVar = localStorage.getItem('userVar');
$(document).ready(function () {
	console.log(loginVar);
	if (loginVar == 1){
		$('#login').hide();
		$('#addBasket').hide();
		$('#adminTab').show();
		$('#usersTab').show();
		$('#logout').show();
		$('#accountTab').show();
		$('#basketTab').hide();
		$('#aBB').hide();
	}
	else if (loginVar == 2){
		$('#account').show();
		$('#logout').show();
		$('#basketTab').show();
		$('#login').hide();
		$('#adminTab').hide();
		$('#usersTab').hide();
		$('#aBB').show();
		$('#accountTab').show();
	
	}
	else if (loginVar == 0){
		$('#login').show();
		$('#account').hide();
		$('#logout').hide();
		$('#adminTab').hide();
		$('#usersTab').hide();
		$('#basketTab').hide();
		$('#aBB').hide();
		$('#accountTab').hide();
		userVar = 0;
	
	}
	else{
		$('#login').show();
		$('#account').hide();
		$('#logout').hide();
		$('#adminTab').hide();
		$('#usersTab').hide();
		$('#addBasket').hide();
		$('#basketTab').hide();
		$('#aBB').hide();
		$('#accountTab').hide();
		userVar = 0;
	}
	if(userVar==1 || userVar==2){
		findByAId(userVar);
	}
	
	$(document).on("click","#login",function(){
		$('#loginModal').modal('show');
		return false;
	});
	
	$(document).on("click","#reg",function(){
		$('#loginModal').modal('hide');
		$('#regModal').modal('show');
		return false;
	});
	
	$(document).on('click','#loginBtn', function(){
		console.log("login pressed");
		login($("#email").val(), $("#pass").val());
		return false;
	});
	
	$(document).on('click','#regBtn', function(){
		console.log("register pressed");
		register();
		return false;
	});
	
	$('#logout').click(function () {
		logout();
		$('#logout').hide();
		return false;
	});
});
	
function logout(){
	clearLogin();
	loginVar = 0;
	userVar = 0;
	localStorage.setItem('loginVar', 0);
	localStorage.setItem('userVar', 0);
	window.location.reload(true);
};
function clearLogin(){
	$('#email').val('');
	$('#pass').val('');
};

function login(emailToCheck, passwordToCheck){
	if (!emailToCheck || !passwordToCheck){
		$('#loginError').slideDown().html('<span id="error">You must enter a username and password</span>');	
	}
	else{
		user = findByEmail(emailToCheck);
		console.log(user);
		if(user != null){
			var correctEmail = user[0].email;
			var correctPassword = user[0].pass;
			console.log('email:'+correctEmail+' pass:'+correctPassword)
			if(passwordToCheck == correctPassword){
				userVar=user[0].id;
				localStorage.setItem('userVar', user[0].id);
				userTypeLogin(user[0].role);
			}else{
				$('#pass').val('');
				$('#loginError').slideDown().html('<span id="error">Invalid Password</span>');	
				$('#btnLogout').hide();
			}
		}
		else{
			console.log("user error");
			clearLogin();
			$('#loginError').slideDown().html("<span>Invalid Username</span>");
			$('#Logout').hide();
		}
	}
	return false;
};
//register needs work, getting error 405 method not allowed 

var formToJSON2B=function(){
	return JSON.stringify({
		"name": $('#nName').val(),
		"email":$('#nEmail').val(),
		"pass":$('#nPass').val(),
		"address": $('#nAddress').val(),
		"dob": $('#nDob').val(),
		"role": "user"
	});
};

var register = function(){
	console.log('register');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: userURL,
		dataType: "json",
		data: formToJSON2B(),
		success: function(data, textStatus, jqXHR){
			alert('User created successfully');
			$('#userId').val(data.id);
			login(data.email, data.pass);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('add User error: '+textStatus);
		}
	});
};

var  findByEmail= function(email) {
	var userData;
	console.log('findByEmail: ' + email);
	$.ajax({
		type: 'GET',
		url: userURL + '/query?email='+email,
		dataType: "json",
		async: false,
		success: function (data) {
			userData = data
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("user doesnt exist error")
		}
	});
	return userData;
};

var findByAId= function(userVar){
	console.log('findByAId '+userVar);
	$.ajax({
		type: 'GET',
		url: userURL + '/'+userVar,
		dataType: "json",
		success: function(data){
			user = data;
			$('#delete').show();
			renderAccount(user);
		}
	});
};

var renderAccount=function(user){
	$('#cur_user_name').val(user.name);
	$('#cur_user_email').val(user.email);
	$('#cur_user_pass').val(user.pass);
	$('#cur_user_address').val(user.address);
	$('#cur_user_dob').val(user.dob);
};

function userTypeLogin(userRole){
	console.log("userTypeLogin "+userRole)
	if (userRole=="admin"){
		loginVar = 1;
		localStorage.setItem('loginVar', 1);
		console.log("manager")
		 window.location.reload();
	}
	else if (userRole=="user"){
		loginVar = 2;
		localStorage.setItem('loginVar', 2);
		console.log("customer")
		 window.location.reload();
	}
};