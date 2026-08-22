package com.railway.booking.service;

import com.railway.booking.model.Train;
import com.railway.booking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train registerTrain(Train train) {
        train.setAvailableSeats(train.getTotalSeats());
        train.setActive(true);

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