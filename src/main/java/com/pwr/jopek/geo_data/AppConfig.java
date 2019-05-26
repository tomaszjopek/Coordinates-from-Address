package com.pwr.jopek.geo_data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
