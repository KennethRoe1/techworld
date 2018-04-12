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

@Path("/users")
public class UserResource {
	
	UserDAO dao = new UserDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> findAll() {
		System.out.println("findAll");
		return dao.findAll();
	}
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User findById(@PathParam("id") String id) {
		System.out.println("findById "+id);
		return dao.findById(Integer.parseInt(id));
	}
	@GET @Path("/query")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> findByEmail(
			@QueryParam("email")String email){
		System.out.println("findByEmail: "+email);
		return dao.findByEmail(email);
	}
	
	//create
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User create(User user) {
		System.out.println("new user");
		return dao.create(user);
	}
	//update
	@PUT @Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User update(User user) {
		System.out.println("Updating User: "+user.getId());
		dao.update(user);
		return user;
	}
	//delete
	@DELETE @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public boolean remove(@PathParam("id") int id) {
		System.out.println("Removing User "+id);
		return dao.remove(id);
	}
	
}
