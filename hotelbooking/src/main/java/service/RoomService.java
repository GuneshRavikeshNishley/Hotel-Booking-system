package service;

import com.google.common.annotations.VisibleForTesting;
import domain.Room;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.RoomRepository;
import repository.Repository;
import java.util.List;
public class RoomService implements Service<Room> {

    private static final int MAX_PEOPLE_ALLOWED = 4;

    private Repository<Room> roomRepository;

    public RoomService() {
        roomRepository = new RoomRepository();
    }

    public RoomService(Repository<Room> roomRepository) {
        this.roomRepository = roomRepository;
    }

    @VisibleForTesting
    void validate(Room room) {
        if (room.getPeople() > MAX_PEOPLE_ALLOWED) {
            throw new ValidationException("The number of people cannot be more than 4");
        }

        if (isDuplicatedId(room)) {
            throw new ValidationException("There is another room with the same id, please, choose another one");
        }
    }

    @VisibleForTesting
    boolean isDuplicatedId(Room room) {
        return roomRepository
                .findByCriteria("type", room.getType().toString())
                .filter(c -> !c.getId().equals(room.getId()))
                .isPresent();
    }

    public void add(Room room) {
        validate(room);
        roomRepository.add(room);
    }

    public void modify(Room room) {
        roomRepository
                .findById(room.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Room with id " + room.getId() + " was not found!"));

        validate(room);
        roomRepository.modify(room);
    }

    public void remove(String id) {
        roomRepository.remove(roomRepository
                                     .findById(id)
                                     .orElseThrow(
                                             () -> new EntityNotFoundException("Room with id " + id + " was not found!"))
        );
    }

    public Room findById(String id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room with id " + id + " was not found!"));
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
