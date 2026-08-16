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

    
}