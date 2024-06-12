package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Request.ProductRequestadmin;
import com.accolite.ecommercebackend.dto.Request.UpdateProductRequest;
import com.accolite.ecommercebackend.dto.Response.ProductAdminResponse;
import com.accolite.ecommercebackend.dto.Response.ProductResponseadmin;

import java.util.List;
import java.util.UUID;

public interface AdminProductService {
    public ProductResponseadmin createProduct(ProductRequestadmin productRequestadmin);
    public List<ProductResponseadmin> fetchAllProducts();
    public UpdateProductRequest updateProduct(UUID productId, UpdateProductRequest updateProductRequest);
    public ProductResponseadmin deleteProduct(UUID productId);
}
