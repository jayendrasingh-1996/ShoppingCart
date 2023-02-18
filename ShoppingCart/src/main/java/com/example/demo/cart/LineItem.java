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

	public LineItem(String itemId) {
		this(itemId, 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass())
			return false;

		LineItem lineItem = (LineItem) o;

		if (quantity != lineItem.quantity)
			return false;

		return itemId != null ? itemId.equals(lineItem.itemId) : lineItem.itemId == null;
	}

	public int hashCode() {

		int result = itemId != null ? itemId.hashCode() : 0;
		result = 31 * result + quantity;
		return result;
	}

	public void reduceQuantityBy(int quantityToReduceBy) {
	
		this.quantity -=quantityToReduceBy;
		
	}
}
