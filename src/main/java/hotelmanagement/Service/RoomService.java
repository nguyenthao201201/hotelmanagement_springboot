package hotelmanagement.Service;

import hotelmanagement.Dto.RoomDTO;
import hotelmanagement.Model.Room;

import hotelmanagement.Repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public Room convertToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        return room;
    }

    public Object convertToDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(room, roomDTO);
        return roomDTO;
    }

    public Room updateRoom(int roomId, RoomDTO roomDTO) {
        Room room = roomRepository.findById((long) roomId).orElse(null);
        if (room != null) {
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setPrice(roomDTO.getPrice());
            room.setStatus(roomDTO.getStatus());
            room.setType(roomDTO.getType());
            room.setDescription(roomDTO.getDescription());
            return roomRepository.save(room);
        }
        return null;
    }
}
