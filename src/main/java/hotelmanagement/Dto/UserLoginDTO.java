package hotelmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    @JsonProperty("phone_number")
//    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

//    @NotBlank(message = "Password is mandatory")
    private String password;

}
