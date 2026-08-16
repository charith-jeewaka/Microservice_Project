package lk.ijse.springboot.microservice.paymentservice.service;

import lk.ijse.springboot.microservice.paymentservice.dto.PaymentRequest;
import lk.ijse.springboot.microservice.paymentservice.entity.Payment;
import lk.ijse.springboot.microservice.paymentservice.entity.PaymentStatus;
import lk.ijse.springboot.microservice.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public Payment processPayment(PaymentRequest request) {

        // Simple MOCK validation for coursework.
        if (request.getCardNumber() == null ||
                request.getCardNumber().length() < 4) {
            throw new RuntimeException("Invalid mock card details");
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new RuntimeException("Invalid payment amount");
        }

        String lastFour =
                request.getCardNumber()
                        .substring(request.getCardNumber().length() - 4);

        Payment payment = Payment.builder()
                .userId(request.getUserId())
                .parkingSpaceId(request.getParkingSpaceId())
                .vehicleId(request.getVehicleId())
                .amount(request.getAmount())
                .cardHolderName(request.getCardHolderName())
                .maskedCardNumber("**** **** **** " + lastFour)
                .status(PaymentStatus.SUCCESS)
                .paymentTime(LocalDateTime.now())
                .receiptNumber(
                        "REC-" +
                                UUID.randomUUID()
                                        .toString()
                                        .substring(0, 8)
                                        .toUpperCase()
                )
                .build();

        return repository.save(payment);
    }

    public List<Payment> getAll() {
        return repository.findAll();
    }

    public Payment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));
    }

    public List<Payment> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}