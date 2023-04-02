package com.udacity.vehicles.domain.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository provide a type of data persistence while the web service runs, primarily related to vehicle information received in the CarService.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
