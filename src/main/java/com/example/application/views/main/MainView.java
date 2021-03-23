package com.example.application.views.main;


import com.example.application.backend.CovidDataService;
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.Chart;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.plotoptions.pie.builder.DonutBuilder;
import com.github.appreciated.apexcharts.config.plotoptions.pie.builder.LabelsBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "covid-visualizer", shortName = "covid-visualizer", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("./views/main/main-view.css")
@Route("")
public class MainView extends VerticalLayout {
    @Autowired
    private CovidDataService data;

    public MainView() {
        ApexCharts chart = ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.donut)
                        .withHeight("250px")
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(true)
                                .build())
                        .build())
                .withLegend(LegendBuilder.get()
                        .withShow(true)
                        .build())
                .build();

        chart.setLabels("Romania","Label2");
        chart.setSeries(50.0,34.0);


        add(new Label("asdbafljkasb"), chart);


    }


}
