package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.Price;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Car Service does a lot of the legwork of the code. It can gather either the entire list of vehicles or just a single vehicle by ID (including calls to the maps and pricing web clients).
 * It can also save updated vehicle information. Lastly, it can delete an existing car. All of these are called by the CarController based on queries to the REST API.
 * You will implement most of these methods yourself.
 * <p>
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    //    private final CarRepository repository;
//
//    public CarService(CarRepository repository) {
//        /**
//         * TODO: Add the Maps and Pricing Web Clients you create
//         *   in `VehiclesApiApplication` as arguments and set them here.
//         */
//        this.repository = repository;
//    }
    @Autowired
    CarRepository repository;

    @Autowired
    MapsClient mapsClient;

    @Autowired
    PriceClient priceClient;

    /**
     * Gathers a list of all vehicles
     *
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return repository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     *
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         *   Remove the below code as part of your implementation.
         */
        /**
         * TODO: Use the Pricing Web client you create in `VehiclesApiApplication`
         *   to get the price based on the `id` input'
         * TODO: Set the price of the car
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         */
        /**
         * TODO: Use the Maps Web client you create in `VehiclesApiApplication`
         *   to get the address for the vehicle. You should access the location
         *   from the car object and feed it to the Maps service.
         * TODO: Set the location of the vehicle, including the address information
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         */

        // to-do 1: find car by id, if exists
        if (repository.findById(id).isPresent()) {
            Car car = repository.findById(id).get();

            // to-do 2: get price based on id from Pricing Web Client
            try {
                String price = priceClient.getPrice(id);
                car.setPrice(price);
            } catch (Exception e) {
                throw new PriceNotFoundException();
            }

            // to-do 3: get address of car from Maps Web Client
            try {
                Location loc = car.getLocation();
                Location result = mapsClient.getAddress(loc);
                car.setLocation(loc);
            } catch (Exception e) {
                throw new MapsNotFoundException();
            }

            return car;

        } else {
            throw new CarNotFoundException();
        }
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     *
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
//        if (car.getId() != null) {
//            return repository.findById(car.getId())
//                    .map(carToBeUpdated -> {
//                        carToBeUpdated.setDetails(car.getDetails());
//                        carToBeUpdated.setLocation(car.getLocation());
//                        return repository.save(carToBeUpdated);
//                    }).orElseThrow(CarNotFoundException::new);
//        }
//
//        return repository.save(car);
        car.setCreatedAt(LocalDateTime.now());
        return repository.save(car);
    }

    public Car update(Car car) {
        if (repository.findById(car.getId()).isPresent()) {
            car.setModifiedAt(LocalDateTime.now());
            return repository.save(car);
        } else {
            throw new CarNotFoundException();
        }
    }

    /**
     * Deletes a given car by ID
     *
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         */
        // if car id not found, throw exception
        if (!repository.existsById(id)) {
            throw new CarNotFoundException();
        } else {
            /**
             * TODO: Delete the car from the repository.
             */
            // if car id found, delete it
            repository.deleteById(id);
        }
    }
}
