package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long houseId;

    private String houseName;
    private String address;

    @OneToMany
    private List<Users> usersList;

    @OneToMany
    private List<Room> rooms;

}
