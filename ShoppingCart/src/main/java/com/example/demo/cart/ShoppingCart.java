package com.example.demo.cart;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.Converters.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Builder
@Getter
@Setter
public class ShoppingCart {

	private Map<String,LineItem> itemsInCart = new LinkedHashMap();

	private Inventory inventory;
	
	public void addItem(LineItem lineItem) {
	
		if(itemsInCart.containsKey(lineItem.getItemId())) {
			LineItem lineItemInCart = itemsInCart.get(lineItem.getItemId());
			lineItemInCart.increaseQuantityBy(lineItem.getQuantity());
			lineItem.setQuantity(lineItemInCart.getQuantity());
		}
		//this.itemsInCart.add(lineItem);
		Item item = inventory.get(lineItem.getItemId());
		lineItem.setName(item.getName());
		lineItem.setPrice(item.getPrice());
		
		this.itemsInCart.put(lineItem.getItemId(),lineItem);
		
		
	}

	public int totalNumberOfItems() {
		
		int totalItem = 0;
		
		for(LineItem lineItem : itemsInCart.values()) {
			totalItem += lineItem.getQuantity(); 
		}
		return totalItem;
	}

	public ShoppingCart(Inventory inventory) {

		 this.inventory = inventory; 
	}

	public void remove(LineItem lineItemToRemove) {
		boolean deleteLineItem = false;
		for(LineItem itemInCart : itemsInCart.values()) {
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
		this.itemsInCart.remove((lineItemToRemove).getItemId());
		
		}
		//return deleteLineItem;
	}

	public List<LineItem> listItemsInCart() {
		return Collections.unmodifiableList(new ArrayList<>( this.itemsInCart.values() ) );
	}

	
}
