package hotelmanagement.Dto;

//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int userId;

    //@NotEmpty(message = "Full name is required")
    private String fullName;

//    @NotEmpty(message = "Phone number is required")
//    @Pattern(regexp = "\\d{10,11}", message = "Invalid phone number")
    private String phoneNumber;

//    @NotEmpty(message = "Address is required")
    private String address;

//    @NotEmpty(message = "Email is required")
//    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

//    @NotEmpty(message = "Identity card is required")
    private String identityCard;
}
