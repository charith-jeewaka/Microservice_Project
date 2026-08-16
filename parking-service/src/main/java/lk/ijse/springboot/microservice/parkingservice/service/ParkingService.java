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

    
}