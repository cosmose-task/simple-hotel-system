package com.sp.cosmos.recruitment.repository;

import com.sp.cosmos.recruitment.model.Customer;
import com.sp.cosmos.recruitment.model.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Modifying
    @Query("update Reservation re set re.cancelled = :cancelled where re.id = :id")
    int setCancelledByReservationId(@Param("cancelled") boolean cancelled, @Param("id") long reservationId);

    List<Reservation> findAllByCustomerAndCancelledIsFalse(Customer customer);
}
