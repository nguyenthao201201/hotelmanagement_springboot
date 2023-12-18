package hotelmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookingDTO {

    @JsonProperty("user_id")
    @Min(value = 1, message = "User Id must be >= 0")
    private Long UserId;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    private String address;

    private String note;

    @JsonProperty("check_in")
    private LocalDate checkIn;

    @JsonProperty("check_out")
    private LocalDate checkOut;

    private String status;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")
    private BigDecimal totalMoney;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    @JsonProperty("payment_method")
    private String paymentMethod;


}
