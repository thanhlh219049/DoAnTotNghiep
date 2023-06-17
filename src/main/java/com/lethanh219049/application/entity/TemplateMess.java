package com.lethanh219049.application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "template_Mess")
public class TemplateMess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // trang thai hanh dong
    @Column(name = "name")
    private String name;

    @Column(name = "body")
    private String body;

}
