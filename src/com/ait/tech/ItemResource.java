package com.ait.tech;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	
}
