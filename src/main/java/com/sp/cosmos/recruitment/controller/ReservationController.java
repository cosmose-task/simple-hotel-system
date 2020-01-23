package com.sp.cosmos.recruitment.controller;

import com.sp.cosmos.recruitment.model.Reservation;
import com.sp.cosmos.recruitment.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * customerId was added as temporary user recognition
 * in the feature the endpoints should looks more transparent
 * customerId will be taken from token
 */
@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity getReservations(@PathVariable Long customerId) {
        return ResponseEntity.ok(reservationService.getReservationsByCustomer(customerId));
    }

    @PostMapping(path = "/{customerId}")
    public ResponseEntity addReservation
            (@Valid @RequestBody Reservation reservation,
             @PathVariable Long customerId,
             UriComponentsBuilder uriComponentsBuilder) {
        UriComponents uriComponents =
                uriComponentsBuilder.path("/reservation/customer/{id}")
                        .buildAndExpand(reservationService.addReservation(reservation, customerId).getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping(path = "/{customerId}/{reservationId}")
    public ResponseEntity cancelReservation(@PathVariable Long reservationId,
                                            @PathVariable Long customerId) {
        reservationService.cancelReservation(reservationId, customerId);
        return ResponseEntity.noContent().build();
    }
}
