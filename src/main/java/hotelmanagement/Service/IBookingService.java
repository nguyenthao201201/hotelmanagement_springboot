package hotelmanagement.Service;

import hotelmanagement.Dto.BookingDTO;
import hotelmanagement.Model.Booking;

import java.util.List;

    public interface IBookingService {
    Booking createBooking(BookingDTO bookingDTO) throws Exception;
    Booking getBookingById(Long bookingId);
    Booking updateBooking(Long bookingId, BookingDTO bookingDTO) throws DataNotFoundException;

    void deleteBooking(Long bookingId);
    List<Booking> findByUserId(Long userId);
}
