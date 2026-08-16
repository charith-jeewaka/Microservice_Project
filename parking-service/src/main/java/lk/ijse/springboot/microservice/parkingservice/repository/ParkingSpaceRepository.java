package lk.ijse.springboot.microservice.parkingservice.repository;

import lk.ijse.springboot.microservice.parkingservice.entity.ParkingSpace;
import lk.ijse.springboot.microservice.parkingservice.entity.ParkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    List<ParkingSpace> findByCity(String city);

    List<ParkingSpace> findByStatus(ParkingStatus status);

    List<ParkingSpace> findByCityAndStatus(
            String city,
            ParkingStatus status
    );
}