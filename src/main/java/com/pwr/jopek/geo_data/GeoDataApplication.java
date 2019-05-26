package com.pwr.jopek.geo_data;

import com.pwr.jopek.geo_data.model.Coordinates;
import com.pwr.jopek.geo_data.model.GeoCustomer;
import com.pwr.jopek.geo_data.rest.HereAPI;
import com.pwr.jopek.geo_data.services.GeoCustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class GeoDataApplication implements CommandLineRunner {

    @Autowired
    private GeoCustomerService customerService;

    @Autowired
    private HereAPI hereAPI;

    public static void main(String[] args) {
        SpringApplication.run(GeoDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        GeoCustomer customer = customerService.fetchById(124L);
//        System.out.println(customer);
//        Coordinates coordinates = hereAPI.getCoordinatesForAddress(customer.getAddress());
//        customer.setLat(coordinates.getLat());
//        customer.setLng(coordinates.getLng());
//
//        customerService.updateCustomer(customer);

//        List<GeoCustomer> customers = customerService.fetchAll();
//        System.out.println("Starting processing n customers: " + customers.size());
//        customers.forEach(customer -> {
//            try {
//                System.out.println(customer);
//                Coordinates coordinates = hereAPI.getCoordinatesForAddress(customer.getAddress());
//                System.out.println(coordinates);
//                customer.setLat(coordinates.getLat());
//                customer.setLng(coordinates.getLng());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        System.out.println("Before mass update");
//        customerService.updateAll(customers);
//        System.out.println("The END");


        for(int i = 0; i < 150; i++) {
            List<GeoCustomer> customers = customerService.batchFetch(i, 100);

            System.out.println("Starting processing n customers: " + customers.size());
            customers.forEach(customer -> {
                try {
//                    System.out.println(customer);
                    Coordinates coordinates = hereAPI.getCoordinatesForAddress(customer.getAddress());
//                    System.out.println(coordinates);
                    customer.setLat(coordinates.getLat());
                    customer.setLng(coordinates.getLng());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Before mass update");
            customerService.updateAll(customers);
            System.out.println("The END of package number: " + i);

        }

    }
}
