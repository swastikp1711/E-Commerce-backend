package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryAdminResponse {
    //    private UUID subCategoryId;
    private String subCategoryName;
//    private String categoryName;
}
