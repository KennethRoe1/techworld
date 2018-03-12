package com.ait.tech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	
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
	
	// process row
    protected Item processRow(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));
        item.setDescription(rs.getString("item_description"));
        item.setCatagory(rs.getString("category"));
        item.setPrice(rs.getDouble("item_price"));
        item.setStock(rs.getInt("stock_count"));
        return item;
    }

}
