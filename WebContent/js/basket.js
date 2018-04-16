$(document).ready(function(){
	//findAll3();
	findByUserId(userVar);
});
var basketURL = "http://localhost:8080/TechWorld/rest/basket";
var currenBasket;

$(document).on("click", "#cardTable a",function(){
	event.preventDefault();
	addToBasket(this.id);
});

var findAll3 = function() {
	console.log('findAll3');
	$.ajax({
		type: 'GET',
		url: basketURL,
		dayaType:"json",
		success: renderList3
	});
};

var findByUserId= function(id){
	console.log('findByUserId '+id);
	$.ajax({
		type: 'GET',
		url: basketURL + '/query/?userId='+id,
		dataType: "json",
		success: renderList3
	});
};

function renderList3(data){
	list=data;
	$.each(list, function(index, basket){
		console.log(data);
		$('#basketTable').append('<tr>'+
				'<td>'+basket.id+'</td>'+
				'<td>'+basket.userId+'</td>'+
				'<td>'+basket.itemId+'</td>'+
				'<td><input type="text" id="quantity" value="'+basket.itemQuantity+'"><button>Update</button></td>'+
				'<td><a id="'+basket.id+'" href="remove">Remove</td>'+
				'</tr>');
	});
	$('#table_id3').DataTable();
}

var addToBasket = function(){
	console.log('addToBasket');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: basketURL,
		dataType: "json",
		data: formToJSONA(),
		success: function(data, textStatus, jqXHR){
			alert('Item created successfully');
			$('#basketId').val(data.id);
			console.log(data);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addItem error: '+textStatus);
		}
	});
};

var updateQuantity = function(){
	console.log('updateItem '+$('#itemId').val());
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: basketURL+'/'+$('#Id').val(),
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

var deleteInstance = function(){
	console.log('deleteItem');
	$.ajax({
		type: 'DELETE',
		url: basketURL+'/'+$('#Id').val(),
		success: function(data, textStatus, jqXHR){
			alert('Item deleted successfully');
			newItem();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteItem error: '+textStatus);
		}
	});
};