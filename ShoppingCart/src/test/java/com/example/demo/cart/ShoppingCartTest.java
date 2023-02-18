package com.example.demo.cart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
class ShoppingCartTest {

	@Mock
	private Inventory inventory;

	@InjectMocks
	private ShoppingCart cart;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void setUp() throws Exception {

		Item item1 = Item.builder().id("item-1").name("Dove_Soap").itemType(ItemType.SOAP)
				.price(BigDecimal.valueOf(39.55)).build();

		Item item2 = Item.builder().id("item-2").name("Axe_Deo").itemType(ItemType.DEO).price(BigDecimal.valueOf(99.99))
				.build();

		inventory = new Inventory();
		inventory.add(item1);
		inventory.add(item2);

		cart = new ShoppingCart(inventory);

	}

	@Test
	void should_add_an_item_to_a_cart() {

//		cart.addItem(new LineItem(Item.builder().id("item-1").name("Dove_Soap").itemType(ItemType.SOAP)
//				.price(BigDecimal.valueOf(39.55)).build()));
//

		cart.addItem(new LineItem("item-1"));

		int totalItemCount = cart.totalNumberOfItems();

		assertThat(totalItemCount).isEqualTo(1);

	}

	@Test
	public void should_add_multiple_items_to_the_cart() {

//		ShoppingCart cart = new ShoppingCart();

//		Item item1 = Item.builder().id("item-1").name("Dove_Soap").itemType(ItemType.SOAP)
//				.price(BigDecimal.valueOf(39.55)).build();
//		Item item2 = Item.builder().id("item-2").name("Axe_Deo").itemType(ItemType.DEO).price(BigDecimal.valueOf(99.99))
//				.build();
//
//		cart.addItem(item1);
//
//		cart.addItem(item2);

//		cart.addItem(new LineItem(Item.builder().id("item-1").name("Dove_Soap").itemType(ItemType.SOAP)
//				.price(BigDecimal.valueOf(39.55)).build()));
//
//		cart.addItem(new LineItem(Item.builder().id("item-2").name("Axe_Deo").itemType(ItemType.DEO)
//				.price(BigDecimal.valueOf(99.99)).build()));

		cart.addItem(new LineItem("item-1"));
		cart.addItem(new LineItem("item-2"));

		int totalItemCount = cart.totalNumberOfItems();

		assertThat(totalItemCount).isEqualTo(2);

	}

	@Test
	public void should_add_multiple_quantities_of_the_same_items_to_the_cart() {

//		ShoppingCart cart = new ShoppingCart();

//		Item item1 = Item.builder().id("item-1").name("Dove_Soap").itemType(ItemType.SOAP)
//				.price(BigDecimal.valueOf(39.55)).build();
//
//		cart.addItem(item1);

//		cart.addItem(new LineItem(Item.builder().id("item-1").name("Dove_Soap").itemType(ItemType.SOAP)
//				.price(BigDecimal.valueOf(39.55)).build(), 2));

		cart.addItem(new LineItem("item-1", 2));

		int totalItemCount = cart.totalNumberOfItems();

		assertThat(totalItemCount).isEqualTo(2);

	}

	
	@Test
	public void should_remove_an_item_from_cart() {
		cart.addItem(new LineItem("item-1"));
		cart.addItem(new LineItem("item-2"));

		cart.remove(new LineItem("item-2"));
		
		int totalItemCount = cart.totalNumberOfItems();
		assertThat(totalItemCount).isEqualTo(1);
		
		
		
	}
	
	@Test
	public void should_remove_specific_quantity_of_an_item_from_cart() {

		cart.addItem(new LineItem("item-1",4));
		cart.addItem(new LineItem("item-2",5));

		cart.remove(new LineItem("item-2",2));
		cart.remove(new LineItem("item-2",3));

		int totalItemCount = cart.totalNumberOfItems();
		assertThat(totalItemCount).isEqualTo(4);
		
		
		
	}
	
		
	
}
