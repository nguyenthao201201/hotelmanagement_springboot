package hotelmanagement.Service;

import hotelmanagement.Dto.RoomDTO;
import hotelmanagement.Model.Room;
import java.util.List;

public interface IRoomService {

List<Room> getAllRooms();
    Room getRoomById(Long roomId);
    Room createRoom(RoomDTO roomDTO);
    Room updateRoom(Long roomId, RoomDTO roomDTO);
    void deleteRoom(Long roomId);
}
