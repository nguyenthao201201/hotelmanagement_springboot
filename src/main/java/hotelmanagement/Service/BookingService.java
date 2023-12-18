package hotelmanagement.Service;

import hotelmanagement.Dto.BookingDTO;
import hotelmanagement.Model.Booking;
import hotelmanagement.Model.BookingStatus;
import hotelmanagement.Repository.BookingRepository;
import hotelmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import hotelmanagement.Model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
        @Override
        public Booking createBooking(BookingDTO bookingDTO) throws Exception {
            //tìm xem userId có tồn tại không
            User user = userRepository
                    .findById(bookingDTO.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: "+ bookingDTO.getUserId()));
            //convert bookingDTO => Booking
            //dùng thư viện Model Mapper

          modelMapper.typeMap(BookingDTO.class, Booking.class)
                  .addMappings(mapper -> mapper.skip(Booking::setBookingId));

            Booking newBooking = new Booking();

            modelMapper.map(bookingDTO, newBooking);
            newBooking.setUser(user);
            newBooking.setCheckIn(new Date());

            LocalDate checkOutDate = bookingDTO.getCheckOut() == null
                    ? LocalDate.now() : bookingDTO.getCheckOut();
            if (checkOutDate.isBefore(LocalDate.now())) {
                throw new DataNotFoundException("Date must be at least today !");
            }
            newBooking.setCheckOut(new Date());

            newBooking.setStatus(BookingStatus.PENDING);
            bookingRepository.save(newBooking);
            return newBooking;
            //return modelMapper.map(bookingDTO, BookingResponse.class);

    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingDTO bookingDTO) throws DataNotFoundException{
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new DataNotFoundException("Cannot find order with id: " + id));
        User existingUser = userRepository.findById(
                bookingDTO.getUserId()).orElseThrow(() ->
                new DataNotFoundException("Cannot find user with id: " + id));
        // Tạo một luồng bảng ánh xạ riêng để kiểm soát việc ánh xạ
        modelMapper.typeMap(BookingDTO.class, Booking.class)
                .addMappings(mapper -> mapper.skip(Booking::setBookingId));
        // Cập nhật các trường của đơn hàng từ orderDTO
        modelMapper.map(bookingDTO, booking);
        booking.setUser(existingUser);
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        //no hard-delete, => please soft-delete
        if(booking != null) {
            booking.setActive(false);
            bookingRepository.save(booking);
    }
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

}