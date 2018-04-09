var userURL = "http://localhost:8080/TechWorld/rest/users";
var user;

var loginVar = localStorage.getItem('loginVar');
$(document).ready(function () {
	
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
	
	$(document).on("click","#login",function(){
		$('#loginModal').modal('show');
		return false;
	});
	
	$(document).on('click','#loginBtn', function(){
		console.log("login pressed");
		login();
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
		localStorage.setItem('loginVar', 0);
		window.location.reload(true);
	};
	function clearLogin(){
		$('#email').val('');
		$('#pass').val('');
	};

function login(){
	var emailToCheck=$("#email").val();
	var passwordToCheck=$("#pass").val();
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
