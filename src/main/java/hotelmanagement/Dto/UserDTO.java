package hotelmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("full_name")
    private String fullName;
    //@NotBlank(message = "Phone number is mandatory")
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("address")
    private String address;

//    @NotBlank(message = "Password is mandatory")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;


    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    @JsonProperty("role_id")

    private Long roleId;

}
