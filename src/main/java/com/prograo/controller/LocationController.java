package com.prograo.controller;

import com.prograo.domain.Location;
import com.prograo.service.LocationService;
import com.prograo.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/locations")
public class LocationController {

    public final LocationService locationService;

    @Autowired
    public SecurityService securityService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAllLocation(@RequestHeader("userAuthId") String userAuthId) {
        List<Location> list = new ArrayList<>();
        if (this.securityService.checkPermissions(userAuthId, "FS"))
            list = this.locationService.getAllLocation();
        return list;
    }
}
