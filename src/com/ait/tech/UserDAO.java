package com.ait.tech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	//find all
		public List<User> findAll() {
	        List<User> list = new ArrayList<User>();
	        Connection c = null;
	    	String sql = "SELECT * FROM users ORDER BY user_id";
	        try {
	            c = ConnectionHelper.getConnection();
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next()) {
	                list.add(processRow(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return list;
	    }
		
	    //find by id
	    public User findById(int id) {
	    	String sql = "select * from users where user_id = ?";
	    	User user = null;
	    	Connection c =null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		PreparedStatement ps = c.prepareStatement(sql);
	    		ps.setInt(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		if(rs.next()) {
	    			user=processRow(rs);
	    		}
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		throw new RuntimeException(e);
	    	}
	    	finally {
	    		ConnectionHelper.close(c);
	    	}
	    	return user;
	    }
	    //find my email
	    public List<User> findByEmail(String email){
	    	List<User> list = new ArrayList<User>();
	    	Connection c = null;
	    	String sql = "select * from users as e where upper(user_email) like ? order by user_id";
	    	try {
	    		c=ConnectionHelper.getConnection();
	    		PreparedStatement ps = c.prepareStatement(sql);
	    		ps.setString(1, "%"+email.toUpperCase()+"%");
	    		ResultSet rs = ps.executeQuery();
	    		while(rs.next()) {
	    			list.add(processRow(rs));
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		throw new RuntimeException(e);
	    	}finally {
	    		ConnectionHelper.close(c);
	    	}
	    	return list;
	    }
	    
		//create
	  	public User create(User user) {
	  		Connection c= null;
	  		PreparedStatement ps = null;
	  		try {
	  			c= ConnectionHelper.getConnection();
	  			ps = c.prepareStatement("insert into users(user_id,user_email,user_name,user_address,user_dob,role) values(?,?,?,?,?,?,?)",
	  					new String[] {"ID"});
	  			ps.setInt(1, user.getId());
	  			ps.setString(2, user.getEmail());
	  			ps.setString(3, user.getName());
	  			ps.setString(4, user.getPass());
	  			ps.setString(5, user.getAddress());
	  			ps.setDate(6, user.getDob());
	  			ps.setString(7, user.getRole());
	  			ps.executeUpdate();
	  			ResultSet rs = ps.getGeneratedKeys();
	  			rs.next();
	  			int id = rs.getInt(1);
	  			user.setId(id);
	  		}catch(Exception e) {
	  			e.printStackTrace();
	  		}finally {
	  			ConnectionHelper.close(c);
	  		}
	  		return user;
	  	}
	  	//update
	  	public User update(User user) {
	  		Connection c = null;
	  		try {
	  			c=ConnectionHelper.getConnection();
	  			PreparedStatement ps = c.prepareStatement("update users set user_email=?,user_name=?, where user_id=?");
	  			ps.setString(1, user.getEmail());
	  			ps.setString(2, user.getName());
	  			ps.setString(3, user.getPass());
	  			ps.setString(4, user.getAddress());
	  			ps.setDate(5, user.getDob());
	  			ps.setString(6, user.getRole());
	  			ps.setInt(7, user.getId());
	  			ps.executeUpdate();
	  		}catch(SQLException e){
	  			e.printStackTrace();
	  			throw new RuntimeException(e);
	  		}finally {
	  			ConnectionHelper.close(c);
	  		}
	  		return user;
	  	}
	  	//delete
	  	public boolean remove(int id) {
	  		Connection c = null;
	  		try {
	  			c = ConnectionHelper.getConnection();
	  			PreparedStatement ps = c.prepareStatement("delete from users where user_id=?");
	  			ps.setInt(1,id);
	  			int count = ps.executeUpdate();
	  			return count==1;
	  		}catch(Exception e) {
	  			e.printStackTrace();
	  			throw new RuntimeException();
	  		}finally {
	  			ConnectionHelper.close(c);
	  		}
	  	}
		
		// process row
	    protected User processRow(ResultSet rs) throws SQLException {
	        User user = new User();
	        user.setId(rs.getInt("user_id"));
	        user.setEmail(rs.getString("user_email"));
	        user.setName(rs.getString("user_name"));
	        user.setPass(rs.getString("user_pass"));
	        user.setAddress(rs.getString("user_address"));
	        user.setDob(rs.getDate("user_dob"));
	        user.setRole(rs.getString("role"));
	        return user;
	    }
	
}
