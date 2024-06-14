package com.kdu.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String emailId;
}
