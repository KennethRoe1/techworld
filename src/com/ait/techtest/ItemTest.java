package com.ait.techtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ait.tech.Item;

public class ItemTest {


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testItemConstructor() {
		Item myItem = new Item(7, "iPhone X", "New Phone", "Phone", 400, 50, "IPhoneX.jpg");
		assertEquals("iPhone X", myItem.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemNameNull() {
		Item myItem = new Item(7, null, "New Phone", "Phone", 400, 50, "IPhoneX.jpg");
		assertEquals("iPhone X", myItem.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemDescriptionNull() {
		Item myItem = new Item(7, "iPhoneX", null, "Phone", 400, 50, "IPhoneX.jpg");
		assertEquals("New Phone", myItem.getDescription());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemCategoryNull() {
		Item myItem = new Item(7, "iPhoneX", "New Phone", null, 400, 50, "IPhoneX.jpg");
		assertEquals("Phone", myItem.getCategory());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemPicNull() {
		Item myItem = new Item(7, "iPhoneX", "New Phone", "Phone", 400, 50, null);
		assertEquals("IPhoneX.jpg", myItem.getCategory());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemNoName() {
		Item myItem = new Item(7, "", "New Phone", "Phone", 400, 50, "IPhoneX.jpg");
		assertEquals("iPhone X", myItem.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemNodescription() {
		Item myItem = new Item(7, "iPhone X", "", "Phone", 400, 50, "IPhoneX.jpg");
		assertEquals("New Phone", myItem.getDescription());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemNoCategory() {
		Item myItem = new Item(7, "iPhone X", "New Phone", "", 400, 50, "IPhoneX.jpg");
		assertEquals("Phone", myItem.getCategory());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemNoPic() {
		Item myItem = new Item(7, "iPhone X", "New Phone", "Phone", 400, 50, "");
		assertEquals("IPhoneX.jpg", myItem.getPic());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemID() {
		Item myItem = new Item(-7, "iPhone X", "New Phone", "Phone", 400, 50, "IPhoneX.jpg");
		assertEquals(7, myItem.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemStock() {
		Item myItem = new Item(7, "iPhone X", "New Phone", "Phone", 400, -50, "IPhoneX.jpg");
		assertEquals(50, myItem.getStock());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testItemPrice() {
		Item myItem = new Item(7, "iPhone X", "New Phone", "Phone", -400, 50, "IPhoneX.jpg");
		assertEquals(400, myItem.getPrice(), 0.001);
	}
}
