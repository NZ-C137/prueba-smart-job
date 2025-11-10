package smart.job.test.infrastructure.out.repository.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones = new ArrayList<>();
    @Column(name = "created")
    private Date created;
    @Column(name = "modified")
    private Date modified;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "token")
    private UUID token;
    @Column(name = "is_active")
    private Boolean isActive;

    public UserEntity() {
    }

    public UserEntity(UUID id, String name, String email, String password, List<PhoneEntity> phones, Date created, Date modified, Date lastLogin, UUID token, Boolean isActive) {
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

    @PrePersist
    public void onCreate(){
        this.created = new Date(System.currentTimeMillis());
        this.lastLogin = new Date(System.currentTimeMillis());
        this.isActive = Boolean.TRUE;
        this.token = UUID.randomUUID();
    }

    @PreUpdate
    public void onUpdate(){
        this.modified = new Date(System.currentTimeMillis());
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

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> phones) {
        phones.forEach(phone->phone.setUser(this));
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
        this.isActive = active;
    }
}
