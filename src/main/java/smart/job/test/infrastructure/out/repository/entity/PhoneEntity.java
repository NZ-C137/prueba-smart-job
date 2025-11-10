package smart.job.test.infrastructure.out.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneEntity {

    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity user;

    public PhoneEntity() {
    }

    public PhoneEntity(String number, String cityCode, String countryCode, UserEntity user) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
