package smart.job.test.infrastructure.out.repository.mapper;

import org.junit.Before;
import org.junit.Test;
import smart.job.test.domain.model.PhonesDTO;
import smart.job.test.domain.model.UserDTO;
import smart.job.test.infrastructure.out.repository.entity.UserEntity;

import java.util.Collections;

import static org.junit.Assert.*;

public class UserDtoToEntityMapperTest {

    private UserDtoToEntityMapper userDtoToEntityMapper;

    @Before
    public void setUp() {
        userDtoToEntityMapper = new UserDtoToEntityMapper();
    }

    @Test
    public void convert_whenFullUserDto_shouldMapAllFieldsToEntity() {
        PhonesDTO phoneDto = new PhonesDTO("1234567", "1", "57");
        UserDTO sourceDto = new UserDTO(
                "Juan Perez",
                "juan.perez@example.com",
                "aSecurePassword",
                Collections.singletonList(phoneDto)
        );

        UserEntity resultEntity = userDtoToEntityMapper.convert(sourceDto);

        assertNotNull(resultEntity);
        assertEquals(sourceDto.getName(), resultEntity.getName());
        assertEquals(sourceDto.getEmail(), resultEntity.getEmail());
        assertEquals(sourceDto.getPassword(), resultEntity.getPassword());

        assertNotNull(resultEntity.getPhones());
        assertFalse(resultEntity.getPhones().isEmpty());
        assertEquals(1, resultEntity.getPhones().size());
        assertEquals(phoneDto.getNumber(), resultEntity.getPhones().get(0).getNumber());
        assertEquals(phoneDto.getCityCode(), resultEntity.getPhones().get(0).getCityCode());
        assertEquals(phoneDto.getCountryCode(), resultEntity.getPhones().get(0).getCountryCode());
    }

    @Test
    public void convert_whenPartialUserDto_shouldSkipNullFields() {

        UserDTO sourceDto = new UserDTO();
        sourceDto.setName("Maria Rodriguez");
        sourceDto.setEmail(null);
        sourceDto.setPassword(null);
        sourceDto.setPhones(null);

        UserEntity resultEntity = userDtoToEntityMapper.convert(sourceDto);

        assertNotNull(resultEntity);
        assertEquals("Maria Rodriguez", resultEntity.getName());
        assertNull(resultEntity.getEmail());
        assertNull(resultEntity.getPassword());
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_whenSourceDtoIsNull_shouldThrowIllegalArgumentException() {
        UserDTO sourceDto = null;
        userDtoToEntityMapper.convert(sourceDto);
    }
}