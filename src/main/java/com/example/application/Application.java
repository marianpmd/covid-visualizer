package com.example.application;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.vaadin.artur.helpers.LaunchUtil;

import javax.servlet.ServletRequest;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling

public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));

    }

}
