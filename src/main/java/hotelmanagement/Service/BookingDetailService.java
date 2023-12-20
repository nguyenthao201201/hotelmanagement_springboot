package hotelmanagement.Service;

import hotelmanagement.Dto.BookingDetailDTO;
import hotelmanagement.Model.Booking;
import hotelmanagement.Model.BookingDetail;
import hotelmanagement.Model.Room;
import hotelmanagement.Repository.BookingDetailRepository;
import hotelmanagement.Repository.BookingRepository;
import hotelmanagement.Repository.RoomRepository;
import hotelmanagement.Repository.ServiceRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingDetailService implements IBookingDetailService{

    private final BookingRepository bookingRepository;
    private final BookingDetailRepository bookingDetailRepository;
    private final RoomRepository roomRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public BookingDetail createBookingDetail(BookingDetailDTO bookingDetailDTO) throws Exception {

        //tìm xem bookingId có tồn tại không
        Booking booking = bookingRepository.findById(bookingDetailDTO.getBookingId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find booking with id: "+ bookingDetailDTO.getBookingId()));

        //Tìm phòng theo ID
        Room room = roomRepository
                .findById(bookingDetailDTO.getRoomId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find room with id: "+ bookingDetailDTO.getRoomId()));

        //tìm service theo ID
        hotelmanagement.Model.Service service = serviceRepository
                .findById(bookingDetailDTO.getServiceId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find service with id: "+ bookingDetailDTO.getServiceId()));

        //Tạo bookingDetail
        BookingDetail bookingDetail = BookingDetail.builder()
                .booking(booking)
                .room(room)
                .service(service)
                .numberOfDays(bookingDetailDTO.getNumberOfDays())
                .priceService(bookingDetailDTO.getPriceService())
                .priceRoom(bookingDetailDTO.getPriceRoom())
                .totalPriceRoom(bookingDetailDTO.getTotalPriceRoom())
                .numberOfServices(bookingDetailDTO.getNumberOfServices())
                .numberOfGuests(bookingDetailDTO.getNumberOfGuests())
                .totalPriceService(bookingDetailDTO.getTotalPriceService())
                .totalMoney(bookingDetailDTO.getTotalMoney())
                .build();

        //Tính tổng tiền
        bookingDetail.setTotalMoney(bookingDetail.getTotalPriceRoom().add(bookingDetail.getTotalPriceService()));
        return bookingDetailRepository.save(bookingDetail);
    }

    @Override
    public BookingDetail getBookingDetail(Long bookingDetailId) throws DataNotFoundException {
        return bookingDetailRepository.findById(bookingDetailId).orElseThrow(() ->
                new DataNotFoundException("Cannot find booking detail with id: "+ bookingDetailId));
    }

    @Override
    public BookingDetail updateBookingDetail(Long bookingDetailId, BookingDetailDTO bookingDetailDTO) throws DataNotFoundException {
        //tìm xem booking detail có tồn tại hay không
        BookingDetail existingBookingDetail = bookingDetailRepository.findById(bookingDetailId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find booking detail with id: "+ bookingDetailId));
        //tìm booking có tồn tại hay không
        Booking existingBooking = bookingRepository.findById(bookingDetailDTO.getBookingId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find booking with id: "+ bookingDetailDTO.getBookingId()));
        //tìm phòng có tồn tại hay không
        Room existingRoom = roomRepository.findById(bookingDetailDTO.getRoomId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find room with id: "+ bookingDetailDTO.getRoomId()));
        //tìm service có tồn tại hay không
        hotelmanagement.Model.Service existingService = serviceRepository.findById(bookingDetailDTO.getServiceId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find service with id: "+ bookingDetailDTO.getServiceId()));
        existingBookingDetail.setNumberOfDays(bookingDetailDTO.getNumberOfDays());
        existingBookingDetail.setNumberOfGuests(bookingDetailDTO.getNumberOfGuests());
        existingBookingDetail.setNumberOfServices(bookingDetailDTO.getNumberOfServices());
        existingBookingDetail.setPriceRoom(bookingDetailDTO.getPriceRoom());
        existingBookingDetail.setPriceService(bookingDetailDTO.getPriceService());
        existingBookingDetail.setTotalPriceRoom(bookingDetailDTO.getTotalPriceRoom());
        existingBookingDetail.setTotalPriceService(bookingDetailDTO.getTotalPriceService());
        existingBookingDetail.setTotalMoney(bookingDetailDTO.getTotalMoney());
        existingBookingDetail.setBooking(existingBooking);
        existingBookingDetail.setRoom(existingRoom);
        existingBookingDetail.setService(existingService);
        return bookingDetailRepository.save(existingBookingDetail);


       // return null;
    }

    @Override
    public void deleteBookingDetail(Long bookingDetailId) {
        bookingDetailRepository.deleteById(bookingDetailId);
    }

    @Override
    public List<BookingDetail> findByBookingId(Long bookingId) {
        //return null;
       return bookingDetailRepository.findByBookingId(bookingId);
    }

}
