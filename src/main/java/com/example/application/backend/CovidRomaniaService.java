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
        Optional<CovidRomaniaData> recent = data.stream()
                .filter(rona -> rona.getReportingDate().equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .findFirst();
        return recent.orElseThrow();
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




}
