package com.example.driversApp.service;

import com.example.driversApp.model.Driver;
import com.example.driversApp.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    @Override
    public Driver saveDriver(Driver driver) {
        this.driverRepository.save(driver);
        return driver;
    }

    @Override
    public Page<Driver> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.driverRepository.findAll(pageable);
    }

    public List<Driver> search(String keyword){
        return driverRepository.search(keyword);
    }

}
