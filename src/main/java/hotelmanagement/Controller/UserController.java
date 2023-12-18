package hotelmanagement.Controller;

import hotelmanagement.Dto.UserDTO;
import hotelmanagement.Model.User;
import hotelmanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hotelmanagement.Service.IUserService;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        try {

            if (result.hasErrors()) {
                StringBuilder errormessage = new StringBuilder();
                result.getFieldErrors().forEach(
                        fieldError -> {
                            errormessage.append(fieldError.getDefaultMessage() + "\n");
                        });
                return ResponseEntity.badRequest().body(errormessage.toString());
            }

            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password and Confirm Password must be the same");
            }
            User user = userService.createUser(userDTO);

           // return ResponseEntity.ok("registerUser successfully");
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO) {
        String token = userService.login(userDTO.getPhoneNumber(), userDTO.getPassword());

       return ResponseEntity.ok(token);
    }

}
