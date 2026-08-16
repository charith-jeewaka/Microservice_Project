package lk.ijse.springboot.microservice.vehicleservice.controller;

import lk.ijse.springboot.microservice.vehicleservice.entity.Vehicle;
import lk.ijse.springboot.microservice.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Vehicle vehicle) {

        Vehicle saved = vehicleService.create(vehicle);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Vehicle registered successfully",
                        "data", saved
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getByUserId(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(vehicleService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle
    ) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Vehicle updated successfully",
                        "data", vehicleService.update(id, vehicle)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {

        vehicleService.delete(id);

        return ResponseEntity.ok(
                Map.of("message", "Vehicle deleted successfully")
        );
    }

    @PutMapping("/{id}/enter")
    public ResponseEntity<?> enter(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Vehicle entry recorded successfully",
                        "data", vehicleService.enter(id)
                )
        );
    }

    @PutMapping("/{id}/exit")
    public ResponseEntity<?> exit(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Vehicle exit recorded successfully",
                        "data", vehicleService.exit(id)
                )
        );
    }
}