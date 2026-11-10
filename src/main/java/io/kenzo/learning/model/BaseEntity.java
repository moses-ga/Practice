package io.kenzo.learning.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Common columns every table carries: id, isActive, createdAt, updatedAt.
 * Extend this for every new model.
 *
 * Accessors are written by hand (not Lombok) so the boolean is exposed as
 * {@code isActive} everywhere: Java ({@code getIsActive()}), Thymeleaf
 * ({@code ${user.isActive}}), form binding ({@code name="isActive"}), JSON and
 * Spring Data derived queries ({@code countByIsActive}).
 */
@MappedSuperclass
public abstract class BaseEntity {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** Human readable created date, also sent in JSON for DataTables. */
    @JsonProperty
    public String formattedCreatedAt() {
        return createdAt == null ? null : createdAt.format(DATE_TIME);
    }

    @JsonProperty
    public String formattedUpdatedAt() {
        return updatedAt == null ? null : updatedAt.format(DATE_TIME);
    }
}
