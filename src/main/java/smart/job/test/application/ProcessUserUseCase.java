package smart.job.test.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import smart.job.test.domain.in.UserInputPort;
import smart.job.test.domain.model.UserDTO;
import smart.job.test.domain.model.UserMessageDTO;
import smart.job.test.domain.model.exceptions.DefaultException;
import smart.job.test.domain.model.exceptions.PwdNotMatchesException;
import smart.job.test.infrastructure.model.Constants;
import smart.job.test.infrastructure.out.repository.crud.PhoneRepository;
import smart.job.test.infrastructure.out.repository.crud.UserRepository;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;
import smart.job.test.infrastructure.out.repository.mapper.UserDtoToEntityMapper;
import smart.job.test.infrastructure.out.repository.mapper.UserEntityToUserMessageDtoMapper;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class ProcessUserUseCase implements UserInputPort {

    @Value("${config.regex.pwd}")
    public String pwdRequirements;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UserDtoToEntityMapper dtoToEntityMapper;

    @Autowired
    UserEntityToUserMessageDtoMapper entityToUserMessageDtoMapper;

    @Override
    public UserMessageDTO insertUser(UserDTO userDTO) {
        if (!Pattern.matches(pwdRequirements, userDTO.getPassword())) {
            throw new PwdNotMatchesException(Constants.PWD_INVALID_TYPE_ERROR);
        }
        try {
            UserEntity userEntity = dtoToEntityMapper.convert(userDTO);
            assert userEntity != null;
            userRepository.save(userEntity);
            return entityToUserMessageDtoMapper.convert(userEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        } catch (Exception ex){
            throw new DefaultException(ex.getMessage());
        }
    }

    @Override
    public List<UserMessageDTO> getAllUsers() {
        List<UserEntity> entityList = userRepository.findAll();
        return entityList.stream().map(user -> entityToUserMessageDtoMapper.convert(user)).toList();
    }

    @Override
    public UserMessageDTO getUser(String email) {
        return entityToUserMessageDtoMapper.convert(userRepository.findByEmail(email));
    }

}
