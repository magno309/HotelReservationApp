package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testCustomerHappyPath(){
        String firstName = "Luis";
        String lastName = "Sanabria";
        String email = "test@test.com";
        Customer customer = new Customer(firstName, lastName, email);
        Assertions.assertEquals(firstName, customer.getFirstName());
        Assertions.assertEquals(lastName, customer.getLastName());
        Assertions.assertEquals(email, customer.getEmail());
    }

    @Test
    public void testCustomerInvalidEmail(){
        String firstName = "Luis";
        String lastName = "Sanabria";
        String email = "Invalid Email!";

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Customer customer = new Customer(firstName, lastName, email);
        });
        Assertions.assertTrue(exception.getMessage().contains("Error, invalid email!"));
    }
}
