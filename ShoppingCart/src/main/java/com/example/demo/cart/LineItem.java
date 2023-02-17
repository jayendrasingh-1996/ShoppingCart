package com.example.demo.cart;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LineItem {
	
	private Item item;
	private int quantity;
	public LineItem(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}
//	public Item getItem() {
//		return item;
//	}
//	public int getQuantity() {
//		return quantity;
//	}
	
	public LineItem ( Item item) {
		this(item,1);
	}
	

}
