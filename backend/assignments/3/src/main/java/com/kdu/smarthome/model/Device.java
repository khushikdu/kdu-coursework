package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class Device {
    @Id
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private boolean available;

    @ManyToOne
    private Room room;
}
