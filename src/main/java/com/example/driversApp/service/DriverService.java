package com.example.driversApp.service;

import com.example.driversApp.model.Driver;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();
    Driver saveDriver(Driver driver);
    Page<Driver> findPaginated(int pageNo, int pageSize);
}
