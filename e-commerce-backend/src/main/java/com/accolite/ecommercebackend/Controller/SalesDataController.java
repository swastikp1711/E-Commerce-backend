package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.SalesDataService;
import com.accolite.ecommercebackend.dto.Response.SalesDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/salesdata")
public class SalesDataController {
    @Autowired
    private SalesDataService salesDataService;
    @GetMapping
    public SalesDataResponse fetchsalesdata(){
        return salesDataService.fetchsalesdata();
    }
}
