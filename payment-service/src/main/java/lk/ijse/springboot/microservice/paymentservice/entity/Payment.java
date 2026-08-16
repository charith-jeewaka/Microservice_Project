package lk.ijse.springboot.microservice.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long parkingSpaceId;

    private Long vehicleId;

    private Double amount;

    private String cardHolderName;

    // Store only masked card information, not a full card number.
    private String maskedCardNumber;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paymentTime;

    private String receiptNumber;
}