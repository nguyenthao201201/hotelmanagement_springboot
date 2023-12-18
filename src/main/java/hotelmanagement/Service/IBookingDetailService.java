package hotelmanagement.Service;

import hotelmanagement.Dto.BookingDetailDTO;
import hotelmanagement.Model.Booking;
import hotelmanagement.Model.BookingDetail;

import java.util.List;

public interface IBookingDetailService {
    BookingDetail createBookingDetail(BookingDetailDTO bookingDetailDTO) throws Exception;

    BookingDetail getBookingDetail(Long bookingDetailId) throws DataNotFoundException;

    BookingDetail updateBookingDetail(Long bookingDetailId, BookingDetailDTO bookingDetailDTO) throws Exception;

    void deleteBookingDetail(Long bookingDetailId);

    List<BookingDetail> findByBookingId(Long bookingId);


}
