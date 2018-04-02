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
	public List<User> findByEmailAndPass(
			@QueryParam("email")String email,
			@QueryParam("pass")String pass){
		System.out.println("findByEmailAndPass: "+email+" "+pass);
		return dao.findByEmailAndPass(email,pass);
	}
	
}
