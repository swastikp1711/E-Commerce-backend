package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
}
