package com.tek.interview.question.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tek.interview.question.main.Calculator;
import com.tek.interview.question.main.InvalidPriceException;
import com.tek.interview.question.main.InvalidQuantityException;
import com.tek.interview.question.main.Item;
import com.tek.interview.question.main.Order;
import com.tek.interview.question.main.OrderLine;

public class CalculatorTest {
	private Calculator calculator;
	private Order order;
	private OrderLine orderLine;

	@Before
	public void initCalculator() {
		order = new Order();
		calculator = new Calculator();
	}
	
	@Test
	public void shouldCalculatePrice() throws Exception {
		orderLine = new OrderLine(new Item("Book", 12.49f), 1);
		assertEquals(13.74f, calculator.calculatePrice(orderLine), 0.0f);

	}

	@Test
	public void shouldCalculateSalesTaxPriceForNonImported() throws Exception {
		orderLine = new OrderLine(new Item("Book", 12.49f), 1);
		assertEquals(1.25f, calculator.calculateSalesTax(orderLine), 0.01f);

	}

	@Test
	public void shouldCalculateSalesTaxPriceForImported() throws Exception {
		orderLine = new OrderLine(new Item("imported box of chocolate", 10), 1);
		assertEquals(1.50f, calculator.calculateSalesTax(orderLine), 0.0f);

	}
	
	@Test
	public void shouldCalculateSalesTaxPriceForImportedCaseSensitive() throws Exception {
		orderLine = new OrderLine(new Item("Imported bottle of perfume", 32.19f), 1);
		assertEquals(4.83f, calculator.calculateSalesTax(orderLine), 0.01f);

	}

	@Test
	public void shouldCalculateOrderTotalPrice() throws Exception {
		
		Order cart = new Order();

		cart.add(new OrderLine(new Item("book", 12.49f), 1));
		cart.add(new OrderLine(new Item("music CD", 14.99f), 1));
		cart.add(new OrderLine(new Item("chocolate bar", 0.85f), 1));
		calculator.calculateOrderTotal(cart);
		assertEquals(28.33, calculator.calculateOrderTotal(cart), 0.01);
	}

	@Test(expected = InvalidPriceException.class)
	public void shouldThrowExceptionWhenInvalidPriceIsPassed() throws Exception {
		orderLine = new OrderLine(new Item("Book", -12.49f), 1);
		calculator.calculatePrice(orderLine);

	}

	@Test(expected = InvalidQuantityException.class)
	public void shouldThrowExceptionWhenInvalidQuanitiyIsPasses() throws Exception {
		orderLine = new OrderLine(new Item("Book", 12.49f), -1);
		calculator.calculatePrice(orderLine);
	}

}
