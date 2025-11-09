package smart.job.test.domain.in;

import smart.job.test.domain.model.UserDTO;
import smart.job.test.domain.model.UserMessageDTO;

public interface UserInputPort {

    UserMessageDTO insertUser(UserDTO userDTO);

}
