package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService service = null;

    private final Map<String, Customer> customers = new HashMap<>();

    public boolean addCustomer(String email, String firstName, String lastName){
        try{
            if(customers.containsKey(email)){
                System.out.println("Email already in use!");
                return false;
            }
            Customer newCustomer = new Customer(firstName, lastName, email);
            customers.put(email, newCustomer);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Customer getCustomer(String customerEmail){
        Optional<Customer> foundCustomer = customers.values().stream().filter(
                customer -> customer.getEmail().equals(customerEmail)
        ).findFirst();
        return foundCustomer.orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customers.values().stream().toList();
    }

    public static CustomerService getService() {
        if (service == null){
            service = new CustomerService();
        }
        return service;
    }
}
