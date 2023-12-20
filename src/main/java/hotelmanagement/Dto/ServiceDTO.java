package hotelmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
//    @NotEmpty(message = "Service name is required")
    @JsonProperty("service_name")
    private String serviceName;
//    @DecimalMin(value = "0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
    private String description;
}
