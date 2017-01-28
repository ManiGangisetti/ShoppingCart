package com.tek.interview.question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

/*
 * represents an item, contains a price and a description.
 *
 */
class Item {

	private String description;
	private float price;

	public Item(String description, float price) {
		super();
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}
}

/*
 * represents an order line which contains the @link Item and the quantity.
 *
 */
class OrderLine implements Cloneable {

	private int quantity;
	private Item item;

	/*
	 * @param item Item of the order
	 * 
	 * @param quantity Quantity of the item
	 */
	public OrderLine(Item item, int quantity) {
		if (item == null) {
			System.err.println("ERROR - Item is NULL");
		}
		assert quantity > 0;
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public Object clone() throws CloneNotSupportedException {
		OrderLine o = new OrderLine(item, quantity);
		return o;
	}
}

class Order implements Cloneable {

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}

	public Order() {
		super();
	}

	public Order(List<OrderLine> orderLines) {
		super();
		this.orderLines = orderLines;
	}

	public Object clone() throws CloneNotSupportedException {
		Order o = new Order(orderLines);
		o.orderLines = new ArrayList<OrderLine>(o.orderLines.size());
		for (OrderLine o1 : orderLines) {
			o.orderLines.add((OrderLine) o1.clone());
		}
		return o;
	}

}

class calculator {

	public static double rounding(double value) {
		return ((double) ((value) * 100)) / 100;
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's price * quantity
	 * * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order
	 */
	public void calculate(Map<String, Order> o) {

		double grandtotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");

			Order r = entry.getValue();

			double totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			for (int i = 0; i <= r.size() - 1; i++) {

				// Calculate the taxes
				double tax = 0;

				if (r.get(i).getItem().getDescription().contains("imported") || r.get(i).getItem().getDescription().contains("Imported")) {
					tax = rounding(r.get(i).getItem().getPrice() * 0.15); // Extra 5% tax on imported items
				} else {
					tax = rounding(r.get(i).getItem().getPrice() * 0.10);
				}

				// Calculate the total price
				double totalprice = r.get(i).getItem().getPrice() + Math.round(tax * 100.00) / 100.00;

				// Print out the item's total price
				System.out.println(r.get(i).getQuantity() + " " + r.get(i).getItem().getDescription() + ": " + Math.round(totalprice * 100.00) / 100.00);

				// Keep a running total
				totalTax += tax;
				total += r.get(i).getItem().getPrice();
			}

			// Print out the total taxes
			System.out.println("Sales Tax: " + Math.round(totalTax * 100.00) / 100.00);

			// Print out the total amount
			System.out.println("Total: " + Math.round(total * 100.00) / 100.00);
			grandtotal += total;
		}

		System.out.println("Sum of orders: " + Math.round(grandtotal * 100.00) / 100.00);
	}
}

public class Foo {

	public static void main(String[] args) throws Exception {

		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order c = new Order();

		Order c1 = new Order();

		c.add(new OrderLine(new Item("book", 12.49f), 1));
		c.add(new OrderLine(new Item("music CD", 14.99f), 1));
		c.add(new OrderLine(new Item("chocolate bar", 0.85f), 1));
		c1 = (Order) c.clone();
		o.put("Order 1", c1);
		// Reuse cart for an other order
		c.clear();
		Order c2 = new Order();
		c.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", 47.50f), 1));
		c2 = (Order) c.clone();
		o.put("Order 2", c2);
		// Reuse cart for an other order
		c.clear();
		Order c3 = new Order();
		c.add(new OrderLine(new Item("Imported bottle of perfume", 27.99f), 1));
		c.add(new OrderLine(new Item("bottle of perfume", 18.99f), 1));
		c.add(new OrderLine(new Item("packet of headache pills", 9.75f), 1));
		c.add(new OrderLine(new Item("box of imported chocolates", 11.25f), 1));
		c3 = (Order) c.clone();
		o.put("Order 3", c3);
		new calculator().calculate(o);

	}
}