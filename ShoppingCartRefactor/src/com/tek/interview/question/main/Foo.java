package com.tek.interview.question.main;

import java.util.LinkedHashMap;
import java.util.Map;

public class Foo {

	public static void main(String[] args) throws Exception {

		Map<String, Order> ordersMap = new LinkedHashMap<String, Order>();

		Order cart = new Order();

		Order orderOneCart = new Order();

		cart.add(new OrderLine(new Item("book", 12.49f), 1));
		cart.add(new OrderLine(new Item("music CD", 14.99f), 1));
		cart.add(new OrderLine(new Item("chocolate bar", 0.85f), 1));
		orderOneCart = (Order) cart.clone();
		ordersMap.put("Order 1", orderOneCart);
		// Reuse cart for an other order
		cart.clear();
		Order orderTwoCart = new Order();
		cart.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		cart.add(new OrderLine(new Item("imported bottle of perfume", 47.50f), 1));
		orderTwoCart = (Order) cart.clone();
		ordersMap.put("Order 2", orderTwoCart);
		// Reuse cart for an other order
		cart.clear();
		Order orderThreeCart = new Order();
		cart.add(new OrderLine(new Item("Imported bottle of perfume", 27.99f), 1));
		cart.add(new OrderLine(new Item("bottle of perfume", 18.99f), 1));
		cart.add(new OrderLine(new Item("packet of headache pills", 9.75f), 1));
		cart.add(new OrderLine(new Item("box of imported chocolates", 11.25f), 1));
		orderThreeCart = (Order) cart.clone();
		ordersMap.put("Order 3", orderThreeCart);
		new Calculator().calculateTotal(ordersMap);

	}
}