package com.railway.booking.service;

import com.railway.booking.model.Train;
import com.railway.booking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train registerTrain(Train train) {

        train.setAvailableSeats(train.getTotalSeats());

        train.setActive(true);

        train.setStarted(false);

        train.setCreatedAt(
                LocalDateTime.now(
                        ZoneId.of("Asia/Kolkata")
                )
        );

        return trainRepository.save(train);
    }

    public Page<Train> searchTrains(
            String source,
            String destination,
            Pageable pageable) {

        return trainRepository
                .findBySourceIgnoreCaseAndDestinationIgnoreCase(
                        source,
                        destination,
                        pageable
                );
    }
}