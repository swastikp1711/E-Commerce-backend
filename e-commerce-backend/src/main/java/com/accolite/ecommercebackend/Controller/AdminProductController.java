package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.Impl.AdminProductServiceImpl;
import com.accolite.ecommercebackend.dto.Request.ProductRequestadmin;
import com.accolite.ecommercebackend.dto.Response.ProductResponseadmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private AdminProductServiceImpl adminProductServiceImpl;

    @PostMapping ("/addProduct")
    public ResponseEntity<ProductResponseadmin> createProduct(@RequestBody ProductRequestadmin productRequestadmin) {
        System.out.println("Data fetched from frontend");
        System.out.println("Data adding to db");
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println("Header: " + headerName + " = " + headerValue);
//        }
        return new ResponseEntity<ProductResponseadmin>(adminProductServiceImpl.createProduct(productRequestadmin), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseadmin> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequestadmin productRequestadmin) {
        return ResponseEntity.ok(adminProductServiceImpl.updateProduct(productId, productRequestadmin));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseadmin>> fetchAllProducts() {
        return ResponseEntity.ok(adminProductServiceImpl.fetchAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseadmin> fetchProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(adminProductServiceImpl.fetchProductById(productId));
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<ProductResponseadmin>> fetchProductsByCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(adminProductServiceImpl.fetchProductsByCategory(categoryId));
    }

    @GetMapping("/bySubCategory/{subCategoryId}")
    public ResponseEntity<List<ProductResponseadmin>> fetchProductsBySubCategory(@PathVariable UUID subCategoryId) {
        return ResponseEntity.ok(adminProductServiceImpl.fetchProductsBySubCategoryId(subCategoryId));
    }

    @GetMapping("/byCategoryAndSubCategory")
    public ResponseEntity<List<ProductResponseadmin>> fetchProductsByCategoryAndSubCategory(@RequestParam UUID categoryId, @RequestParam UUID subCategoryId) {
        return ResponseEntity.ok(adminProductServiceImpl.fetchProductsByCategoryAndSubCategory(categoryId, subCategoryId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseadmin> deleteProduct(@PathVariable String productId) {
        try {
            UUID uuid = UUID.fromString(productId);
            return ResponseEntity.ok(adminProductServiceImpl.deleteProduct(uuid));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
