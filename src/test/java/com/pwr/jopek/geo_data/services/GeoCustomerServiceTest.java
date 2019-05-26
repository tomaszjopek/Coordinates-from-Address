package com.pwr.jopek.geo_data.services;

import com.pwr.jopek.geo_data.GeoDataApplication;
import com.pwr.jopek.geo_data.model.GeoCustomer;
import com.pwr.jopek.geo_data.repositories.GeoCustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK
        , classes = {GeoDataApplication.class})
public class GeoCustomerServiceTest {

    @Autowired
    private GeoCustomerRepository repository;

    @Test
    public void batchFetch() {
        Pageable pageable = PageRequest.of(0, 100);
        List<GeoCustomer> customers = repository.findAllBy(pageable);

        assertEquals(100, customers.size());

    }
}
