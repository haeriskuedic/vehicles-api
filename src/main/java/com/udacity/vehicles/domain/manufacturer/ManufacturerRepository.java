package com.udacity.vehicles.domain.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository provide a type of data persistence while the web service runs, primarily to store manufacturer information like that initialized in VehiclesApiApplication.
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

}
