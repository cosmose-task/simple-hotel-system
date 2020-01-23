package com.sp.cosmos.recruitment.service;

import com.sp.cosmos.recruitment.model.Room;
import com.sp.cosmos.recruitment.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAllByCriteria(LocalDate startDate, LocalDate endDate, Integer minPrice, Integer maxPrice, String city) {

        if (startDate != null && endDate != null) {
            ReservationValidationHelper.validateReservationDate(startDate, endDate);
            return roomRepository.findAllAvailableByAllCriteria(startDate, endDate, minPrice, maxPrice, city);
        } else {
            return roomRepository.findAllAvailableByCriteriaWithoutDates(minPrice, maxPrice, city);
        }
    }
}
