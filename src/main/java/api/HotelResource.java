package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Date;
import java.util.List;

public class HotelResource {

    private final CustomerService customerService = CustomerService.getService();
    private final ReservationService reservationService = ReservationService.getService();

    private static HotelResource service = null;

    public static HotelResource getService() {
        if (service == null) {
            service = new HotelResource();
        }
        return service;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        boolean result = customerService.addCustomer(email, firstName, lastName);
        if (result) {
            System.out.println("Customer created correctly!");
        } else {
            System.out.println("Customer not created!");
        }
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer selectedCustomer = customerService.getCustomer(customerEmail);

        if (selectedCustomer == null) {
            System.out.println("Customer don't exist!");
            return null;
        }

        return reservationService.reserveARoom(selectedCustomer, room, checkInDate, checkOutDate);
    }

    public List<Reservation> getCustomerReservations (String customerEmail) {

        Customer selectedCustomer = customerService.getCustomer(customerEmail);

        if (selectedCustomer == null) {
            System.out.println("Customer don't exist!");
            return null;
        }

        return reservationService.getCustomerReservations(selectedCustomer);
    }

    public List<IRoom> findARoom (Date checkIn, Date checkOut) {
        return null;
    }

}
