$(document).ready(function(){
	findAll2();
});
var userURL = "http://localhost:8080/TechWorld/rest/users";
var currentUser;

$(document).on("click", "#userTable a",function(){
	event.preventDefault();
	$('#userModal').modal('show');
	findById2(this.id);
});

var findAll2 = function() {
	console.log('findAll2');
	$.ajax({
		type: 'GET',
		url: userURL,
		dayaType:"json",
		success: renderList2
	});
};

$(document).on("click", '#btnAdd2', function(){
	newUser();
});
$(document).on("click", '#create2', function(){
	if($('#id').val()=='')
		addUser();
	else
		updateUser();
	return false;
});
$(document).on("click", '#delete2', function(){
	deleteUser();
});

var findById2= function(id){
	console.log('findById '+id);
	$.ajax({
		type: 'GET',
		url: userURL + '/'+id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: '+data.name);
			currentUser = data;
			$('#delete').show();
			renderDetails2(currentUser);
		}
	});
};

var newUser = function(){
	$('#user_id').val('');
	$('#user_name').val('');
	$('#user_email').val('');
	$('#user_pass').val('');
	$('#user_address').val('');
	$('#user_dob').val('');
	$('#user_role').val('');
	$('#delete2').hide();
};

var renderDetails2=function(user){
	$('#user_id').val(user.id);
	$('#user_name').val(user.name);
	$('#user_email').val(user.email);
	$('#user_pass').val(user.pass);
	$('#user_address').val(user.address);
	$('#user_dob').val(user.dob);
	$('#user_role').val(user.role);
};

var formToJSON2A=function(){
	return JSON.stringify({
		"name": $('#user_name').val(),
		"email":$('#user_email').val(),
		"pass":$('#user_pass').val(),
		"address": $('#user_address').val(),
		"dob": $('#user_dob').val(),
		"role": $('#user_role').val()
	});
};

var formToJSON2U=function(){
	return JSON.stringify({
		"id":$('').val('#user_id'),
		"name": $('#user_name').val(),
		"email":$('#user_email').val(),
		"pass":$('#user_pass').val(),
		"address": $('#user_address').val(),
		"dob": $('#user_dob').val(),
		"role": $('#user_role').val()
	});
};

function renderList2(data){
	list=data;
	$.each(list, function(index, user){
		$('#userTable').append('<tr><td>'+user.name+'</td><td>'+user.email+
		'</td><td>'+user.address+'</td><td>'+user.dob+'</td><td><a id="'+user.id+'" href="edit">Edit</td></tr>');
	});
	$('#table_id2').DataTable();
};

var addIUser = function(){
	console.log('addItem');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: userURL,
		dataType: "json",
		data: formToJSON2A(),
		success: function(data, textStatus, jqXHR){
			alert('User created successfully');
			$('#userId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('add Usererror: '+textStatus);
		}
	});
};

var updateUser = function(){
	console.log('updateItem '+$('#itemId').val());
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL+'/'+$('#userId').val(),
		dataType: "json",
		data: formToJSON2U(),
		success: function(data, textStatus, jqXHR){
			alert('User updated successfully');
			console.log(data);
		},
		error: function(jqHXR, textStatus, errorThrown){
			alert('update User error: '+textStatus);
		}
	});
};

var deleteUser = function(){
	console.log('deleteItem');
	$.ajax({
		type: 'DELETE',
		url: rootURL+'/'+$('#userId').val(),
		success: function(data, textStatus, jqXHR){
			alert('User deleted successfully');
			newUser();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('delete User error: '+textStatus);
		}
	});
};