package com.example.application.views.main;


import com.example.application.backend.CountyData;
import com.example.application.backend.CovidDataService;
import com.example.application.backend.CovidRomaniaService;
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.config.subtitle.builder.StyleBuilder;
import com.github.appreciated.apexcharts.config.tooltip.Z;
import com.github.appreciated.apexcharts.config.tooltip.builder.FixedBuilder;
import com.github.appreciated.apexcharts.config.tooltip.builder.ZBuilder;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Footer;
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
    private VerticalLayout verticalLayoutCounty = new VerticalLayout();
    private final Label title = new Label("Global Donut Chart");

    public MainView(@Autowired CovidDataService data ,
                    @Autowired CovidRomaniaService romaniaData) {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayoutCounty.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        ComboBox<String> countries = new ComboBox<>();
        countries.setPlaceholder("Pick a country ... ");

        countries.setItems(data.getCountryNames().stream().filter(country -> !country.equals("Romania")));

        VerticalLayout temp = new VerticalLayout();
        temp.setWidth("350px");


        countries.addValueChangeListener(listener ->
        {
            updateChart(temp, listener.getValue(), data);
        });


        verticalLayout.add(countries, temp);

        VerticalLayout roTotalLayout = new VerticalLayout();
        roTotalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        roTotalLayout.setWidth("360px");
        roTotalLayout.add(romanianTotalDataChart(romaniaData));
        roTotalLayout.add(getLabelData(romaniaData));

        Label label = new Label("Romanian Recent Data");

        add(title, verticalLayout , label,roTotalLayout);


        VerticalLayout withPopulation = new VerticalLayout();
        withPopulation.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        withPopulation.setWidth("360px");
        withPopulation.add(romanianTotalDataWithPopulationChart(romaniaData));
        Label population = new Label("Population : " + romaniaData.getTotalPopulation());
        population.getElement().getStyle().set("font-size","12px");
        population.getElement().getStyle().set("color","#FFFFFF");
        population.getElement().getStyle().set("border-radius","8px");
        population.getElement().getStyle().set("background","#66a8ff");
        withPopulation.add();
        withPopulation.add(population,getLabelData(romaniaData));
        add(withPopulation);


        Label county = new Label("Data By County");
        ComboBox<String> counties = new ComboBox<>();
        counties.setPlaceholder("Pick a county ... ");
        counties.setItems(romaniaData.getCountyNames());


        VerticalLayout tempCounty = new VerticalLayout();
        tempCounty.setWidth("360px");

        counties.addValueChangeListener(listener ->
        {
            updateCountyChart(tempCounty, listener.getValue(),romaniaData);
        });
        verticalLayoutCounty.add(county,counties);
        verticalLayoutCounty.add(tempCounty);
        add(verticalLayoutCounty);


        Details details = new Details();
        VerticalLayout vl = new VerticalLayout();
        details.setSummaryText("Where do i get this data ? ");
        Anchor global = new Anchor();
        global.setText("Global data");
        global.setHref("https://github.com/CSSEGISandData/COVID-19/tree/master/csse_covid_19_data");
        Anchor romania = new Anchor();
        romania.setText("Romanian data");
        romania.setHref("https://www.graphs.ro/index.php");
        vl.add(global,romania);
        details.addContent(vl);

        VerticalLayout footer = new VerticalLayout();
        footer.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        footer.add(details);

        footer.setMinHeight("120px");
        add(footer);

    }

    private void updateCountyChart(VerticalLayout tempCounty, String value, CovidRomaniaService romaniaData) {
        tempCounty.removeAll();
        CountyData countyData = romaniaData.getCountyByName(value);

        ApexCharts chart = getBasicDonutChart();

        chart.setLabels("Confirmed", "Deaths", "Recovered");
        chart.setTitle(TitleSubtitleBuilder.get()
                .withText(value + " as of " +romaniaData.latestReportedDate())
                .withAlign(Align.center)
                .withStyle(StyleBuilder.get()
                        .withColor("hsl(214deg 100% 70%)")
                        .withFontSize("15px")
                        .build())
                .build());
        chart.setLabels("Population","Confirmed");
        chart.setColors("#66a8ff","#e8aa35");
        chart.setSeries(Double.valueOf( countyData.getCountyPopulation()),Double.valueOf( countyData.getTotalCases()));
        tempCounty.add(chart);
        tempCounty.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        HorizontalLayout layout = new HorizontalLayout();
        Label population = new Label("Population : "+countyData.getCountyPopulation());
        population.getElement().getStyle().set("font-size","12px");
        population.getElement().getStyle().set("color","#FFFFFF");
        population.getElement().getStyle().set("border-radius","8px");
        population.getElement().getStyle().set("background","#66a8ff");
        population.getElement().getStyle().set("margin","6px");
        Label cases = new Label("Confirmed : "+countyData.getTotalCases());
        cases.getElement().getStyle().set("font-size","12px");
        cases.getElement().getStyle().set("color","#FFFFFF");
        cases.getElement().getStyle().set("border-radius","8px");
        cases.getElement().getStyle().set("background","#e8aa35");
        cases.getElement().getStyle().set("margin","6px");
        layout.add(population,cases);
        tempCounty.add(layout);

        verticalLayoutCounty.add(tempCounty);

    }

    private ApexCharts romanianTotalDataWithPopulationChart(CovidRomaniaService data){
        ApexCharts chart = getBasicDonutChart();

        chart.setTitle(TitleSubtitleBuilder.get()
                .withText("Totals as of " + data.latestReportedDate())
                .withAlign(Align.center)
                .withStyle(StyleBuilder.get()
                        .withColor("hsl(214deg 100% 70%)")
                        .withFontSize("15px")
                        .build())
                .build());
        chart.setLabels("Population","Confirmed", "Deaths", "Recovered");
        chart.setSeries(Double.valueOf( data.getTotalPopulation())
                ,Double.valueOf(data.getTotalCases())
                ,Double.valueOf(data.getTotalDeaths())
                ,Double.valueOf(data.getTotalRecovered()));
        chart.setColors("#66a8ff","#e8aa35", "#FF0000", "#00FF00");
        return chart;
    }

    public HorizontalLayout getLabelData(CovidRomaniaService data){
        HorizontalLayout layout = new HorizontalLayout();
        Label confirmed = new Label("Confirmed : "+data.getTotalCases());
        confirmed.getElement().getStyle().set("font-size","12px");
        confirmed.getElement().getStyle().set("color","#FFFFFF");
        confirmed.getElement().getStyle().set("border-radius","8px");
        confirmed.getElement().getStyle().set("background","#e8aa35");
        Label deaths = new Label("Deaths : "+data.getTotalDeaths());
        deaths.getElement().getStyle().set("font-size","12px");
        deaths.getElement().getStyle().set("color","#FFFFFF");
        deaths.getElement().getStyle().set("border-radius","8px");
        deaths.getElement().getStyle().set("background","#FF0000");
        Label recovered = new Label("Recovered : "+data.getTotalRecovered());
        recovered.getElement().getStyle().set("font-size","12px");
        recovered.getElement().getStyle().set("color","#FFFFFF");
        recovered.getElement().getStyle().set("border-radius","8px");
        recovered.getElement().getStyle().set("background","#00FF00");
        layout.add(confirmed,deaths,recovered);
        return layout;
    }

    private ApexCharts romanianTotalDataChart(CovidRomaniaService data){
        ApexCharts chart = getBasicDonutChart();

        chart.setTitle(TitleSubtitleBuilder.get()
                .withText("Totals as of " + data.latestReportedDate())
                .withAlign(Align.center)
                .withStyle(StyleBuilder.get()
                        .withColor("hsl(214deg 100% 70%)")
                        .withFontSize("15px")
                        .build())
                .build());
        chart.setLabels("Confirmed", "Deaths", "Recovered");

        chart.setSeries(Double.valueOf(data.getTotalCases()), Double.valueOf(data.getTotalDeaths()), Double.valueOf(data.getTotalRecovered()));
        return chart;

    }

    private void updateChart(VerticalLayout temp, String value, CovidDataService data) {
        temp.removeAll();
        int index = data.getCountryDataByName(value);

        ApexCharts chart = getBasicDonutChart();

        chart.setLabels("Confirmed", "Deaths", "Recovered");
        chart.setTitle(TitleSubtitleBuilder.get()
                .withText(value)
                .withAlign(Align.center)
                .withStyle(StyleBuilder.get()
                        .withColor("hsl(214deg 100% 70%)")
                        .withFontSize("15px")
                        .build())
                .build());
        int confirmedVal = data.getAllStats().get(index).getConfirmed();
        int deathsVal = data.getAllStats().get(index).getDeaths();
        int recoveredVal = data.getAllStats().get(index).getRecovered();
        chart.setSeries(Double.valueOf(confirmedVal), Double.valueOf(deathsVal), Double.valueOf(recoveredVal));
        temp.add(chart);
        temp.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        HorizontalLayout layout = new HorizontalLayout();
        Label confirmed = new Label("Confirmed : "+confirmedVal);
        confirmed.getElement().getStyle().set("font-size","12px");
        confirmed.getElement().getStyle().set("color","#FFFFFF");
        confirmed.getElement().getStyle().set("border-radius","8px");
        confirmed.getElement().getStyle().set("background","#e8aa35");
        confirmed.getElement().getStyle().set("margin","6px");
        Label deaths = new Label("Deaths : "+deathsVal);
        deaths.getElement().getStyle().set("font-size","12px");
        deaths.getElement().getStyle().set("color","#FFFFFF");
        deaths.getElement().getStyle().set("border-radius","8px");
        deaths.getElement().getStyle().set("background","#FF0000");
        deaths.getElement().getStyle().set("margin","6px");
        Label recovered = new Label("Recovered : "+recoveredVal);
        recovered.getElement().getStyle().set("font-size","12px");
        recovered.getElement().getStyle().set("color","#FFFFFF");
        recovered.getElement().getStyle().set("border-radius","8px");
        recovered.getElement().getStyle().set("background","#00FF00");
        recovered.getElement().getStyle().set("margin","6px");
        layout.add(confirmed,deaths,recovered);
        temp.add(layout);



        verticalLayout.add(temp);
    }

    private ApexCharts getBasicDonutChart(){
        return ApexChartsBuilder.get()
                .withChart(ChartBuilder.get()
                        .withType(Type.donut)
                        .withHeight("225px")
                        .withZoom(ZoomBuilder.get()
                                .withEnabled(true)
                                .build())
                        .build())
                .withColors("#e8aa35", "#FF0000", "#00FF00")
                .withTitle(TitleSubtitleBuilder.get()
                        .withAlign(Align.center)
                        .withStyle(StyleBuilder.get()
                                .withColor("hsl(214deg 100% 70%)")
                                .withFontSize("15px")
                                .build())
                        .build())
                .withLegend(LegendBuilder.get()
                        .withWidth(90.0)
                        .withShow(true)
                        .build())
                .build();

    }


}
