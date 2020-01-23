package com.sp.cosmos.recruitment.service;

import com.sp.cosmos.recruitment.error.ReservationException;
import com.sp.cosmos.recruitment.model.Customer;
import com.sp.cosmos.recruitment.model.Reservation;
import com.sp.cosmos.recruitment.model.Room;
import com.sp.cosmos.recruitment.repository.ReservationRepository;
import com.sp.cosmos.recruitment.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Reservation> getReservationsByCustomer(Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return reservationRepository.findAllByCustomerAndCancelledIsFalse(customer);
    }

    public Reservation addReservation(Reservation reservation, Long customerId) {
        ReservationValidationHelper.validateReservationDate(reservation.getStartTime(), reservation.getEndTime());

        Customer customer = new Customer();
        customer.setId(customerId);
        reservation.setCustomer(customer);

        return addReservation(reservation);
    }

    @Transactional
    public Reservation addReservation(Reservation reservation) {

        List<Room> rooms = roomRepository
                .findAllAvailableByAllCriteriaAndRoomId(reservation.getRoom().getId(), reservation.getStartTime(), reservation.getEndTime());
        if (rooms.isEmpty())
            throw new ReservationException();

        return reservationRepository.save(reservation);
    }

    @Transactional
    public int cancelReservation(Long reservationId, Long customerId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationException::new);
        if(!reservation.getCustomer().getId().equals(customerId))
            throw new ReservationException();
        return reservationRepository.setCancelledByReservationId(true, reservationId);
    }
}
