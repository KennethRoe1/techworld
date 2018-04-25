package com.ait.techtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.ait.tech.User;
import java.sql.Date;




public class UserTest {
	String str = "2001-01-01";
	Date date = Date.valueOf(str);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserConstructor() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", "pass", "Roscommon", date ,"Admin");
		assertEquals("Mario Costello", myUser.getName());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserNameNull() {
		User myUser = new User (7, "Mario@Gmail.com", null, "pass", "Roscommon", date ,"Admin");
		assertEquals("Mario Costello", myUser.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserEmailNull() {
		User myUser = new User (7, null, "Mario Costello", "pass", "Roscommon", date ,"Admin");
		assertEquals("Mario@Gmail.com", myUser.getEmail());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserPassNull() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", null, "Roscommon", date ,"Admin");
		assertEquals("pass", myUser.getPass());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserAddressNull() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", "pass", null, date ,"Admin");
		assertEquals("Roscommon", myUser.getAddress());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserroleNull() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", "pass", "Roscommon", date ,null);
		assertEquals("Admin", myUser.getRole());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserNoName() {
		User myUser = new User (7, "Mario@Gmail.com", "", "pass", "Roscommon", date ,"Admin");
		assertEquals("Mario Costello", myUser.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserNoEmail() {
		User myUser = new User (7, "", "Mario Costello", "pass", "Roscommon", date ,"Admin");
		assertEquals("Mario@Gmail.com", myUser.getEmail());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserNoPass() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", "", "Roscommon", date ,"Admin");
		assertEquals("pass", myUser.getPass());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserNoAddress() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", "pass", "", date ,"Admin");
		assertEquals("Roscommon", myUser.getAddress());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserNoRole() {
		User myUser = new User (7, "Mario@Gmail.com", "Mario Costello", "pass", "Roscommon", date ,"");
		assertEquals("Admin ", myUser.getRole());
	}

}
