module com.simanja {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens com.simanja to javafx.fxml;

    opens com.simanja.controller to javafx.fxml;

    opens com.simanja.model to javafx.fxml;

    opens com.simanja.dto to
            com.fasterxml.jackson.databind;

    exports com.simanja;
    exports com.simanja.controller;
    exports com.simanja.model;
    exports com.simanja.service;
    exports com.simanja.util;
    exports com.simanja.dto;
}