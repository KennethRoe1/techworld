$(document).ready(function(){
	findByUserId(userVar);
});
var basketURL = "http://localhost:8080/TechWorld/rest/basket";
var currenBasket;
var anItem;

//buttons
$(document).on("click", "#cardTable a",function(){
	event.preventDefault();
	addToBasket(this.id);
});

$(document).on("click", "#basketTable a",function(){
	event.preventDefault();
	deleteInstance(this.id);
});

$(document).on("click", ".updateQBtn",function(){
	var theN=this.id;
	var theQ=$('#quantity'+theN).val();
	console.log('update pressed, N='+theN+', Q='+theQ);
	updateQuantity(theN,theQ);
	
});

// Queries and displays
var findByUserId= function(id){
	//console.log('findByUserId '+id);
	$.ajax({
		type: 'GET',
		url: basketURL + '/query/?userId='+id,
		dataType: "json",
		success: renderListB1
	});
};

var findItemById= function(id){
	$.ajax({
		type: 'GET',
		url: rootURL + '/'+id,
		dataType: "json",
		success: function(data){
			anItem = data;
			renderParts(anItem);
		}
	});
};

function renderListB1(data){
	list=data;
	$.each(list, function(index, basket){
		//console.log(data);
		$('#basketTable').append('<tr class="theRows">'+
				'<td id="name'+basket.itemId+'"></td>'+
				'<td id="image'+basket.itemId+'"></td>'+
				'<td id="price'+basket.itemId+'"></td>'+
				'<td><input type="text" id="quantity'+basket.id+'" style="width: 30px" value="'+basket.itemQuantity+'"><button class="updateQBtn" id="'+basket.id+'">Update</button></td>'+
				'<td><a id="'+basket.id+'" href="remove">X</td>'+
				'</tr>');
		findItemById(basket.itemId);
	});
}

var renderParts=function(item){
	$('#name'+item.id).html(item.name);
	$('#price'+item.id).html('$'+item.price);
	$('#image'+item.id).html('<img src="pics/'+item.pic+'" alt="thumb" height="50"/>');
};

// crud
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

var updateQuantity = function(id, quantity){
	console.log('updateing quantity of instance '+id);
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: basketURL+'/'+id,
		dataType: "json",
		data: JSON.stringify({"id": id,"itemQuantity": quantity}),
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