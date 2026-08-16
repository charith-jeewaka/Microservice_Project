package lk.ijse.springboot.microservice.paymentservice.controller;

import lk.ijse.springboot.microservice.paymentservice.dto.PaymentRequest;
import lk.ijse.springboot.microservice.paymentservice.entity.Payment;
import lk.ijse.springboot.microservice.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> processPayment(
            @RequestBody PaymentRequest request) {

        Payment payment =
                paymentService.processPayment(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Payment completed successfully",
                        "receipt", payment
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                paymentService.getById(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                paymentService.getByUser(userId)
        );
    }
}