package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Request.ProductRequestadmin;
import com.accolite.ecommercebackend.dto.Response.ProductAdminResponse;
import com.accolite.ecommercebackend.dto.Response.ProductResponseadmin;

import java.util.List;
import java.util.UUID;

public interface AdminProductService {
    public ProductResponseadmin createProduct(ProductRequestadmin productRequestadmin);
    public ProductResponseadmin updateProduct(UUID productId, ProductRequestadmin productRequestadmin);
    public List<ProductResponseadmin> fetchAllProducts();
    public ProductResponseadmin fetchProductById(UUID productId);
    public ProductAdminResponse fetchLimitedProductById(UUID productId);

}
