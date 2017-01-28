package com.tek.interview.question.main;

import java.util.Map;

public class Calculator {

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
	public void calculateTotal(Map<String, Order> ordersMap) {

		double grandtotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : ordersMap.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");

			Order order = entry.getValue();

			// Iterate through the items in the order
			grandtotal += calculateOrderTotal(order);
		}

		System.out.println("Sum of orders: " + Math.round(grandtotal * 100.00) / 100.00);
	}

	public double calculateOrderTotal(Order order) {
		double totalTax = 0;
		double total = 0;
		for (int i = 0; i <= order.size() - 1; i++) {

			// Calculate the taxes
			double tax = 0;

			tax = calculateSalesTax(order.get(i));

			// Calculate the total price
			double totalprice = calculatePrice(order.get(i));

			// Print out the item's total price
			System.out.println(order.get(i).getQuantity() + " " + order.get(i).getItem().getDescription() + ": " + Math.round(totalprice * 100.00) / 100.00);

			// Keep a running total
			totalTax += tax;
			total += order.get(i).getItem().getPrice() * order.get(i).getQuantity();
		}

		// Print out the total taxes
		System.out.println("Sales Tax: " + Math.round(totalTax * 100.00) / 100.00);

		// Print out the total amount
		System.out.println("Total: " + Math.round(total * 100.00) / 100.00);

		return total;
	}

	public double calculateSalesTax(OrderLine orderLine) {
		double tax;
		if (orderLine.getItem().getDescription().contains("imported") || orderLine.getItem().getDescription().contains("Imported")) {
			tax = rounding(orderLine.getItem().getPrice() * orderLine.getQuantity() * 0.15); // Extra 5% tax on imported & Imported items																						 
		} else {
			tax = rounding(orderLine.getItem().getPrice() * orderLine.getQuantity() * 0.10);
		}
		return tax;
	}

	public double calculatePrice(OrderLine orderLine) {

		
		return orderLine.getItem().getPrice() * orderLine.getQuantity()
				+ Math.round(calculateSalesTax(orderLine) * 100.00) / 100.00;
	}

}