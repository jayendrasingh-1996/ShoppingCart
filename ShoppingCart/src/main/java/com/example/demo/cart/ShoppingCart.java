package com.example.demo.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

//@Builder
@Getter
@Setter
public class ShoppingCart {

	private Map<String, LineItem> itemsInCart = new LinkedHashMap();

	private Inventory inventory;

	private double salesTax;

	private double totalPriceOfItems;

	private double totalPriceWithTax;

	private static DecimalFormat df = new DecimalFormat("0.00");

	public void addItem(LineItem lineItem) {

		if (itemsInCart.containsKey(lineItem.getItemId())) {
			LineItem lineItemInCart = itemsInCart.get(lineItem.getItemId());
			lineItemInCart.increaseQuantityBy(lineItem.getQuantity());
			lineItem.setQuantity(lineItemInCart.getQuantity());
		}
		// this.itemsInCart.add(lineItem);
		Item item = inventory.get(lineItem.getItemId());
		lineItem.setName(item.getName());
		lineItem.setPrice(item.getPrice());

		// sales tax
		this.salesTax += calculateCurrentItemTax(lineItem);

		// total price with tax
		this.totalPriceWithTax += calculateCurrentItemSalesTaxPrice(lineItem);

		// total price of items
		this.totalPriceOfItems += calculateCurrentItemSalesPrice(lineItem);

		this.itemsInCart.put(lineItem.getItemId(), lineItem);

	}

	private Double calculateCurrentItemSalesPrice(LineItem lineItem) {

		if (lineItem.getQuantity() > 0) {

			BigDecimal bd = BigDecimal.valueOf((lineItem.getPrice().doubleValue()) * lineItem.getQuantity());
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			return bd.doubleValue();

		}

		return 0.0;
	}

	private Double calculateCurrentItemSalesTaxPrice(LineItem lineItem) {

		if (lineItem.getQuantity() > 0) {

//			double result = lineItem.getPrice().doubleValue()) * lineItem.getQuantity() + calculateCurrentItemTax(lineItem);
			BigDecimal bd = BigDecimal.valueOf(
					(lineItem.getPrice().doubleValue()) * lineItem.getQuantity() + calculateCurrentItemTax(lineItem));

			bd = bd.setScale(2, RoundingMode.HALF_UP);
			return  bd.doubleValue();

		}
		return  0.0;
	}

	private Double calculateCurrentItemTax(LineItem lineItem) {

		if (lineItem.getQuantity() > 0) {

			BigDecimal bd;
			// BigDecimal bd = BigDecimal.valueOf(lineItem.getPrice().doubleValue() *
			// lineItem.getQuantity() * 0.12);

			bd = lineItem.getPrice().multiply(new BigDecimal(lineItem.getQuantity()));

			bd = bd.multiply(new BigDecimal(0.12));

			bd = bd.setScale(2, RoundingMode.HALF_UP);
			return bd.doubleValue();

		}

		return 0.0;
	}

	public int totalNumberOfItems() {

		int totalItem = 0;

		for (LineItem lineItem : itemsInCart.values()) {
			totalItem += lineItem.getQuantity();
		}
		return totalItem;
	}

	public ShoppingCart(Inventory inventory) {

		this.inventory = inventory;
	}

	public void remove(LineItem lineItemToRemove) {
		boolean deleteLineItem = false;

//		int  quantityIfAllTheProductsAreRemoved; 
//		BigDecimal priceIfAllTheProductsAreRemoved;
//		
		for (LineItem itemInCart : itemsInCart.values()) {

			if (Objects.equals(itemInCart.getItemId(), lineItemToRemove.getItemId())) {

				// priceIfAllTheProductsAreRemoved.valueOf(quantityIfAllTheProductsAreRemoved);

//				quantityIfAllTheProductsAreRemoved=itemInCart.getQuantity();
//				
				lineItemToRemove.setPrice(itemInCart.getPrice());

				if (lineItemToRemove.getQuantity() == itemInCart.getQuantity()) {
					deleteLineItem = true;

				} else {
					itemInCart.reduceQuantityBy(lineItemToRemove.getQuantity());
					lineItemToRemove.setPrice(itemInCart.getPrice());
				}
			}
		}

		if (deleteLineItem) {

			this.itemsInCart.remove((lineItemToRemove).getItemId());

		}

		// sales tax
		this.salesTax -= calculateCurrentItemTax(lineItemToRemove);

		// total price

//		Float tax= calculateCurrentItemSalesTaxPrice(lineItemToRemove);
//		BigDecimal bd ;
//		bd=new BigDecimal(this.totalPriceWithTax);
//		 bd=bd.setScale(2, RoundingMode.HALF_UP);
//		bd=bd.subtract(new BigDecimal(tax));
//	
		this.totalPriceWithTax -= calculateCurrentItemSalesTaxPrice(lineItemToRemove);

		if (this.totalPriceWithTax <= 0) {
			this.totalPriceWithTax = 0;
		}
//		this.totalPriceWithTax -= (double)tax;

		this.totalPriceOfItems -= calculateCurrentItemSalesPrice(lineItemToRemove);
		if (this.totalPriceOfItems <= 0) {
			this.totalPriceOfItems = 0;
		}

		// return deleteLineItem;
	}

	public List<LineItem> listItemsInCart() {
		return Collections.unmodifiableList(new ArrayList<>(this.itemsInCart.values()));
	}

}
