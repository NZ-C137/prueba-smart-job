package smart.job.test.infrastructure.out.repository.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import smart.job.test.domain.model.UserMessageDTO;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;

@Component
public class UserEntityToUserMessageDtoMapper implements Converter<UserEntity, UserMessageDTO> {

    @Override
    public UserMessageDTO convert(UserEntity source) {
        ModelMapper mapper = new ModelMapper();
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        mapper.map(source, userMessageDTO);
        return userMessageDTO;
    }



}
