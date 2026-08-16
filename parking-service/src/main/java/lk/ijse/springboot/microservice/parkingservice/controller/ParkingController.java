package lk.ijse.springboot.microservice.parkingservice.controller;

import lk.ijse.springboot.microservice.parkingservice.entity.ParkingSpace;
import lk.ijse.springboot.microservice.parkingservice.entity.ParkingStatus;
import lk.ijse.springboot.microservice.parkingservice.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ParkingSpace parkingSpace) {

        ParkingSpace saved = parkingService.create(parkingSpace);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Parking space created successfully",
                        "data", saved
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAll() {
        return ResponseEntity.ok(parkingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpace> getById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ParkingSpace parkingSpace
    ) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Parking space updated successfully",
                        "data", parkingService.update(id, parkingSpace)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        parkingService.delete(id);

        return ResponseEntity.ok(
                Map.of("message", "Parking space deleted successfully")
        );
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<ParkingSpace>> findByCity(
            @PathVariable String city
    ) {
        return ResponseEntity.ok(parkingService.findByCity(city));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ParkingSpace>> findByStatus(
            @PathVariable ParkingStatus status
    ) {
        return ResponseEntity.ok(parkingService.findByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ParkingSpace>> search(
            @RequestParam String city,
            @RequestParam ParkingStatus status
    ) {
        return ResponseEntity.ok(
                parkingService.findByCityAndStatus(city, status)
        );
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<?> reserve(@PathVariable Long id) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Parking space reserved successfully",
                        "data", parkingService.reserve(id)
                )
        );
    }

    @PutMapping("/{id}/occupy")
    public ResponseEntity<?> occupy(@PathVariable Long id) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Parking space marked as occupied",
                        "data", parkingService.occupy(id)
                )
        );
    }

    @PutMapping("/{id}/release")
    public ResponseEntity<?> release(@PathVariable Long id) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Parking space released successfully",
                        "data", parkingService.release(id)
                )
        );
    }
}