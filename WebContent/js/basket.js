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

$(document).on("click", "#basketTable a",function(){
	event.preventDefault();
	deleteInstance(this.id);
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
		$('#basketTable').append('<tr class="theRows">'+
				'<td>'+basket.id+'</td>'+
				'<td>'+basket.userId+'</td>'+
				'<td>'+basket.itemId+'</td>'+
				'<td><input type="text" id="quantity" value="'+basket.itemQuantity+'"><button>Update</button></td>'+
				'<td><a id="'+basket.id+'" href="remove">X</td>'+
				'</tr>');
	});
}

var addToBasket = function(id){
	var itemId = id;
	console.log('addToBasket');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: basketURL,
		dataType: "json",
		data:formToJSONI(itemId),
		success: function(data, textStatus, jqXHR){
			alert('Item added successfully');
			console.log(data);
			$('.theRows').remove();
			findByUserId(userVar);
			
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addBasket error: '+textStatus);
		}
	});
};

var formToJSONI=function(itemId){
	return JSON.stringify({
		"userId": userVar,
		"itemId": itemId,
		"itemQuantity":1
	});
};

var updateQuantity = function(id){
	console.log('updateing quantity of instance '+id);
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

var deleteInstance = function(id){
	console.log('deleteItem');
	$.ajax({
		type: 'DELETE',
		url: basketURL+'/'+id,
		success: function(data, textStatus, jqXHR){
			alert('Item deleted successfully');
			newItem();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteItem error: '+textStatus);
		}
	});
};