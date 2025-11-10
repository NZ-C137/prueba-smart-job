package smart.job.test.infrastructure.out.repository.mapper;

import org.junit.Before;
import org.junit.Test;
import smart.job.test.domain.model.UserMessageDTO;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserEntityToUserMessageDtoMapperTest {

    private UserEntityToUserMessageDtoMapper userEntityToUserMessageDtoMapper;

    @Before
    public void setUp() {
        userEntityToUserMessageDtoMapper = new UserEntityToUserMessageDtoMapper();
    }

    @Test
    public void convert_whenFullUserEntity_shouldMapAllFieldsToDto() {
        UserEntity sourceEntity = new UserEntity();
        sourceEntity.setId(UUID.randomUUID());
        sourceEntity.setName("Test User");
        sourceEntity.setEmail("test@example.com");
        sourceEntity.setCreated(new Date(Instant.now().toEpochMilli()));
        sourceEntity.setModified(new Date(Instant.now().toEpochMilli()));
        sourceEntity.setLastLogin(new Date(Instant.now().toEpochMilli()));
        sourceEntity.setToken(UUID.randomUUID());
        sourceEntity.setActive(true);

        UserMessageDTO resultDto = userEntityToUserMessageDtoMapper.convert(sourceEntity);

        assertNotNull(resultDto);
        assertEquals(sourceEntity.getId(), resultDto.getId());
        assertEquals(sourceEntity.getCreated(), resultDto.getCreated());
        assertEquals(sourceEntity.getModified(), resultDto.getModified());
        assertEquals(sourceEntity.getLastLogin(), resultDto.getLastLogin());
        assertEquals(sourceEntity.getToken(), resultDto.getToken());
        assertEquals(sourceEntity.getActive(), resultDto.getActive());
    }

    @Test
    public void convert_whenEntityHasNullFields_shouldMapNullsToDto() {
        UserEntity sourceEntity = new UserEntity();
        sourceEntity.setId(UUID.randomUUID());
        sourceEntity.setCreated(null);
        sourceEntity.setLastLogin(new Date(Instant.now().toEpochMilli()));
        sourceEntity.setToken(null);
        sourceEntity.setActive(false);

        UserMessageDTO resultDto = userEntityToUserMessageDtoMapper.convert(sourceEntity);

        assertNotNull(resultDto);
        assertEquals(sourceEntity.getId(), resultDto.getId());
        assertNull(resultDto.getCreated());
        assertNotNull(resultDto.getLastLogin());
        assertNull(resultDto.getToken());
        assertEquals(sourceEntity.getActive(), resultDto.getActive());
    }
}