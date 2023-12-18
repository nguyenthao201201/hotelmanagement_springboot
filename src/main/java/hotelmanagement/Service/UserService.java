package hotelmanagement.Service;

import hotelmanagement.Dto.UserDTO;
import hotelmanagement.Model.Role;
import hotelmanagement.Model.User;
import hotelmanagement.Repository.RoleRepository;
import hotelmanagement.Repository.UserRepository;
import hotelmanagement.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User createUser(UserDTO userDTO) throws Exception{
        String phoneNumber = userDTO.getPhoneNumber();
        // Kiêm tra số điện thoại đã tồn tại chưa
        if(userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        //userDTO => user
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .address(userDTO.getAddress())

                .build();

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));

        newUser.setRole(role);
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        return null;
    }
}
