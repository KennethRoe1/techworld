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
		$('#adminTab').show();
		$('#usersTab').show();
		$('#logout').show();	
	
	}else if (loginVar == 0){
		$('#login').show();
		$('#account').hide();
		$('#logout').hide();
		$('#adminTab').hide();
		$('#usersTab').hide();
	
	}
	else{
		$('#login').show();
		$('#account').hide();
		$('#logout').hide();
		$('#adminTab').hide();
		$('#usersTab').hide();
	}
	*/
	$(document).on("click","#login",function(){
		$('#loginModal').modal('show');
		return false;
	});
	
	$('#loginBtn').click(function () {
		console.log("login pressed");
		login();
		return false;
	});
	
	$('#logout').click(function () {
		logout();
		$('#logout').hide();
		return false;
	});
	
	function logout(){
		clearLogin();
		loginVar = 0;
		localStorage.setItem('loginVar', 0);
		window.location.reload(true);
	};
	function clearLogin(){
		$('#email').val('');
		$('#pass').val('');
	};

	function login(){
		var usernameToCheck=$('#email').val();
		var passwordToCheck=$('#pass').val();
		if (!usernameToCheck || !passwordToCheck){
			$('#loginError').slideDown().html('<span id="error">You must enter a username and password</span>');	
		}
		else{
			user = findByEmail(usernameToCheck);
			if(user != null){
				var correctUsername = user.name;
				var correctPassword = user.pass;
				console.log(correctUsername+' '+correctPassword)
				if(passwordToCheck == correctPassword){
					//Temp login admin
					loginVar = 1;
					localStorage.setItem('loginVar', 1);
					console.log("admin")
					 window.location.reload();
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
	
	var  findByEmail= function(email) {
		var Cuser;
		console.log('findByEmail: ' + email);
		$.ajax({
			type: 'GET',
			url: userURL + '/query?email='+email,
			dataType: "json",
			async: false,
			success: function (data) {
				$('#btnLogout').show();
				Cuser = data
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("user doesnt exist error")
			}
		});
		return Cuser;
	};
});