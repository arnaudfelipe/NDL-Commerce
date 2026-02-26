package com.ndlcommerce.adapters.persistence.category;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
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
@Table(name = "category", schema = "ecommerce")
public class CategoryDataMapper {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(name = "parent_id")
  private UUID parentId;

  @Column(nullable = false)
  private Boolean active = true;

  @Column(name = "created_by")
  private UUID createdBy;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_by")
  private UUID updatedBy;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public CategoryDataMapper(String name, UUID parentId, UUID createdBy) {
    this.name = name;
    this.parentId = parentId;
    this.createdBy = createdBy;
    this.active = true;
  }
}
