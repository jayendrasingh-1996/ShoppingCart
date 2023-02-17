package com.example.demo.cart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

	private Inventory inventory;

	ShoppingCart cart;

	@BeforeAll
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

}
