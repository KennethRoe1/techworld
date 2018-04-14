$(document).ready(function(){
	findAll3();
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

var findById3= function(id){
	console.log('findById '+id);
	$.ajax({
		type: 'GET',
		url: basketURL + '/'+id,
		dataType: "json",
		success: function(data){
			console.log('findById success: '+data.id);
			currentBasket = data;
			//renderDetails3(currentBasket);
		}
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
				'<td>'+basket.quantity+'</td>'+
				'<td><a id="'+basket.id+'" href="remove">Remove</td>'+
				'</tr>');
	});
}