package com.example.demo.cart;



import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Builder
@Getter
@Setter
public class ShoppingCart {

	private List<LineItem> itemsInCart = new ArrayList<>();

	private Inventory inventory;
	
	public void addItem(LineItem lineItem) {
	
		this.itemsInCart.add(lineItem);	
	}

	public int totalNumberOfItems() {
		
		int totalItem = 0;
		
		for(LineItem lineItem : itemsInCart) {
			totalItem += lineItem.getQuantity(); 
		}
		return totalItem;
	}

	public ShoppingCart(Inventory inventory) {

		 this.inventory = inventory; 
	}

}
