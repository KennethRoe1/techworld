package com.ait.tech;

import java.sql.Connection;
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
