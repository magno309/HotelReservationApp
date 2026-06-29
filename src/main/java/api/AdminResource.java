package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.List;

public class AdminResource {

    private final CustomerService customerService = CustomerService.getService();
    private final ReservationService reservationService = ReservationService.getService();

    private static AdminResource service = null;

    public static AdminResource getService() {
        if (service == null) {
            service = new AdminResource();
        }
        return service;
    }

    public Customer getACustomer(String email) {
        Customer customer = customerService.getCustomer(email);
        if(customer == null) {
            System.out.println("Customer not found!");
            return null;
        }
        return customer;
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(
                room -> {
                    if(reservationService.addRoom(room)) {
                        System.out.println("Room " + room.getRoomNumber() + " added correctly!");
                    }else {
                        System.out.println("Room " + room.getRoomNumber() + " already exist!");
                    }
                }
        );
    }

    public List<IRoom> getAllRooms() {
        return null;
    }

    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservations();
    }

}
