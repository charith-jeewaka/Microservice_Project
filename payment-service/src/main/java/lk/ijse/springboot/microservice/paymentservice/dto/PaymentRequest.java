package lk.ijse.springboot.microservice.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long userId;
    private Long parkingSpaceId;
    private Long vehicleId;

    private Double amount;

    // Mock payment input only.
    private String cardNumber;
    private String cardHolderName;
}