package lk.ijse.springboot.microservice.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plateNumber;

    private String brand;

    private String model;

    private String vehicleType;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
}