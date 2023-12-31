package hotelmanagement.Service;

import hotelmanagement.Dto.UserDTO;
import hotelmanagement.Model.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;

    User login(String phoneNumber, String password) throws Exception;

}

