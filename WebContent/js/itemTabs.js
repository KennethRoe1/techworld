$(document).ready(function(){
	findAll();
	$('#delete').hide();
});
var rootURL = "http://localhost:8080/TechWorld/rest/items";
var currenItem;

$(document).on("click", "#itemTable a",function(){
	event.preventDefault();
	$('#itemModal').modal('show');
	findById(this.id);
});

$(document).on("click", '#btnAdd', function(){
	newItem();
});
$(document).on("click", '#create', function(){
	if($('#itemId').val()=='')
		addItem();
	else
		updateItem();
	return false;
});
$(document).on("click", '#delete', function(){
	deleteItem();
});

var findAll = function() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dayaType:"json",
		success: renderList
	});
};

var newItem = function(){
	$('#pic').attr('src', '');
	$('#itemId').val("");
	$('#name').val("");
	$('#description').val("");
	$('#category').val("");
	$('#stock').val("");
	$('#price').val("");
	$('#delete').hide();
};

var findById= function(id){
	console.log('findById '+id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/'+id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: '+data.name);
			currentItem = data;
			$('#delete').show();
			renderDetails(currentItem);
		}
	});
};

var renderDetails=function(item){
	$('#itemId').val(item.id);
	$('#pic').attr('src', 'pics/'+item.pic);
	$('#name').val(item.name);
	$('#description').val(item.description);
	$('#category').val(item.category);
	$('#stock').val(item.stock);
	$('#price').val(item.price);
	console.log("rendering "+item.id);
};

var formToJSONA=function(){
	return JSON.stringify({
		"pic": '',//$('#pic').val
		"name": $('#name').val(),
		"description":$('#description').val(),
		"category":$('#category').val(),
		"stock": $('#stock').val(),
		"price": $('#price').val()
	});
};

var formToJSONU=function(){
	return JSON.stringify({
		"id": $('#itemId').val(),
		"pic": '',//$('#pic').val
		"name": $('#name').val(),
		"description":$('#description').val(),
		"category":$('#category').val(),
		"stock": $('#stock').val(),
		"price": $('#price').val()
	});
};

var addItem = function(){
	console.log('addItem');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSONA(),
		success: function(data, textStatus, jqXHR){
			alert('Item created successfully');
			$('#itemId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addItem error: '+textStatus);
		}
	});
};

var updateItem = function(){
	console.log('updateItem '+$('#itemId').val());
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL+'/'+$('#itemId').val(),
		dataType: "json",
		data: formToJSONU(),
		success: function(data, textStatus, jqXHR){
			alert('Item updated successfully');
			console.log(data);
		},
		error: function(jqHXR, textStatus, errorThrown){
			alert('updateItem error: '+textStatus);
		}
	});
};

var deleteItem = function(){
	console.log('deleteItem');
	$.ajax({
		type: 'DELETE',
		url: rootURL+'/'+$('#itemId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Item deleted successfully');
			newItem();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteItem error: '+textStatus);
		}
	});
};

function renderList(data){
	list=data;
	$.each(list, function(index, item){
		$('#itemTable').append('<tr><td>'+item.name+
				'</td><td>'+item.price+'</td><td>'+item.category+
				'</td><td>'+item.stock+'</td><td><a id="'+item.id+'" href="edit">Edit</td></tr>');
	});
	
	$('#table_id').DataTable();
	output='<div class="row">';
	$.each(list, function(index, item){
		var img="pics/"+item.pic;
		output+=('<div class="col-sm-6 col-md-4 col-lg-3">'+
				'<div class="card"><img src='+'"'+img+'"'+
				'height="150"><p>Name: '+item.name+'</p>'+
				'<p>Details: '+item.description+'</p>'+
				'<p>Stock: '+item.stock+'</p>'+
				'<p>Price: $'+item.price+'</p>'+
				'<a id="'+item.id+'" href="addToBacket" class="addToBacket"> Add to Basket</a>'+
				'</div></div>');
	});
	output+='</div>';
	$('#productList').append(output);
};