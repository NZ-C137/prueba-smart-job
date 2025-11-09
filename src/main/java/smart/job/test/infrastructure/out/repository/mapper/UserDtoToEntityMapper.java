package smart.job.test.infrastructure.out.repository.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import smart.job.test.domain.model.UserDTO;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;

@Component
public class UserDtoToEntityMapper implements Converter<UserDTO, UserEntity> {

    @Override
    public UserEntity convert(UserDTO source) {
        UserEntity entity = new UserEntity();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(source, entity);
        return entity;
    }

}
