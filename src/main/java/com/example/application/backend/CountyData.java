
package com.example.application.backend;

import java.util.HashMap;
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
    "county_id",
    "county_name",
    "county_population",
    "total_cases"
})
@Component
public class CountyData {

    @JsonProperty("county_id")
    private String countyId;
    @JsonProperty("county_name")
    private String countyName;
    @JsonProperty("county_population")
    private Integer countyPopulation;
    @JsonProperty("total_cases")
    private Integer totalCases;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("county_id")
    public String getCountyId() {
        return countyId;
    }

    @JsonProperty("county_id")
    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    @JsonProperty("county_name")
    public String getCountyName() {
        return countyName;
    }

    @JsonProperty("county_name")
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @JsonProperty("county_population")
    public Integer getCountyPopulation() {
        return countyPopulation;
    }

    @JsonProperty("county_population")
    public void setCountyPopulation(Integer countyPopulation) {
        this.countyPopulation = countyPopulation;
    }

    @JsonProperty("total_cases")
    public Integer getTotalCases() {
        return totalCases;
    }

    @JsonProperty("total_cases")
    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
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
        return "CountyData{" +
                "countyId='" + countyId + '\'' +
                ", countyName='" + countyName + '\'' +
                ", countyPopulation=" + countyPopulation +
                ", totalCases=" + totalCases +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
