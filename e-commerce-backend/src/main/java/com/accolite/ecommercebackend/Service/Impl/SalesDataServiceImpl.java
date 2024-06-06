package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Repository.SalesDataRepository;
import com.accolite.ecommercebackend.Service.SalesDataService;
import com.accolite.ecommercebackend.dto.Response.SalesDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesDataServiceImpl implements SalesDataService {
    @Autowired
    private SalesDataRepository salesDataRepository;
    @Override
    public SalesDataResponse fetchsalesdata() {
        return salesDataRepository.getSales();
    }
}
