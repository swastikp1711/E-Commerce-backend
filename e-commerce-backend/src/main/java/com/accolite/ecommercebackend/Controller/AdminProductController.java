package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.Impl.AdminProductServiceImpl;
import com.accolite.ecommercebackend.dto.Request.ProductRequestadmin;
import com.accolite.ecommercebackend.dto.Request.UpdateProductRequest;
import com.accolite.ecommercebackend.dto.Response.ProductResponseadmin;
import com.accolite.ecommercebackend.dto.Response.UpdateProductResponse;
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
        return new ResponseEntity<ProductResponseadmin>(adminProductServiceImpl.createProduct(productRequestadmin), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<UpdateProductRequest> updateProduct(@PathVariable UUID productId, @RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(adminProductServiceImpl.updateProduct(productId, updateProductRequest));
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
