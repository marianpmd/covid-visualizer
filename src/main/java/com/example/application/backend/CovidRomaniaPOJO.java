
package com.example.application.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "covid_romania"
})
@Component
public class CovidRomaniaPOJO {

    @JsonProperty("covid_romania")
    private List<CovidRomaniaData> covidRomania = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("covid_romania")
    public List<CovidRomaniaData> getCovidRomania() {
        return covidRomania;
    }

    @JsonProperty("covid_romania")
    public void setCovidRomania(List<CovidRomaniaData> covidRomania) {
        this.covidRomania = covidRomania;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "CovidRomaniaPOJO{" +
                "covidRomania=" + covidRomania +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
