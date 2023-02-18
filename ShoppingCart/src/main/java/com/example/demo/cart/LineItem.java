package com.example.demo.cart;

import java.math.BigDecimal;
import java.util.function.IntPredicate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LineItem {

	private String itemId;
	private int quantity;
	private BigDecimal price;
	private String name;

	public LineItem(String itemId, int quantity,BigDecimal price,String name) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.price=price;
		this.name=name;
	}
	 
//	public Item getItem() {
//		return item;
//	}
//	public int getQuantity() {
//		return quantity;
//	}

	public LineItem(String itemId,int quantity) {
		this.itemId=itemId;
		this.quantity=quantity;
				
	}

	public LineItem(String itemId) {
		this(itemId,1);
		
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

	public BigDecimal totalPrice() {
		// TODO Auto-generated method stub
		return price.multiply(BigDecimal.valueOf(quantity));
		
	}

	public void increaseQuantityBy(int quantityToAdd) {
		this.quantity +=quantityToAdd;
	}
}
