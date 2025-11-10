package smart.job.test.infrastructure.in;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import smart.job.test.domain.in.UserInputPort;
import smart.job.test.domain.model.UserDTO;
import smart.job.test.domain.model.UserMessageDTO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    private UserInputPort userInputPort;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertUser(){
        UserDTO userDTO = getUserDTO();

        doReturn(new UserMessageDTO()).when(userInputPort).insertUser(userDTO);

        ResponseEntity<Object> response = userController.insertUser(userDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetUser(){
        String email = "test@email.com";
        doReturn(new UserMessageDTO()).when(userInputPort).getUser(anyString());

        ResponseEntity<Object> response = userController.getUser(email);

        assertNotNull(response);
    }

    @Test
    public void testGetAllUsers(){
        doReturn(List.of(new UserMessageDTO())).when(userInputPort).getAllUsers();

        ResponseEntity<Object> response = userController.getAllUsers();

        assertNotNull(response);
    }

    UserDTO getUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test-name");
        userDTO.setEmail("test-email");
        userDTO.setPassword("test-password");
        return userDTO;
    }
}