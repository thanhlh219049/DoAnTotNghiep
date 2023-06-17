package com.lethanh219049.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "task_batch_queue")
public class TaskBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // trang thai hanh dong
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private TemplateMess body;

    @Column(name = "parameters")
    private String parameters;

    // NEW, COMPLETED, FAILED
    @Column(name = "status")
    private String status;

    @Column(name = "retry")
    private Long retry;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "message_error")
    private String messageError;

    @Column(name = "description")
    private String description;

}
