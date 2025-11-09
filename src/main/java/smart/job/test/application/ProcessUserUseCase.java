package smart.job.test.application;

import org.apache.tomcat.util.file.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import smart.job.test.domain.in.UserInputPort;
import smart.job.test.domain.model.UserDTO;
import smart.job.test.domain.model.UserMessageDTO;
import smart.job.test.domain.model.exceptions.DuplicatedMailException;
import smart.job.test.domain.model.exceptions.PwdNotMatchesException;
import smart.job.test.infrastructure.model.Constants;
import smart.job.test.infrastructure.out.repository.crud.UserRepository;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;
import smart.job.test.infrastructure.out.repository.mapper.UserDtoToEntityMapper;
import smart.job.test.infrastructure.out.repository.mapper.UserEntityToUserMessageDtoMapper;

@Component
public class ProcessUserUseCase implements UserInputPort {

    @Value("${config.regex.pwd}")
    public String pwdRequirements;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDtoToEntityMapper dtoToEntityMapper;

    @Autowired
    UserEntityToUserMessageDtoMapper entityToUserMessageDtoMapper;

    @Override
    public UserMessageDTO insertUser(UserDTO userDTO) {
        try {
            if (!Matcher.match(pwdRequirements, userDTO.getPassword(), false)) {
                throw new PwdNotMatchesException(Constants.PWD_INVALID_TYPE_ERROR);
            }

            UserEntity userEntity = dtoToEntityMapper.convert(userDTO);

            assert userEntity != null;
            userRepository.save(userEntity);

            return entityToUserMessageDtoMapper.convert(userEntity);

        } catch (Exception ex) {
            throw new DuplicatedMailException(Constants.DUPLICATED_MAIL_ERROR);
        }
    }

}
