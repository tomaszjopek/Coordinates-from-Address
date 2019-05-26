package com.pwr.jopek.geo_data.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwr.jopek.geo_data.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HereAPI {

    private final ObjectMapper objectMapper;

    @Value(value = "${here.api.url}")
    private String apiUrl;

    @Value(value = "${here.api.app.id}")
    private String appId;

    @Value(value = "${here.api.app.code}")
    private String appCode;

    @Autowired
    public HereAPI(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Coordinates getCoordinatesForAddress(String address) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> parameters = prepareParameters(address);

        String response = restTemplate.getForObject("https://geocoder.api.here.com/6.2/geocode.json?app_id={YOUR_APP_ID}&app_code={YOUR_APP_CODE}&searchtext={text}",
                String.class,
                appId, appCode, address);

        JsonNode node = objectMapper.readTree(response);
        Coordinates coordinates = new Coordinates();

        try {
            JsonNode location = node.path("Response").path("View").get(0).path("Result").get(0).path("Location").path("DisplayPosition");
            coordinates.setLat(location.get("Latitude").asDouble());
            coordinates.setLng(location.get("Longitude").asDouble());
        }
        catch (Exception e) {
            System.out.println("Catched exception null geo data.");
        }

        return coordinates;
    }

    private Map<String, String> prepareParameters(String address) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("searchtext", address);
        map.put("app_id", this.appId);
        map.put("app_code", this.appCode);

        return map;
    }

}
