package smart.job.test.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import smart.job.test.infrastructure.model.Constants;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotNull
    @NotBlank
    @JsonProperty("email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = Constants.EMAIL_INVALID_TYPE_ERROR)
    private String email;

    @NotNull
    @NotBlank
    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private @Valid List<PhonesDTO> phoneList;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, List<PhonesDTO> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneList = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhonesDTO> getPhones() {
        return phoneList;
    }

    public void setPhones(List<PhonesDTO> phones) {
        this.phoneList = phones;
    }
}
