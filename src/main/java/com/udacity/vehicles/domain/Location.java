package com.udacity.vehicles.domain;

//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Transient;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * This declares information about the location of a vehicle.
 * This is not the exact same as the Address class used by boogle-maps - it's primary use is related to the storage of latitude and longitude values.
 * Because the data, such as address, gathered from boogle-maps is annotated as @Transient, this data is not stored until the next time boogle-maps is called.
 *
 * Stores information about a given location.
 * Latitude and longitude must be provided, while other
 * location information must be gathered each time from
 * the maps API.
 */
@Embeddable
public class Location {

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @Transient
    private String address;

    @Transient
    private String city;

    @Transient
    private String state;

    @Transient
    private String zip;

    public Location() {
    }

    public Location(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
