package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SalesDataResponse {

    private long productCount;
    private long userCount;
    private long orderCount;
    private double totalRevenue;

}
