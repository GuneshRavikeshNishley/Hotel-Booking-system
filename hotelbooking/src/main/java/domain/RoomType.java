package domain;

public enum RoomType {

    SINGLE(1), DOUBLE(2),FAMILY(4);
    private  Integer roomtype;

    RoomType (Integer roomtype){
        this.roomtype =roomtype;
    }

    public Integer roomtype(){
        return this.roomtype;
    }
}
