package hotelmanagement.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import hotelmanagement.Model.BookingDetail;
import lombok.*;

import java.math.BigDecimal;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookingDetailResponse {

    @JsonProperty("booking_id")
    private Long bookingId;

    @JsonProperty("room_id")
    private Long roomId;

    @JsonProperty("price_room")
    private BigDecimal priceRoom;

    @JsonProperty("number_of_days")
    private Integer numberOfDays;

    @JsonProperty("number_of_guests")
    private Integer numberOfGuests;

    @JsonProperty("service_id")
    private Long serviceId;

    @JsonProperty("number_of_services")
    private Integer numberOfServices;

    @JsonProperty("price_service")
    private BigDecimal priceService;

    @JsonProperty("total_price_service")
    private BigDecimal totalPriceService;

    @JsonProperty("total_price_room")
    private BigDecimal totalPriceRoom;

    @JsonProperty("total_money")
    private BigDecimal totalMoney;

    public static BookingDetailResponse fromBookingDetail(BookingDetail bookingDetail) {
        return BookingDetailResponse
                .builder()
                .bookingId(bookingDetail.getBooking().getId())
                .roomId(bookingDetail.getRoom().getRoomId())
                .priceRoom(bookingDetail.getPriceRoom())
                .numberOfDays(bookingDetail.getNumberOfDays())
                .numberOfGuests(bookingDetail.getNumberOfGuests())
                .serviceId(bookingDetail.getService().getServiceId())
                .numberOfServices(bookingDetail.getNumberOfServices())
                .priceService(bookingDetail.getPriceService())
                .totalPriceService(bookingDetail.getTotalPriceService())
                .totalPriceRoom(bookingDetail.getTotalPriceRoom())
                .totalMoney(bookingDetail.getTotalMoney())
                .build();
    }
}
