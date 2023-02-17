package com.example.demo.cart;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LineItem {
	
	private String itemId;
	private int quantity;
	
	public LineItem(String itemId, int quantity) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
	}
//	public Item getItem() {
//		return item;
//	}
//	public int getQuantity() {
//		return quantity;
//	}
	
	public LineItem ( String itemId) {
		this(itemId,1);
	}
	

}
