package hotelmanagement.Controller;

import hotelmanagement.Dto.BookingDetailDTO;
import hotelmanagement.Model.Booking;
import hotelmanagement.Model.BookingDetail;
import hotelmanagement.Service.BookingDetailService;
import hotelmanagement.Service.DataNotFoundException;
import hotelmanagement.response.BookingDetailResponse;
//import jakarta.validation.Valid;
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
            //@Valid
            @RequestBody BookingDetailDTO bookingDetailDTO ) {
        try {
            BookingDetail bookingDetail = bookingDetailService.createBookingDetail(bookingDetailDTO);
            //tra ve gia tri tuong ung cac truong trong booking detail
            return ResponseEntity.ok(BookingDetailResponse.fromBookingDetail(bookingDetail));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
       // return ResponseEntity.ok("createBookingDetail");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingDetail(
           // @Valid
            @PathVariable("id") Long id) throws DataNotFoundException {
       BookingDetail bookingDetail =  bookingDetailService.getBookingDetail(id);
        return ResponseEntity.ok().body(BookingDetailResponse.fromBookingDetail(bookingDetail));
      //  return ResponseEntity.ok(bookingDetail); (trả ve chi tiet booking detail)
    }

    // Lấy danh sách các order detail của 1 order nào đó
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getBookingDetails(
            //@Valid
            @PathVariable("bookingId") Long bookingId
    ) {
       List<BookingDetail> bookingDetails = bookingDetailService.findByBookingId(bookingId);
       List<BookingDetailResponse> bookingDetailResponses = bookingDetails
               .stream()
               .map(BookingDetailResponse::fromBookingDetail)
               .toList();
         return ResponseEntity.ok(bookingDetailResponses);//tra ve cac truong tuong ung trong booking detail
        //return ResponseEntity.ok(bookingDetails); (tra ve danh sach chi tiet cac thong tin booking detail)
    }

    @PutMapping("/{id}")

    public ResponseEntity<?> updateBookingDetail(
           // @Valid
            @PathVariable("id") Long id,
            @RequestBody BookingDetailDTO bookingDetailDTO) {
        try {
            BookingDetail bookingDetail = bookingDetailService.updateBookingDetail(id, bookingDetailDTO);
            return ResponseEntity.ok(bookingDetail);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        //return ResponseEntity.ok("updateBookingDetail with" + id + "newBookingDetailDTO" + bookingDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingDetail(
           // @Valid
            @PathVariable("id") Long id) {
        bookingDetailService.deleteBookingDetail(id);
        return ResponseEntity.ok("delete BookingDetail with id = " + id + " successfully");
    }

}
