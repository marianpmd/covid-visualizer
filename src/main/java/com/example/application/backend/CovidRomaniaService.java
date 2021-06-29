package com.example.application.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class CovidRomaniaService {

    private List<CovidRomaniaData> data;
    private CovidRomaniaData recent;

    //TODO : This method runs for each request which is bad , it may be better to persist the data whenever the server is started
    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //new URL("https://www.graphs.ro/json.php")
        CovidRomaniaPOJO pojo = mapper.readValue(new File("JSONRoTempData.json"), CovidRomaniaPOJO.class);

        data = pojo.getCovidRomania();
        recent = data.get(0);

        System.out.println("The most recent data as of :" + recent.getReportingDate());
    }

    public String latestReportedDate() {
        return data.get(0).getReportingDate();
    }

    public int getTotalDeaths() {

        return recent.getTotalDeaths();
    }

    public int getTotalCases() {
        return recent.getTotalCases();
    }

    public int getTotalRecovered() {
        return recent.getTotalRecovered();
    }

    public String getTotalPopulation() {
        return recent.getRomaniaPopulation2020();
    }

    public List<String> getCountyNames() {
        return recent.getCountyData().stream()
                .map(CountyData::getCountyName)
                .collect(Collectors.toList());
    }

    public CountyData getCountyByName(String name) {
        for (CountyData countyData : recent.getCountyData()) {
            if (countyData.getCountyName().equals(name)) {
                return countyData;
            }
        }
        return null;
    }


}
