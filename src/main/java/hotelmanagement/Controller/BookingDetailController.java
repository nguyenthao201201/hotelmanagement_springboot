package hotelmanagement.Controller;

import hotelmanagement.Dto.BookingDetailDTO;
import hotelmanagement.Model.Booking;
import hotelmanagement.Model.BookingDetail;
import hotelmanagement.Service.BookingDetailService;
import hotelmanagement.Service.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}booking_details")
@RequiredArgsConstructor
public class BookingDetailController {


    private final BookingDetailService bookingDetailService;

    @PostMapping("/create")
    public ResponseEntity<?> createBookingDetail(
            @Valid @RequestBody BookingDetailDTO bookingDetailDTO ) {
        try {
            BookingDetail bookingDetail = bookingDetailService.createBookingDetail(bookingDetailDTO);
            return ResponseEntity.ok(bookingDetail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
       // return ResponseEntity.ok("createBookingDetail");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingDetail(
            @Valid @PathVariable("id") Long id) throws DataNotFoundException {
       BookingDetail bookingDetail =  bookingDetailService.getBookingDetail(id);
        return ResponseEntity.ok(bookingDetail);
    }

    // Lấy danh sách các order detail của 1 order nào đó
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getBookingDetails(
            @Valid @PathVariable("bookingId") Long bookingId
    ) {
       List<BookingDetail> bookingDetails = bookingDetailService.findByBookingId(bookingId);
        return ResponseEntity.ok(bookingDetails);
    }

    @PutMapping("/{id}")

    public ResponseEntity<?> updateBookingDetail(
            @Valid @PathVariable("id") Long id,
            @RequestBody BookingDetailDTO newBookingDetailDTO) {
        return ResponseEntity.ok("updateBookingDetail with" + id + "newBookingDetailDTO" + newBookingDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingDetail(
            @Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("deleteBookingDetail" + id);
    }

}
