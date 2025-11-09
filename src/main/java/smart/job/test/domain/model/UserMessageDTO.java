package smart.job.test.domain.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class UserMessageDTO {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<PhonesDTO> phones;
    private Date created;
    private Date modified;
    @JsonAlias("last_login")
    private Date lastLogin;
    private UUID token;
    @JsonAlias("isactive")
    private Boolean isActive;

    public UserMessageDTO() {
    }

    public UserMessageDTO(UUID id, String name, String email, String password, List<PhonesDTO> phones, Date created, Date modified, Date lastLogin, UUID token, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        return phones;
    }

    public void setPhones(List<PhonesDTO> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
