package hotelmanagement.Controller;

import hotelmanagement.Dto.RoomDTO;
import hotelmanagement.Model.Room;
import hotelmanagement.Service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("")//http://localhost:8080/api/v1/rooms?page=1&limit=10
    public ResponseEntity<List<Room>> getRooms(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("{id}")
    public ResponseEntity<Room> getRoombyId(@PathVariable("id") int roomId) {
        Room room = roomService.getRoomById((long) roomId);

        if (room != null) {
            return ResponseEntity.ok(room); // Trả về phòng dưới dạng JSON
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<?> insertRoom(@Valid @RequestBody RoomDTO roomDTO,
                                             BindingResult result){

        if (result.hasErrors()) {
            StringBuilder errormessage = new StringBuilder();
            result.getFieldErrors().forEach(
                    fieldError -> {
                        errormessage.append(fieldError.getDefaultMessage() + "\n");
            });
            return ResponseEntity.badRequest().body(errormessage.toString());
        }
        Room room = roomService.convertToEntity(roomDTO);

        Room savedRoom = roomService.saveRoom(room);
        if (savedRoom != null) {
            return new ResponseEntity<>("Room saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to save room", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return ResponseEntity.ok("insertRoom here");
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateRoom(@PathVariable("id") int roomId, @RequestBody RoomDTO roomDTO){
        Room updatedRoom = roomService.updateRoom(roomId, roomDTO);

        if (updatedRoom != null) {
            return new ResponseEntity<>("Room updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") int roomId) {
        roomService.deleteRoom((long) roomId);
        return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
    }
}
