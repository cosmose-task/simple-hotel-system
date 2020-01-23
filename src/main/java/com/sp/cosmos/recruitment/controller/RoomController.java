package com.sp.cosmos.recruitment.controller;

import com.sp.cosmos.recruitment.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity getRooms
            (@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
             @RequestParam(required = false) String city,
             @RequestParam(required = false) Integer startRange,
             @RequestParam(required = false) Integer endRange) {

        return ResponseEntity.ok(roomService.findAllByCriteria(startDate, endDate, startRange, endRange, city));
    }
}
