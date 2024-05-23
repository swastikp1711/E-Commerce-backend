package com.accolite.ecommercebackend.dto.Request;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryAdminRequest {
    private String subCategoryName;
    private UUID categoryId;
}
