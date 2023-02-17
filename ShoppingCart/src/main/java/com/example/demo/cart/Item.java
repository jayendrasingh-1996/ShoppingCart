package com.example.demo.cart;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class Item {
	
	private final String id;
	private final String name;
	private final ItemType itemType;
	private final BigDecimal price;

	
	
	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public ItemType getItemType() {
		return itemType;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public Item(String id, String name,ItemType itemType, BigDecimal price) {
		this.id=id;
		this.name=name;
		this.itemType=itemType;
		this.price=price;
	}


	
}
