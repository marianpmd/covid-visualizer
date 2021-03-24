package com.example.application.backend;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@SpringComponent
@UIScope
public class CovidDataService {

    private static final String COVID_DATA_CSV_CONFIRMED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String COVID_DATA_CSV_DEATHS = "https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static final String COVID_DATA_CSV_RECOVERED = "https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<CovidData> allStats = new ArrayList<>();


    /*locationStat.setLatestTotalCases(Integer.parseInt( record.get(record.size()-1)));*/

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<CovidData> newStats = new ArrayList<>();

        Iterable<CSVRecord> recordsConfirmed = getRecordData(COVID_DATA_CSV_CONFIRMED);

        for (CSVRecord record : recordsConfirmed) {
           CovidData locationStat = new CovidData();
           String current = record.get("Country/Region");
           String next = record.get("Country/Region");

           if (!current.equals(next)){
               System.out.println(current);

           }



            //locationStat.setCountry(record.get("Country/Region"));

            newStats.add(locationStat);
        }
        this.allStats = newStats;

    }

    private Iterable<CSVRecord> getRecordData(String URItype) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URItype))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());
        return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    }

    public List<CovidData> getAllStats() {
        return allStats;
    }

}
