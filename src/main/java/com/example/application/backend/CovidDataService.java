package com.example.application.backend;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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
import java.util.List;
import java.util.stream.Collectors;


@SpringComponent
@UIScope
public class CovidDataService {

    private static final String COVID_DATA_CSV_CONFIRMED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String COVID_DATA_CSV_DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static final String COVID_DATA_CSV_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<CovidData> allStats = new ArrayList<>();


    @PostConstruct
    @Scheduled(cron = "1 1 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<CovidData> newStats = new ArrayList<>();

        CSVParser recordsConfirmed = getRecordData(COVID_DATA_CSV_CONFIRMED);
        CSVParser recordsDeath = getRecordData(COVID_DATA_CSV_DEATHS);
        CSVParser recordsRecovered = getRecordData(COVID_DATA_CSV_RECOVERED);

        List<CSVRecord> recordList = recordsConfirmed.getRecords();
        List<CSVRecord> recordDeathList = recordsDeath.getRecords();
        List<CSVRecord> recordRecoveredList = recordsRecovered.getRecords();

        for (int i = 0; i < recordList.size() - 1; i++) {

            String country = recordList.get(i).get("Country/Region");
            int confirmed = Integer.parseInt(recordList.get(i).get(recordsConfirmed.getHeaderNames().size() - 1));
            int deaths = Integer.parseInt(recordDeathList.get(i).get(recordsConfirmed.getHeaderNames().size() - 1));
            int recovered = 0;
            if (recordRecoveredList.size() > i) {
                recovered = Integer.parseInt(recordRecoveredList.get(i).get(recordsConfirmed.getHeaderNames().size() - 1));
            }


            /*int k = i;*/

            while (recordList.get(i).get("Country/Region").equals(recordList.get(i + 1).get("Country/Region"))) {
                confirmed += Integer.parseInt(recordList.get(i + 1).get(recordsConfirmed.getHeaderNames().size() - 1));
                deaths = Integer.parseInt(recordDeathList.get(i + 1).get(recordsConfirmed.getHeaderNames().size() - 1));
                if (recordRecoveredList.size() > i+1) {
                    recovered = Integer.parseInt(recordRecoveredList.get(i+1).get(recordsConfirmed.getHeaderNames().size() - 1));
                }
                i++;
            }

            CovidData covidData = new CovidData();
            covidData.setCountry(country);
            covidData.setConfirmed(confirmed);
            covidData.setDeaths(deaths);
            covidData.setRecovered(recovered);
            System.out.println(covidData.toString());
            newStats.add(covidData);
        }

        this.allStats = newStats;

    }

    private CSVParser getRecordData(String URItype) throws IOException, InterruptedException {

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

    public List<String> getCountryNames(){
        return allStats.stream()
                .map(CovidData::getCountry)
                .collect(Collectors.toList());

    }

    public int getCountryDataByName(String value) {
        for (CovidData data : allStats){
            if (data.getCountry().equals(value)){
                return allStats.indexOf(data);
            }
        }
        return 0;

    }
}
