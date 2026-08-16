package lk.ijse.springboot.microservice.vehicleservice.repository;

import lk.ijse.springboot.microservice.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlateNumber(String plateNumber);

    List<Vehicle> findByUserId(Long userId);

    boolean existsByPlateNumber(String plateNumber);
}