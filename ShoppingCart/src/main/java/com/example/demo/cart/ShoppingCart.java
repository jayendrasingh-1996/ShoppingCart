package com.example.demo.cart;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public void remove(LineItem lineItemToRemove) {
		boolean deleteLineItem = false;
		for(LineItem itemInCart : itemsInCart) {
			if(Objects.equals(itemInCart.getItemId(), lineItemToRemove.getItemId()))
			{
				if(lineItemToRemove.getQuantity() == itemInCart.getQuantity()) {
					deleteLineItem =true;
				}else {
					itemInCart.reduceQuantityBy(lineItemToRemove.getQuantity());
				}
			}
		}
		
		if(deleteLineItem) {
		this.itemsInCart.remove(lineItemToRemove);
		}
	}

}
