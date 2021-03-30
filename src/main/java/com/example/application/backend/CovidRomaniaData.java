
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
    "reporting_date",
    "total_cases",
    "new_cases_today",
    "total_tests",
    "new_tests_today",
    "total_deaths",
    "new_deaths_today",
    "total_recovered",
    "new_recovered_today",
    "intensive_care_right_now",
    "emergency_calls",
    "information_calls",
    "persons_in_quarantine",
    "persons_in_home_isolation",
    "tests_for_case_definition",
    "tests_upon_request",
    "tests_done_before_today_and_reported_today",
    "rapid_tests",
    "infected_asymptomatic",
    "infected_hospitalized",
    "infected_positive_retests",
    "persons_in_institutional_isolation",
    "persons_in_home_quarantine",
    "persons_in_institutional_quarantine",
    "romania_population_2020",
    "sourceUrl",
    "county_data"
})
@Component
public class CovidRomaniaData {

    @JsonProperty("reporting_date")
    private String reportingDate;
    @JsonProperty("total_cases")
    private Integer totalCases;
    @JsonProperty("new_cases_today")
    private Integer newCasesToday;
    @JsonProperty("total_tests")
    private Integer totalTests;
    @JsonProperty("new_tests_today")
    private Integer newTestsToday;
    @JsonProperty("total_deaths")
    private Integer totalDeaths;
    @JsonProperty("new_deaths_today")
    private Integer newDeathsToday;
    @JsonProperty("total_recovered")
    private Integer totalRecovered;
    @JsonProperty("new_recovered_today")
    private Integer newRecoveredToday;
    @JsonProperty("intensive_care_right_now")
    private Integer intensiveCareRightNow;
    @JsonProperty("emergency_calls")
    private Integer emergencyCalls;
    @JsonProperty("information_calls")
    private Object informationCalls;
    @JsonProperty("persons_in_quarantine")
    private Object personsInQuarantine;
    @JsonProperty("persons_in_home_isolation")
    private Integer personsInHomeIsolation;
    @JsonProperty("tests_for_case_definition")
    private Integer testsForCaseDefinition;
    @JsonProperty("tests_upon_request")
    private Integer testsUponRequest;
    @JsonProperty("tests_done_before_today_and_reported_today")
    private Object testsDoneBeforeTodayAndReportedToday;
    @JsonProperty("rapid_tests")
    private Integer rapidTests;
    @JsonProperty("infected_asymptomatic")
    private Object infectedAsymptomatic;
    @JsonProperty("infected_hospitalized")
    private Integer infectedHospitalized;
    @JsonProperty("infected_positive_retests")
    private Integer infectedPositiveRetests;
    @JsonProperty("persons_in_institutional_isolation")
    private Integer personsInInstitutionalIsolation;
    @JsonProperty("persons_in_home_quarantine")
    private Integer personsInHomeQuarantine;
    @JsonProperty("persons_in_institutional_quarantine")
    private Integer personsInInstitutionalQuarantine;
    @JsonProperty("romania_population_2020")
    private String romaniaPopulation2020;
    @JsonProperty("sourceUrl")
    private String sourceUrl;
    @JsonProperty("county_data")
    private List<CountyData> countyData = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reporting_date")
    public String getReportingDate() {
        return reportingDate;
    }

    @JsonProperty("reporting_date")
    public void setReportingDate(String reportingDate) {
        this.reportingDate = reportingDate;
    }

    @JsonProperty("total_cases")
    public Integer getTotalCases() {
        return totalCases;
    }

    @JsonProperty("total_cases")
    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    @JsonProperty("new_cases_today")
    public Integer getNewCasesToday() {
        return newCasesToday;
    }

    @JsonProperty("new_cases_today")
    public void setNewCasesToday(Integer newCasesToday) {
        this.newCasesToday = newCasesToday;
    }

    @JsonProperty("total_tests")
    public Integer getTotalTests() {
        return totalTests;
    }

    @JsonProperty("total_tests")
    public void setTotalTests(Integer totalTests) {
        this.totalTests = totalTests;
    }

    @JsonProperty("new_tests_today")
    public Integer getNewTestsToday() {
        return newTestsToday;
    }

    @JsonProperty("new_tests_today")
    public void setNewTestsToday(Integer newTestsToday) {
        this.newTestsToday = newTestsToday;
    }

    @JsonProperty("total_deaths")
    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    @JsonProperty("total_deaths")
    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    @JsonProperty("new_deaths_today")
    public Integer getNewDeathsToday() {
        return newDeathsToday;
    }

    @JsonProperty("new_deaths_today")
    public void setNewDeathsToday(Integer newDeathsToday) {
        this.newDeathsToday = newDeathsToday;
    }

    @JsonProperty("total_recovered")
    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    @JsonProperty("total_recovered")
    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    @JsonProperty("new_recovered_today")
    public Integer getNewRecoveredToday() {
        return newRecoveredToday;
    }

    @JsonProperty("new_recovered_today")
    public void setNewRecoveredToday(Integer newRecoveredToday) {
        this.newRecoveredToday = newRecoveredToday;
    }

    @JsonProperty("intensive_care_right_now")
    public Integer getIntensiveCareRightNow() {
        return intensiveCareRightNow;
    }

    @JsonProperty("intensive_care_right_now")
    public void setIntensiveCareRightNow(Integer intensiveCareRightNow) {
        this.intensiveCareRightNow = intensiveCareRightNow;
    }

    @JsonProperty("emergency_calls")
    public Integer getEmergencyCalls() {
        return emergencyCalls;
    }

    @JsonProperty("emergency_calls")
    public void setEmergencyCalls(Integer emergencyCalls) {
        this.emergencyCalls = emergencyCalls;
    }

    @JsonProperty("information_calls")
    public Object getInformationCalls() {
        return informationCalls;
    }

    @JsonProperty("information_calls")
    public void setInformationCalls(Object informationCalls) {
        this.informationCalls = informationCalls;
    }

    @JsonProperty("persons_in_quarantine")
    public Object getPersonsInQuarantine() {
        return personsInQuarantine;
    }

    @JsonProperty("persons_in_quarantine")
    public void setPersonsInQuarantine(Object personsInQuarantine) {
        this.personsInQuarantine = personsInQuarantine;
    }

    @JsonProperty("persons_in_home_isolation")
    public Integer getPersonsInHomeIsolation() {
        return personsInHomeIsolation;
    }

    @JsonProperty("persons_in_home_isolation")
    public void setPersonsInHomeIsolation(Integer personsInHomeIsolation) {
        this.personsInHomeIsolation = personsInHomeIsolation;
    }

    @JsonProperty("tests_for_case_definition")
    public Integer getTestsForCaseDefinition() {
        return testsForCaseDefinition;
    }

    @JsonProperty("tests_for_case_definition")
    public void setTestsForCaseDefinition(Integer testsForCaseDefinition) {
        this.testsForCaseDefinition = testsForCaseDefinition;
    }

    @JsonProperty("tests_upon_request")
    public Integer getTestsUponRequest() {
        return testsUponRequest;
    }

    @JsonProperty("tests_upon_request")
    public void setTestsUponRequest(Integer testsUponRequest) {
        this.testsUponRequest = testsUponRequest;
    }

    @JsonProperty("tests_done_before_today_and_reported_today")
    public Object getTestsDoneBeforeTodayAndReportedToday() {
        return testsDoneBeforeTodayAndReportedToday;
    }

    @JsonProperty("tests_done_before_today_and_reported_today")
    public void setTestsDoneBeforeTodayAndReportedToday(Object testsDoneBeforeTodayAndReportedToday) {
        this.testsDoneBeforeTodayAndReportedToday = testsDoneBeforeTodayAndReportedToday;
    }

    @JsonProperty("rapid_tests")
    public Integer getRapidTests() {
        return rapidTests;
    }

    @JsonProperty("rapid_tests")
    public void setRapidTests(Integer rapidTests) {
        this.rapidTests = rapidTests;
    }

    @JsonProperty("infected_asymptomatic")
    public Object getInfectedAsymptomatic() {
        return infectedAsymptomatic;
    }

    @JsonProperty("infected_asymptomatic")
    public void setInfectedAsymptomatic(Object infectedAsymptomatic) {
        this.infectedAsymptomatic = infectedAsymptomatic;
    }

    @JsonProperty("infected_hospitalized")
    public Integer getInfectedHospitalized() {
        return infectedHospitalized;
    }

    @JsonProperty("infected_hospitalized")
    public void setInfectedHospitalized(Integer infectedHospitalized) {
        this.infectedHospitalized = infectedHospitalized;
    }

    @JsonProperty("infected_positive_retests")
    public Integer getInfectedPositiveRetests() {
        return infectedPositiveRetests;
    }

    @JsonProperty("infected_positive_retests")
    public void setInfectedPositiveRetests(Integer infectedPositiveRetests) {
        this.infectedPositiveRetests = infectedPositiveRetests;
    }

    @JsonProperty("persons_in_institutional_isolation")
    public Integer getPersonsInInstitutionalIsolation() {
        return personsInInstitutionalIsolation;
    }

    @JsonProperty("persons_in_institutional_isolation")
    public void setPersonsInInstitutionalIsolation(Integer personsInInstitutionalIsolation) {
        this.personsInInstitutionalIsolation = personsInInstitutionalIsolation;
    }

    @JsonProperty("persons_in_home_quarantine")
    public Integer getPersonsInHomeQuarantine() {
        return personsInHomeQuarantine;
    }

    @JsonProperty("persons_in_home_quarantine")
    public void setPersonsInHomeQuarantine(Integer personsInHomeQuarantine) {
        this.personsInHomeQuarantine = personsInHomeQuarantine;
    }

    @JsonProperty("persons_in_institutional_quarantine")
    public Integer getPersonsInInstitutionalQuarantine() {
        return personsInInstitutionalQuarantine;
    }

    @JsonProperty("persons_in_institutional_quarantine")
    public void setPersonsInInstitutionalQuarantine(Integer personsInInstitutionalQuarantine) {
        this.personsInInstitutionalQuarantine = personsInInstitutionalQuarantine;
    }

    @JsonProperty("romania_population_2020")
    public String getRomaniaPopulation2020() {
        return romaniaPopulation2020;
    }

    @JsonProperty("romania_population_2020")
    public void setRomaniaPopulation2020(String romaniaPopulation2020) {
        this.romaniaPopulation2020 = romaniaPopulation2020;
    }

    @JsonProperty("sourceUrl")
    public String getSourceUrl() {
        return sourceUrl;
    }

    @JsonProperty("sourceUrl")
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @JsonProperty("county_data")
    public List<CountyData> getCountyData() {
        return countyData;
    }

    @JsonProperty("county_data")
    public void setCountyData(List<CountyData> countyData) {
        this.countyData = countyData;
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
        return "CovidRomaniaData{" +
                "reportingDate='" + reportingDate + '\'' +
                ", totalCases=" + totalCases +
                ", newCasesToday=" + newCasesToday +
                ", totalTests=" + totalTests +
                ", newTestsToday=" + newTestsToday +
                ", totalDeaths=" + totalDeaths +
                ", newDeathsToday=" + newDeathsToday +
                ", totalRecovered=" + totalRecovered +
                ", newRecoveredToday=" + newRecoveredToday +
                ", intensiveCareRightNow=" + intensiveCareRightNow +
                ", emergencyCalls=" + emergencyCalls +
                ", informationCalls=" + informationCalls +
                ", personsInQuarantine=" + personsInQuarantine +
                ", personsInHomeIsolation=" + personsInHomeIsolation +
                ", testsForCaseDefinition=" + testsForCaseDefinition +
                ", testsUponRequest=" + testsUponRequest +
                ", testsDoneBeforeTodayAndReportedToday=" + testsDoneBeforeTodayAndReportedToday +
                ", rapidTests=" + rapidTests +
                ", infectedAsymptomatic=" + infectedAsymptomatic +
                ", infectedHospitalized=" + infectedHospitalized +
                ", infectedPositiveRetests=" + infectedPositiveRetests +
                ", personsInInstitutionalIsolation=" + personsInInstitutionalIsolation +
                ", personsInHomeQuarantine=" + personsInHomeQuarantine +
                ", personsInInstitutionalQuarantine=" + personsInInstitutionalQuarantine +
                ", romaniaPopulation2020='" + romaniaPopulation2020 + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", countyData=" + countyData +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
