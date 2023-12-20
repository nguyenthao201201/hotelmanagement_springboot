package hotelmanagement.Controller;

import hotelmanagement.Dto.BookingDTO;
import hotelmanagement.Model.Booking;
import hotelmanagement.Service.BookingService;
import hotelmanagement.Service.IBookingService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}bookings")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("")//http://localhost:8080/api/v1/bookings?page=1&limit=10
    public ResponseEntity<String> getBookings(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok(String.format("getBookings, page = %d, limit = %d", page, limit));
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getBookingbyId(@PathVariable("id") long bookingId) {
        try {
            Booking existingBooking = bookingService.getBookingById(bookingId);
            return ResponseEntity.ok(existingBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping ("/user/{user_id}") //
    //GET http://localhost:8088/api/v1/bookings/user/4
    public ResponseEntity<?> getBookings(//@Valid
                                             @PathVariable("user_id") Long userId) {

        try {
            List<Booking> bookings = bookingService.findByUserId(userId);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> insertBooking(//@Valid
                                               @RequestBody BookingDTO bookingDTO,
                                                BindingResult result) {

        try {
            if (result.hasErrors()) {
                StringBuilder errormessage = new StringBuilder();
                result.getFieldErrors().forEach(
                        fieldError -> {
                            errormessage.append(fieldError.getDefaultMessage() + "\n");
                                 });
                return ResponseEntity.badRequest().body(errormessage.toString());
            }
            //trả về giá trị của
            Booking bookingResponse = bookingService.createBooking(bookingDTO);
            return ResponseEntity.ok(bookingResponse);
        } catch (Exception e) {
            // Handle exceptions and return an error response with HTTP status 500 (Internal Server Error)

            return ResponseEntity.badRequest().body(e.getMessage());
        }
        //return ResponseEntity.ok("insertBooking here");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBooking(//@Valid
                                               @PathVariable("id") long bookingId,
                                                //@Valid
                                               @RequestBody BookingDTO bookingDTO,
                                                BindingResult result) {


        try {
            Booking booking = bookingService.updateBooking(bookingId, bookingDTO);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        //return ResponseEntity.ok("updateBooking here" + bookingId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("deleteBooking " + bookingId + " successfully");
    }
}
