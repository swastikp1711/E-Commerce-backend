package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.HomePageService;
import com.accolite.ecommercebackend.dto.Response.HomePageLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/load")
    public ResponseEntity<HomePageLoadResponse> loadHomePage() {
        HomePageLoadResponse response = homePageService.loadHomePage();
        return ResponseEntity.ok(response);
    }
}
