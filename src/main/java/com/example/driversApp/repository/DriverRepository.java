package com.example.driversApp.repository;


import com.example.driversApp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long > {


    @Query(value = "SELECT * FROM heroku_9c03cee4772dd9b.drivers WHERE "
    + "MATCH(name_and_surename, nationality, team) "
    + "AGAINST (?1)", nativeQuery = true)
    public List<Driver> search(String keyword);

}
