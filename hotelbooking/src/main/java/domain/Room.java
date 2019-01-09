package domain;

import java.util.Objects;

public class Room {

    private String id;
    private RoomType type;
    private int people;




    public Room(String id, RoomType type){
        this.id = id;
        this.type = type;

    }

    public String getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public int getPeople() {
        return people;
    }

    public void show(){
        System.out.println(getId());
        System.out.println(getType());
    }


}
