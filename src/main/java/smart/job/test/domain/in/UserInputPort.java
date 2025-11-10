package smart.job.test.domain.in;

import smart.job.test.domain.model.UserDTO;
import smart.job.test.domain.model.UserMessageDTO;

import java.util.List;

public interface UserInputPort {

    UserMessageDTO insertUser(UserDTO userDTO);
    List<UserMessageDTO> getAllUsers();
    UserMessageDTO getUser(String email);

}
