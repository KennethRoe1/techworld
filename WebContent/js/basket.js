$(document).ready(function(){
	//findAll3();
	findByUserId(userVar);
});
var basketURL = "http://localhost:8080/TechWorld/rest/basket";
var currenBasket;

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
			/*
			function(data){
			console.log('findByUserId success: '+data.id);
			currentBasket = data;
			console.log(data);
			success: renderList3
		}
		*/
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
				'<td>'+basket.itemQuantity+'</td>'+
				'<td><a id="'+basket.id+'" href="remove">Remove</td>'+
				'</tr>');
	});
}