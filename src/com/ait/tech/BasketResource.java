package com.ait.tech;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/basket")
public class BasketResource {
	
BasketDAO dao = new BasketDAO();
	//find all
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Basket> findAll() {
		System.out.println("findAll");
		return dao.findAll();
	}
	//find by id
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Basket findById(@PathParam("id") String id) {
		System.out.println("findById "+id);
		return dao.findById(Integer.parseInt(id));
	}
	//find by user_id
	@GET @Path("/query")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Basket> findByUserId(
			@QueryParam("userId")String userId){
		System.out.println("findByUserId: "+userId);
		return dao.findByUserId(Integer.parseInt(userId));
	}
	
	//create
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Basket create(Basket basket) {
		System.out.println("new basket");
		return dao.create(basket);
	}
	//update quantity
	@PUT @Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Basket update(Basket basket) {
		System.out.println("Updating basket: "+basket.getId());
		dao.updateQuantity(basket);
		return basket;
	}
	//delete
	@DELETE @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean remove(@PathParam("id") int id) {
		System.out.println("Removing Item "+id);
		return dao.remove(id);
	}
}
