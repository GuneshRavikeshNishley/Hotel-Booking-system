package repository;

import domain.Room;
import domain.RoomType;
import exception.InfrastructureException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepository extends BaseRepository<Room>{

    private static final String LOG_ERROR_MSG = "Error during the room %s";

    public void add(Room room){
        Connection connection = openConnection();

        try {
            String sql = "INSERT INTO room(id,type) VALUES(?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, room.getId());
            pstmt.setString(2, room.getType().name());


            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The insert wasn't executed!");
            }

        } catch (SQLException e){ e.printStackTrace();
            logger.error(String.format(LOG_ERROR_MSG, "insert"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "insert"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void modify(Room room){
        Connection connection = openConnection();

        try{
            String sql = "UPDATE room set type = ? WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);


            pstmt.setString(2,room.getType().name());
            pstmt.setString(3, room.getId());


            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The Update wasn't executed!");
            }
        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "update"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "update"),e);
        } finally {
            closeConnection(connection);
        }

    }

    public void remove(Room room) {
        Connection connection = openConnection();

        try{

            String sql = "DELETE FROM room WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, room.getId());

            if(pstmt.executeUpdate() == 0){
                throw new InfrastructureException("The delete wasn't executed!");
            }
        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),e);
        }

    }

    public Optional<Room> findById(String id){
        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM room WHERE id=?");
            statement.setString(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                RoomType type  = RoomType.valueOf(resultSet.getString("type"));
                return Optional.of(new Room(id,type));
            }
        } catch(SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();

    }

    @Override
    public Optional<Room> findByCriteria(String field, String criteria) {
        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM room WHERE "+field+"=?");
            statement.setString(1,criteria);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String id = resultSet.getString("id");
                RoomType type  = RoomType.valueOf(resultSet.getString("type"));

                return Optional.of(new Room(id, type));
            }
        } catch(SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();
    }

    public List<Room> findAll() {

        List<Room> rooms = new ArrayList<Room>();

        Connection conn = openConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM room");


            while (resultSet.next()) {
                String id = resultSet.getString("id");
                RoomType type  = RoomType.valueOf(resultSet.getString("type"));

                rooms.add(new Room(id,type));
            }

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"),e);
        } finally {
            closeConnection(conn);
        }
        return rooms;
    }
}
