package domain;

import java.time.Instant;

public class BookOrder {

    private Room room;
    private User user;
    private Instant bookDate;
    private Instant checkOutDate;
    private Float price;


    public BookOrder(Room room, User user, Instant bookDate, Instant checkOutDate, Float price) {
        this.room = room;
        this.user = user;
        this.bookDate = bookDate;
        this.checkOutDate = checkOutDate;
        this.price = price;

    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public Instant getBookDate() {
        return bookDate;
    }

    public Instant getCheckOutDate() {
        return checkOutDate;
    }

    public Float getPrice() {
        return price;
    }

}

