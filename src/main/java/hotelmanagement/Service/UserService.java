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

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
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
    public User login(String phoneNumber, String password) throws Exception{

        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException(" Invalid phone number/ password");
        }
        return optionalUser.get();
        //User existedUser = optionalUser.get();
        //check Password
//        if(!passwordEncoder.matches(password, existedUser.getPassword())){
//            throw new BadCredentialsException("Invalid phone number/ password");
        }
//        //authenticate with Java Spring Security
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(phoneNumber, password);
//      authenticationManager.authenticate(authenticationToken) ;
//
//        return jwtTokenUtil.generateToken(existedUser);
    }
//}
