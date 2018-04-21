package com.ait.tech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasketDAO {
	
	//find all
		public List<Basket> findAll() {
	        List<Basket> list = new ArrayList<Basket>();
	        Connection c = null;
	    	String sql = "SELECT * FROM basket ORDER BY instant_id";
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
	    public Basket findById(int id) {
	    	String sql = "select * from basket where instant_id = ?";
	    	Basket basket = null;
	    	Connection c =null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		PreparedStatement ps = c.prepareStatement(sql);
	    		ps.setInt(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		if(rs.next()) {
	    			basket=processRow(rs);
	    		}
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		throw new RuntimeException(e);
	    	}
	    	finally {
	    		ConnectionHelper.close(c);
	    	}
	    	return basket;
	    }
	    
	  //find by userId
	    public List<Basket> findByUserId(int userId){
	    	List<Basket> list = new ArrayList<Basket>();
	    	Connection c = null;
	    	String sql = "select * from basket where user_id = ? order by instant_id";
	    	try {
	    		c=ConnectionHelper.getConnection();
	    		PreparedStatement ps = c.prepareStatement(sql);
	    		ps.setInt(1, userId);
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
	  	public Basket create(Basket basket) {
	  		Connection c= null;
	  		PreparedStatement ps = null;
	  		try {
	  			c= ConnectionHelper.getConnection();
	  			ps = c.prepareStatement("insert into basket(user_id,item_id,item_quantity) values(?,?,?)",
	  					new String[] {"ID"});
	  			ps.setInt(1, basket.getUserId());
	  			ps.setInt(2,basket.getItemId());
	  			ps.setInt(3, basket.getItemQuantity());
	  			ps.executeUpdate();
	  			ResultSet rs = ps.getGeneratedKeys();
	  			rs.next();
	  			int id = rs.getInt(1);
	  			basket.setId(id);
	  		}catch(Exception e) {
	  			e.printStackTrace();
	  		}finally {
	  			ConnectionHelper.close(c);
	  		}
	  		return basket;
	  	}
	  	//update
	  	public Basket update(Basket basket) {
	  		Connection c = null;
	  		try {
	  			c=ConnectionHelper.getConnection();
	  			PreparedStatement ps = c.prepareStatement("update basket set user_id=?, item_id=?, item_quantity=? where instant_id=?");
	  			ps.setInt(1, basket.getUserId());
	  			ps.setInt(2, basket.getItemId());
	  			ps.setInt(3,  basket.getItemQuantity());
	  			ps.setInt(4, basket.getId());
	  			ps.executeUpdate();
	  		}catch(SQLException e){
	  			e.printStackTrace();
	  			throw new RuntimeException(e);
	  		}finally {
	  			ConnectionHelper.close(c);
	  		}
	  		return basket;
	  	}
	  	
	  //update Quantity
	  	public Basket updateQuantity(Basket basket) {
	  		Connection c = null;
	  		try {
	  			c=ConnectionHelper.getConnection();
	  			PreparedStatement ps = c.prepareStatement("update basket set item_quantity=? where instant_id=?");
	  			ps.setInt(1,  basket.getItemQuantity());
	  			ps.setInt(2, basket.getId());
	  			ps.executeUpdate();
	  		}catch(SQLException e){
	  			e.printStackTrace();
	  			throw new RuntimeException(e);
	  		}finally {
	  			ConnectionHelper.close(c);
	  		}
	  		return basket;
	  	}
	  	//delete
	  	public boolean remove(int id) {
	  		Connection c = null;
	  		try {
	  			c = ConnectionHelper.getConnection();
	  			PreparedStatement ps = c.prepareStatement("delete from basket where instant_id=?");
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
	    protected Basket processRow(ResultSet rs) throws SQLException {
	    	Basket basket = new Basket();
	    	basket.setId(rs.getInt("instant_id"));
	    	basket.setUserId(rs.getInt("user_id"));
	    	basket.setItemId(rs.getInt("item_id"));
	    	basket.setItemQuantity(rs.getInt("item_quantity"));
	        return basket;
	    }
	  	
}
