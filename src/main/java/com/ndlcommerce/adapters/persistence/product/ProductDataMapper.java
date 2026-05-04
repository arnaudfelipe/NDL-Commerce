package com.ndlcommerce.adapters.persistence.product;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "product", schema = "ecommerce")
public class ProductDataMapper {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @Column(nullable = false, length = 255)
  private String name;

  @NotNull
  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "created_by")
  private UUID createdBy;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_by")
  private UUID updatedBy;

  @NotNull
  @Column(name = "brand_id")
  private UUID brandId;

  @NotNull
  @Column(name = "category_id")
  private UUID categoryId;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "active")
  private Boolean active;

  public ProductDataMapper(String name, String description, UUID brandId, UUID categoryId, UUID createdBy) {
    this.name = name;
    this.description = description;
    this.brandId = brandId;
    this.categoryId = categoryId;
    this.createdBy = createdBy;
    this.active = true;
  }
}
