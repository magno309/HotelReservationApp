package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService service = null;

    private final Map<String, IRoom> rooms = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public static ReservationService getService() {
        if(service == null) {
            service = new ReservationService();
        }
        return service;
    }

    public boolean addRoom(IRoom room) {
        if (rooms.containsKey(room.getRoomNumber())) {
            return false;
        }
        rooms.put(room.getRoomNumber(), room);
        return true;
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkIn, Date checkOut) {
        if(isRoomAvailable(room, checkIn, checkOut) == false){
            throw new IllegalArgumentException("Room is already booked for these dates.");
        }
        Reservation newReservation = new Reservation(customer, room, checkIn, checkOut);
        this.reservations.add(newReservation);
        return newReservation;
    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> result = new ArrayList<>();

        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                result.add(room);
            }
        }
        return result;
    }

    public List<Reservation> getCustomerReservations(Customer customer){
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                result.add(reservation);
            }
        }
        return result;
    }

    public void printAllReservations() {
        reservations.forEach(
                reservation -> System.out.println(reservation.toString())
        );
    }

    public List<IRoom> getAllRooms() {
        return rooms.values().stream().toList();
    }

    private boolean isRoomAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getiRoom().equals(room) &&
                    reservation.getCheckInDate().before(checkOutDate) &&
                    reservation.getCheckOutDate().after(checkInDate)) {
                return false;
            }
        }
        return true;
    }

}
