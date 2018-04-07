$(document).ready(function(){
	findById();
});
var userURL = "http://localhost:8080/TechWorld/rest/users";
var currentUser;

var findById= function(id){
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
			renderDetails(currentUser);
		}
	});
}

var renderDetails=function(item){
	$('#id').val(user.id);
	$('#name').val(user.name);
	$('#email').val(user.email);
	$('#pass').val(user.pass);
	$('address').val(user.address);
	$('dob').val(user.dob);
	$('role').val(user.role);
}

var formToJSON=function(){
	return JSON.stringify({
		"pic": "",
		"name": $('#name').val(),
		"description":$('#description').val(),
		"category":$('category').val(),
		"stock": $('#tock').val(),
		"price": $('#price').val()
	});
};

function renderList(data){
	list=data;
	$.each(list, function(index, item){
		$('#userTable').append('<tr><td>'+item.name+'</td><td>'+item.catagory+
		'</td><td>'+item.price+'</td><td>'+item.stock+'</td><td><a id="'+item.id+'" href="edit">Edit</td></tr>');
	});
	
	$('#table_id').DataTable();
	output='<div class="row">';
	$.each(list, function(index, item){
		var img="pics/"+item.pic;
		console.log(img);
		output+=('<div class="col-sm-6 col-md-4 col-lg-3">'+
				'<div class="card"><img src='+'"'+img+'"'+
				'height="150"><p>Name: '+item.name+'</p>'+
				'<p>Details: '+item.description+'</p>'+
				'<p>Stock: '+item.stock+'</p>'+
				'<p>Price: $'+item.price+'</p>'+
				'</div></div>');
	});
	output+='</div>';
	$('#userList').append(output);
};