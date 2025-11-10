package smart.job.test.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import smart.job.test.domain.model.UserDTO;
import smart.job.test.domain.model.UserMessageDTO;
import smart.job.test.domain.model.exceptions.DefaultException;
import smart.job.test.domain.model.exceptions.PwdNotMatchesException;
import smart.job.test.infrastructure.out.repository.crud.UserRepository;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;
import smart.job.test.infrastructure.out.repository.mapper.UserDtoToEntityMapper;
import smart.job.test.infrastructure.out.repository.mapper.UserEntityToUserMessageDtoMapper;

import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessUserUseCaseTest {

    @InjectMocks
    private ProcessUserUseCase processUserUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDtoToEntityMapper dtoToEntityMapper;

    @Mock
    private UserEntityToUserMessageDtoMapper entityToUserMessageDtoMapper;


    private UserDTO userDTO;
    private UserEntity userEntity;
    private UserMessageDTO userMessageDTO;

    @Before
    public void setUp() {
        processUserUseCase.pwdRequirements = "^.{8,}$";

        userDTO = new UserDTO();
        userDTO.setName("Test User");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("ValidPassword123");
        userDTO.setPhones(Collections.emptyList());

        userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setName("Test User");
        userEntity.setEmail("test@example.com");

        userMessageDTO = new UserMessageDTO();
        userMessageDTO.setId(userEntity.getId());
        userMessageDTO.setCreated(new Date(Instant.now().toEpochMilli()));
        userMessageDTO.setLastLogin(new Date(Instant.now().toEpochMilli()));
        userMessageDTO.setToken(UUID.randomUUID());
        userMessageDTO.setActive(true);
    }

    @Test(expected = PwdNotMatchesException.class)
    public void insertUser_whenPasswordIsInvalid_shouldThrowPwdNotMatchesException() {
        userDTO.setPassword("short()");
        processUserUseCase.insertUser(userDTO);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertUser_whenEmailAlreadyExists_shouldThrowDataIntegrityViolationException() {
        when(dtoToEntityMapper.convert(any(UserDTO.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenThrow(new DataIntegrityViolationException("El correo ya registrado"));
        processUserUseCase.insertUser(userDTO);
    }

    @Test(expected = DefaultException.class)
    public void insertUser_whenGenericErrorOccurs_shouldThrowDefaultException() {

        when(dtoToEntityMapper.convert(any(UserDTO.class))).thenThrow(new RuntimeException("Error inesperado en el mapper"));
        processUserUseCase.insertUser(userDTO);

    }

    @Test
    public void insertUser_whenDataIsValid_shouldSaveAndReturnUserMessageDTO() {
        
        when(dtoToEntityMapper.convert(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity); // save retorna la entidad guardada
        when(entityToUserMessageDtoMapper.convert(userEntity)).thenReturn(userMessageDTO);

        UserMessageDTO result = processUserUseCase.insertUser(userDTO);

        assertNotNull(result);
        assertEquals(userMessageDTO.getId(), result.getId());
        assertEquals(userMessageDTO.getToken(), result.getToken());

        verify(dtoToEntityMapper, times(1)).convert(userDTO);
        verify(userRepository, times(1)).save(userEntity);
        verify(entityToUserMessageDtoMapper, times(1)).convert(userEntity);
    }

    @Test
    public void getAllUsers_whenUsersExist_shouldReturnListOfUserMessageDTO() {
        
        List<UserEntity> entityList = Collections.singletonList(userEntity);
        when(userRepository.findAll()).thenReturn(entityList);
        when(entityToUserMessageDtoMapper.convert(userEntity)).thenReturn(userMessageDTO);


        List<UserMessageDTO> result = processUserUseCase.getAllUsers();


        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(userMessageDTO.getId(), result.get(0).getId());

        verify(userRepository, times(1)).findAll();
        verify(entityToUserMessageDtoMapper, times(1)).convert(userEntity);
    }

    @Test
    public void getAllUsers_whenNoUsersExist_shouldReturnEmptyList() {
        
        when(userRepository.findAll()).thenReturn(Collections.emptyList());


        List<UserMessageDTO> result = processUserUseCase.getAllUsers();


        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userRepository, times(1)).findAll();
        verify(entityToUserMessageDtoMapper, never()).convert(any());
    }

    @Test
    public void getUser_whenUserExists_shouldReturnUserMessageDTO() {
        
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(entityToUserMessageDtoMapper.convert(userEntity)).thenReturn(userMessageDTO);


        UserMessageDTO result = processUserUseCase.getUser(email);


        assertNotNull(result);
        assertEquals(userMessageDTO.getId(), result.getId());

        verify(userRepository, times(1)).findByEmail(email);
        verify(entityToUserMessageDtoMapper, times(1)).convert(userEntity);
    }

    @Test
    public void getUser_whenUserDoesNotExist_shouldReturnNull() {
        
        String email = "notfound@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);
        when(entityToUserMessageDtoMapper.convert(null)).thenReturn(null);

        UserMessageDTO result = processUserUseCase.getUser(email);

        assertNull(result);

        verify(userRepository, times(1)).findByEmail(email);
        verify(entityToUserMessageDtoMapper, times(1)).convert(null);
    }
}