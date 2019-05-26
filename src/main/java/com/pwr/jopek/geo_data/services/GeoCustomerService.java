package com.pwr.jopek.geo_data.services;


import com.pwr.jopek.geo_data.model.GeoCustomer;
import com.pwr.jopek.geo_data.repositories.GeoCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoCustomerService {

    private final GeoCustomerRepository repository;

    @Autowired
    public GeoCustomerService(GeoCustomerRepository repository) {
        this.repository = repository;
    }

    public GeoCustomer fetchById(Long id) {
        return repository.findGeoCustomersByCustomerId(id);
    }

    public List<GeoCustomer> fetchAll() {
        return repository.findAll();
    }

    public void updateCustomer(GeoCustomer customer) {
        repository.save(customer);
    }

    public void updateAll(List<GeoCustomer> customers) {
        repository.saveAll(customers);
    }

    public List<GeoCustomer> batchFetch(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllBy(pageable);
    }
}
