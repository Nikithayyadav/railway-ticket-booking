package com.railway.booking.repository;

import com.railway.booking.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {

    Optional<Train> findByTrainNumber(String trainNumber);
    Page<Train> findBySourceIgnoreCaseAndDestinationIgnoreCase(
            String source,
            String destination,
            Pageable pageable
    );

}