package com.lethanh219049.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEmail {
    private String host;
    private int port;
    private String userName;
    private String password;
}
