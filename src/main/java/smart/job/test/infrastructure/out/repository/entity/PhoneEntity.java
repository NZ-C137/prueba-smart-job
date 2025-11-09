package smart.job.test.infrastructure.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "phones")
public class PhoneEntity {

    @Column(name = "number")
    private Long number;
    @Column(name = "city_code")
    private int cityCode;
    @Column(name = "country_code")
    private int countryCode;

}
