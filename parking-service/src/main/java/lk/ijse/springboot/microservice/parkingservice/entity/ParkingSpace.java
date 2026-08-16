package lk.ijse.springboot.microservice.parkingservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_spaces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spaceNumber;

    private String city;

    private String zone;

    private Long ownerId;

    private Double hourlyRate;

    @Enumerated(EnumType.STRING)
    private ParkingStatus status;
}