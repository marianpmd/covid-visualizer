package com.example.application.views.main;


import com.example.application.backend.CovidDataService;
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@PWA(name = "covid-visualizer", shortName = "covid-visualizer", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("./views/main/main-view.css")
@Route("")
@UIScope
public class MainView extends VerticalLayout {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private final Label title = new Label("Covid Donut Chart");

    public MainView(@Autowired CovidDataService data) {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.setSizeFull();
        ComboBox<String> countries = new ComboBox<>();
        countries.setPlaceholder("Pick a country ... ");

        countries.setItems(data.getCountryNames());
        HorizontalLayout temp = new HorizontalLayout();


        countries.addValueChangeListener(listener ->
        {
            updateChart(temp, listener.getValue(), data);
        });


        verticalLayout.add(countries, temp);

        add(title, verticalLayout);


    }


    private void updateChart(HorizontalLayout temp, String value, CovidDataService data) {
        temp.removeAll();
        int index = data.getCountryDataByName(value);

        ApexCharts chart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.donut)
                        .withHeight("600px")
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(true)
                                .build())
                        .build())
                .withColors("#e8aa35", "#FF0000", "#00FF00")
                .withLegend(LegendBuilder.get()
                        .withShow(true)
                        .build())
                .build();


        chart.setLabels("Confirmed", "Deaths", "Recovered");
        chart.setTitle(TitleSubtitleBuilder.get()
                .withText(data.getAllStats().get(index).getCountry())
                .withAlign(Align.center)
                .build());
        chart.setSeries(Double.valueOf(data.getAllStats().get(index).getConfirmed()), Double.valueOf(data.getAllStats().get(index).getDeaths()), Double.valueOf(data.getAllStats().get(index).getRecovered()));
        temp.add(chart);

        verticalLayout.add(temp);
    }


}
