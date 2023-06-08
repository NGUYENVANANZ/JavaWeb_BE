package com.example.javaweb_be.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userName;

    private String passWord;

    private Long role_User;

}
