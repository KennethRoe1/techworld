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

@Path("/items")
public class ItemResource {
	
	ItemDAO dao = new ItemDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Item> findAll() {
		System.out.println("findAll");
		return dao.findAll();
	}
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Item findById(@PathParam("id") String id) {
		System.out.println("findById "+id);
		return dao.findById(Integer.parseInt(id));
	}
	//create
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Item create(Item item) {
		System.out.println("new item");
		return dao.create(item);
	}
	//update
	@PUT @Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Item update(Item item) {
		System.out.println("Updating item: "+item.getName());
		dao.update(item);
		return item;
	}
	//delete
	@DELETE @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean remove(@PathParam("id") int id) {
		System.out.println("Removing Item "+id);
		return dao.remove(id);
	}
	
}
