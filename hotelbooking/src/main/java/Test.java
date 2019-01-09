import domain.Room;
import domain.RoomType;
import repository.RoomRepository;

import java.util.List;
import java.util.Optional;


public class Test {



    public static void main(String[] args){

        RoomRepository roomRepository = new RoomRepository();
        Room room = new Room("101", RoomType.SINGLE);
        roomRepository.add(room);
       // Optional<Room> room = roomRepository.findById("101");
        //room.get().show();










    }




}
