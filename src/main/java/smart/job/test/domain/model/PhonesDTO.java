package smart.job.test.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhonesDTO {

    @NotNull
    @JsonProperty("number")
    private String number;
    @NotNull
    @JsonProperty("citycode")
    private String cityCode;
    @NotNull
    @JsonProperty("contrycode") //This should be "country" but the example says "contry"
    private String countryCode;

    public PhonesDTO() {
    }

    public PhonesDTO(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
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
}
