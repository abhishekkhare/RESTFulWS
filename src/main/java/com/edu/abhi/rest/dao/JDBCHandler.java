package com.edu.abhi.rest.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.edu.abhi.rest.domain.Customer;

/**
 * 
 * @author abhishekkhare
 *
 */
public class JDBCHandler {
	private static Map<Integer, String> teamMap = new HashMap<Integer, String>();
	private static Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
	private static AtomicInteger idCounter = new AtomicInteger();

	// Populate the DB on load of class so we have some data to play with.
	static {
		for (int i = 0; i < 20; i++) {
			int key = new Double(Math.random()).intValue() + i;
			teamMap.put(key, "Team" + key);
		}

		Customer customer = new Customer();
		int id = idCounter.incrementAndGet();
		customer.setId(id);
		customer.setFirstName("Bill");
		customer.setLastName("Burke");
		customer.setStreet("263 Clarendon Street");
		customer.setCity("Boston");
		customer.setState("MA");
		customer.setZip("02115");
		customer.setCountry("USA");
		customerDB.put(id, customer);

		customer = new Customer();
		id = idCounter.incrementAndGet();
		customer.setId(id);
		customer.setFirstName("Joe");
		customer.setLastName("Burke");
		customer.setStreet("263 Clarendon Street");
		customer.setCity("Boston");
		customer.setState("MA");
		customer.setZip("02115");
		customer.setCountry("USA");
		customerDB.put(id++, customer);

		customer = new Customer();
		id = idCounter.incrementAndGet();
		customer.setId(id);
		customer.setFirstName("Monica");
		customer.setLastName("Burke");
		customer.setStreet("263 Clarendon Street");
		customer.setCity("Boston");
		customer.setState("MA");
		customer.setZip("02115");
		customer.setCountry("USA");
		customerDB.put(id++, customer);

		customer = new Customer();
		id = idCounter.incrementAndGet();
		customer.setId(id);
		customer.setFirstName("Steve");
		customer.setLastName("Burke");
		customer.setStreet("263 Clarendon Street");
		customer.setCity("Boston");
		customer.setState("MA");
		customer.setZip("02115");
		customer.setCountry("USA");
		customerDB.put(id++, customer);

	}

	public Map<Integer, String> getTeam() {
		return teamMap;
	}

	public static Map<Integer, Customer> getCustomerDB() {
		return customerDB;
	}

	public static void setCustomerDB(Map<Integer, Customer> customerDB) {
		JDBCHandler.customerDB = customerDB;
	}

	public static AtomicInteger getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(AtomicInteger idCounter) {
		JDBCHandler.idCounter = idCounter;
	}

	public static Customer getUserByName(String firstName, String lastName) {
		Set<Integer> keys = customerDB.keySet();
		for (Integer integer : keys) {
			Customer customer = customerDB.get(integer);
			if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
				System.out.println("Found:" + firstName + " - " + lastName);
				return customer;
			}
		}
		System.out.println("Not Found:" + firstName + " - " + lastName);
		return null;
	}
}
