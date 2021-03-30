package com.example.application.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class CovidRomaniaService {

    private CovidRomaniaPOJO pojo;
    private List<CovidRomaniaData> data;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //new URL("https://www.graphs.ro/json.php")
        pojo = mapper.readValue(new File("JSONRoTempData.json"), CovidRomaniaPOJO.class);
        //System.out.println(pojo);
        data = pojo.getCovidRomania();

        System.out.println( "The most recent data as of :" + LocalDateTime.now() +getRecentDateData().toString());
    }

    public CovidRomaniaData getRecentDateData(){
        return data.get(0);
    }
    public String latestReportedDate(){
        return data.get(0).getReportingDate();
    }

    public int getTotalDeaths(){
        CovidRomaniaData recent = getRecentDateData();
        return recent.getTotalDeaths();
    }

    public int getTotalCases(){
        CovidRomaniaData recent = getRecentDateData();
        return recent.getTotalCases();
    }
    public int getTotalRecovered(){
        CovidRomaniaData recent = getRecentDateData();
        return recent.getTotalRecovered();
    }

    public String getTotalPopulation(){
        CovidRomaniaData recent = getRecentDateData();
        return recent.getRomaniaPopulation2020();
    }

    public List<String> getCountyNames(){
        CovidRomaniaData recent = getRecentDateData();
        return recent.getCountyData().stream()
                .map(CountyData::getCountyName)
                .collect(Collectors.toList());
    }

    public CountyData getCountyByName(String name){
        CovidRomaniaData recent = getRecentDateData();
        for (CountyData countyData : recent.getCountyData()){
            if (countyData.getCountyName().equals(name)){
                return countyData;
            }
        }
        return null;
    }




}
