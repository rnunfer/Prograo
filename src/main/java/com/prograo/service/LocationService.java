package com.prograo.service;

import com.prograo.domain.Location;
import com.prograo.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService (LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation() {
        return this.locationRepository.findAll();
    }
}
