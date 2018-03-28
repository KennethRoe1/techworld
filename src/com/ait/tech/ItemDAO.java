package com.ait.tech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ItemDAO {
	
	//find all
	public List<Item> findAll() {
        List<Item> list = new ArrayList<Item>();
        Connection c = null;
    	String sql = "SELECT * FROM items ORDER BY item_name";
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
    public Item findById(int id) {
    	String sql = "select * from items where item_id = ?";
    	Item item = null;
    	Connection c =null;
    	try {
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = c.prepareStatement(sql);
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			item=processRow(rs);
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}
    	finally {
    		ConnectionHelper.close(c);
    	}
    	return item;
    }
    
	//create
  	public Item create(Item item) {
  		Connection c= null;
  		PreparedStatement ps = null;
  		try {
  			c= ConnectionHelper.getConnection();
  			ps = c.prepareStatement("insert into items(item_id,item_name,item_description,category,item_price,stock_count,pic) values(?,?,?,?,?,?,?)",
  					new String[] {"ID"});
  			ps.setInt(1, item.getId());
  			ps.setString(2, item.getName());
  			ps.setString(3, item.getDescription());
  			ps.setString(4, item.getCategory());
  			ps.setDouble(5, item.getPrice());
  			ps.setInt(6, item.getStock());
  			ps.setString(7, item.getPic());
  			ps.executeUpdate();
  			ResultSet rs = ps.getGeneratedKeys();
  			rs.next();
  			int id = rs.getInt(1);
  			item.setId(id);
  		}catch(Exception e) {
  			e.printStackTrace();
  		}finally {
  			ConnectionHelper.close(c);
  		}
  		return item;
  	}
  	//update
  	public Item update(Item item) {
  		Connection c = null;
  		try {
  			c=ConnectionHelper.getConnection();
  			PreparedStatement ps = c.prepareStatement("update items set item_name=?,item_description=?,category=?,price=?,stock_count=?,pic=? where item_id=?");
  			ps.setString(1, item.getName());
  			ps.setString(2, item.getDescription());
  			ps.setString(3, item.getCategory());
  			ps.setInt(4, item.getStock());
  			ps.setDouble(5, item.getPrice());
  			ps.setString(6, item.getPic());
  			ps.setInt(7, item.getId());
  			ps.executeUpdate();
  		}catch(SQLException e){
  			e.printStackTrace();
  			throw new RuntimeException(e);
  		}finally {
  			ConnectionHelper.close(c);
  		}
  		return item;
  	}
  	//delete
  	public boolean remove(int id) {
  		Connection c = null;
  		try {
  			c = ConnectionHelper.getConnection();
  			PreparedStatement ps = c.prepareStatement("delete from items where item_id=?");
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
    protected Item processRow(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));
        item.setDescription(rs.getString("item_description"));
        item.setCategory(rs.getString("category"));
        item.setPrice(rs.getDouble("item_price"));
        item.setStock(rs.getInt("stock_count"));
        return item;
    }
  	
}
