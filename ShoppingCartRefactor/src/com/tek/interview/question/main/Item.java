package com.tek.interview.question.main;

/*
 * represents an item, contains a price and a description.
 *
 */
public class Item {

	private String description;
	private float price;

	public Item(String description, float price) throws InvalidPriceException {
		super();
		this.description = description;
		if (price > 0.0) {
			this.price = price;
		} else {
			throw new InvalidPriceException("Invalid price");
		}
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}
}
