package model;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom iRoom;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.iRoom = iRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getiRoom() {
        return iRoom;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return customer + " reservation's:\n---------------------" +
                "\n" + iRoom +
                "\n checkInDate: " + DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(checkInDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()) +
                "\n checkOutDate: " + DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(checkOutDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()) +
                "\n---------------------";
    }
}
