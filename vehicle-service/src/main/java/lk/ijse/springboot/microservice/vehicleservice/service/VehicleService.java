package lk.ijse.springboot.microservice.vehicleservice.service;

import lk.ijse.springboot.microservice.vehicleservice.entity.Vehicle;
import lk.ijse.springboot.microservice.vehicleservice.entity.VehicleStatus;
import lk.ijse.springboot.microservice.vehicleservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repository;

    public Vehicle create(Vehicle vehicle) {

        if (repository.existsByPlateNumber(vehicle.getPlateNumber())) {
            throw new RuntimeException("Vehicle plate number already exists");
        }

        if (vehicle.getStatus() == null) {
            vehicle.setStatus(VehicleStatus.OUTSIDE);
        }

        return repository.save(vehicle);
    }

    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    public Vehicle getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public List<Vehicle> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Vehicle update(Long id, Vehicle request) {

        Vehicle vehicle = getById(id);

        vehicle.setPlateNumber(request.getPlateNumber());
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setUserId(request.getUserId());

        return repository.save(vehicle);
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Vehicle not found");
        }

        repository.deleteById(id);
    }

    public Vehicle enter(Long id) {

        Vehicle vehicle = getById(id);

        if (vehicle.getStatus() == VehicleStatus.ENTERED) {
            throw new RuntimeException("Vehicle has already entered");
        }

        vehicle.setStatus(VehicleStatus.ENTERED);

        return repository.save(vehicle);
    }

    public Vehicle exit(Long id) {

        Vehicle vehicle = getById(id);

        if (vehicle.getStatus() == VehicleStatus.OUTSIDE) {
            throw new RuntimeException("Vehicle is already outside");
        }

        vehicle.setStatus(VehicleStatus.OUTSIDE);

        return repository.save(vehicle);
    }
}