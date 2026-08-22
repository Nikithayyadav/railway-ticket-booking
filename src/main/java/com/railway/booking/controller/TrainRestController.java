package com.railway.booking.controller;

import com.railway.booking.model.Train;
import com.railway.booking.service.TrainService;
import com.railway.booking.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.railway.booking.response.PageMeta;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainRestController {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public ApiResponse<Train> registerTrain(
            @RequestBody Train train) {

        Train savedTrain =
                trainService.registerTrain(train);

        return new ApiResponse<>(
                true,
                savedTrain,
                null,
                null
        );
    }
    @GetMapping("/search")
    public ApiResponse<List<Train>> searchTrains(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Train> trainPage =
                trainService.searchTrains(
                        source,
                        destination,
                        pageable
                );

        PageMeta pageMeta = new PageMeta(
                trainPage.getNumber(),
                trainPage.getSize(),
                trainPage.getTotalElements(),
                trainPage.getTotalPages(),
                trainPage.isFirst(),
                trainPage.isLast()
        );

        return new ApiResponse<>(
                true,
                trainPage.getContent(),
                null,
                pageMeta
        );
    }
}