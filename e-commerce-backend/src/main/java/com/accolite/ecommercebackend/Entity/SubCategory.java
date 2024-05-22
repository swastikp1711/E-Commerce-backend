package com.accolite.ecommercebackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="subCategoryId", columnDefinition = "BINARY(16)")
    private UUID subCategoryId;

    private String subCategoryName;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    public SubCategory(UUID subCategoryId, String subCategoryName, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Category category) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.category = category;
    }
}

