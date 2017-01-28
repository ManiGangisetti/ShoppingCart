package com.tek.interview.question.main;

public class OrderLine implements Cloneable {

	private int quantity;
	private Item item;

	/*
	 * @param item Item of the order
	 * 
	 * @param quantity Quantity of the item
	 */
	public OrderLine(Item item, int quantity) throws InvalidQuantityException {

		if (item == null) {
			throw new InvalidQuantityException("Item is NULL");
		} else {
			this.item = item;
		}
		if (quantity > 0) {
			this.quantity = quantity;
		} else {
			throw new InvalidQuantityException("Invalid quantity");
		}

	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public Object clone() throws CloneNotSupportedException {
		OrderLine o;
		try {
			o = new OrderLine(item, quantity);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}