package com.example.driversApp.controller;


import com.example.driversApp.model.Driver;
import com.example.driversApp.service.DriverService;
import com.example.driversApp.service.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverServiceImpl driverServiceImpl;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listDrivers", driverService.getAllDrivers());
        return "index";
    }

    @GetMapping("/zespoly")
    public String viewZespolyPage(Model model){
        return "zespoly";
    }

    @GetMapping("/terminarz")
    public String viewTerminarzPage(Model model){
        return "terminarz";
    }


    @GetMapping("/dodaj")
    public String dodaj(Model model){
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "dodaj";
    }

    @PostMapping("/saveDriver")
    public String saveDriver(@Valid @ModelAttribute("driver") Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dodaj";
        } else {
            driverService.saveDriver(driver);
            return "redirect:/";
        }
    }


    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model){
        List<Driver> searchResult = driverServiceImpl.search(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle","Wyniki wyszukiwania dla: '" + keyword +"'");
        model.addAttribute("searchResult", searchResult);
        return "wyszukaj_wyniki";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 3;
        Page<Driver> page = driverService.findPaginated(pageNo, pageSize);
        List<Driver> listDrivers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listDrivers", listDrivers);
        return "index";
    }

}
