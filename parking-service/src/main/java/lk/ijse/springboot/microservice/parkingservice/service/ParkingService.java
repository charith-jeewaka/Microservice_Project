package lk.ijse.springboot.microservice.parkingservice.service;

import lk.ijse.springboot.microservice.parkingservice.entity.ParkingSpace;
import lk.ijse.springboot.microservice.parkingservice.entity.ParkingStatus;
import lk.ijse.springboot.microservice.parkingservice.repository.ParkingSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingSpaceRepository repository;

    public ParkingSpace create(ParkingSpace parkingSpace) {
        if (parkingSpace.getStatus() == null) {
            parkingSpace.setStatus(ParkingStatus.AVAILABLE);
        }

        return repository.save(parkingSpace);
    }

    public List<ParkingSpace> getAll() {
        return repository.findAll();
    }

    public ParkingSpace getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));
    }

    public ParkingSpace update(Long id, ParkingSpace request) {

        ParkingSpace parkingSpace = getById(id);

        parkingSpace.setSpaceNumber(request.getSpaceNumber());
        parkingSpace.setCity(request.getCity());
        parkingSpace.setZone(request.getZone());
        parkingSpace.setOwnerId(request.getOwnerId());
        parkingSpace.setHourlyRate(request.getHourlyRate());

        if (request.getStatus() != null) {
            parkingSpace.setStatus(request.getStatus());
        }

        return repository.save(parkingSpace);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Parking space not found");
        }

        repository.deleteById(id);
    }

    public List<ParkingSpace> findByCity(String city) {
        return repository.findByCity(city);
    }

    public List<ParkingSpace> findByStatus(ParkingStatus status) {
        return repository.findByStatus(status);
    }

    public List<ParkingSpace> findByCityAndStatus(
            String city,
            ParkingStatus status
    ) {
        return repository.findByCityAndStatus(city, status);
    }

    public ParkingSpace reserve(Long id) {

        ParkingSpace parkingSpace = getById(id);

        if (parkingSpace.getStatus() != ParkingStatus.AVAILABLE) {
            throw new RuntimeException("Parking space is not available");
        }

        parkingSpace.setStatus(ParkingStatus.RESERVED);

        return repository.save(parkingSpace);
    }

    public ParkingSpace occupy(Long id) {

        ParkingSpace parkingSpace = getById(id);

        if (parkingSpace.getStatus() == ParkingStatus.OCCUPIED) {
            throw new RuntimeException("Parking space is already occupied");
        }

        parkingSpace.setStatus(ParkingStatus.OCCUPIED);

        return repository.save(parkingSpace);
    }

    public ParkingSpace release(Long id) {

        ParkingSpace parkingSpace = getById(id);

        parkingSpace.setStatus(ParkingStatus.AVAILABLE);

        return repository.save(parkingSpace);
    }
}