package com.pwr.jopek.geo_data.repositories;

import com.pwr.jopek.geo_data.model.GeoCustomer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoCustomerRepository extends JpaRepository<GeoCustomer, Long> {

    List<GeoCustomer> findAll();
    GeoCustomer findGeoCustomersByCustomerId(Long customerId);
    List<GeoCustomer> findGeoCustomersByCustomerIdAndLatIsNotNullAndLngIsNotNull(Long customerId, Pageable pageable);
    List<GeoCustomer> findAllBy(Pageable pageable);
}
