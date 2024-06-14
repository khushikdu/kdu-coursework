package com.kdu.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem {

    @Id
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private LocalDateTime manufactureDateTime;
    private String manufactureFactoryPlace;
}
