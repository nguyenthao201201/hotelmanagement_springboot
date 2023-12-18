package hotelmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    @NotEmpty(message = "Room number is required")
    @JsonProperty("room_number")

    private String roomNumber;
    @NotEmpty(message = "Type is required")

    @JsonProperty("type")
    private String type;

    private String description;

    @DecimalMin(value = "0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
    @NotEmpty(message = "Status is required")
    private String status;

}
