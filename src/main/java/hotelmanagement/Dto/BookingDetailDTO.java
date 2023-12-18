package hotelmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailDTO {

    @JsonProperty("booking_id")
    @Min(value = 1, message = "Booking Id must be > 0")
    private Long bookingId;

    @JsonProperty("room_id")
    @Min(value = 1, message = "Room Id must be > 0")
    private Long roomId;

    @JsonProperty("price_room")
    @Min(value = 0, message = "Price room must be >= 0")
    private BigDecimal priceRoom;

    @JsonProperty("number_of_days")
    @Min(value = 1, message = "Number of days must be >= 1")
    private Integer numberOfDays;

    @JsonProperty("number_of_guests")
    @Min(value = 1, message = "Number of guests must be >= 1")
    private Integer numberOfGuests;

    @JsonProperty("service_id")
    @Min(value = 1, message = "Service Id must be > 0")
    private Long serviceId;

    @JsonProperty("number_of_services")
    @Min(value = 1, message = "Number of services must be >= 1")
    private Integer numberOfServices;

    @JsonProperty("price_service")
    @Min(value = 0, message = "Price service must be >= 0")
    private BigDecimal priceService;

    @JsonProperty("total_price_service")
    private BigDecimal totalPriceService;

    @JsonProperty("total_price_room")
    private BigDecimal totalPriceRoom;

    @JsonProperty("total_money")
    private BigDecimal totalMoney;
}
