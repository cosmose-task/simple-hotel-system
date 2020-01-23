package com.sp.cosmos.recruitment.repository;

import com.sp.cosmos.recruitment.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query(value = " SELECT r.*  FROM ROOM r " +
            "LEFT JOIN reservation re ON r.id=re.room_id " +
            "AND re.end_time >= :startDate " +
            "AND re.start_time <= :endDate " +
            "AND re.cancelled IS FALSE " +
            "INNER JOIN hotel h ON h.id = r.hotel_id " +
            "WHERE re.id IS NULL " +
            "AND (h.city=:city OR :city IS NULL)" +
            "AND (r.daily_price <= :maxPrice OR :maxPrice IS NULL) " +
            "AND (r.daily_price >= :minPrice OR :minPrice IS NULL) " +
            "AND r.availability IS TRUE",
            nativeQuery = true)
    List<Room> findAllAvailableByAllCriteria(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate,
                                             @Param("minPrice") Integer minPrice,
                                             @Param("maxPrice") Integer maxPrice,
                                             @Param("city") String city);

    @Query(value = " SELECT r.* FROM ROOM r " +
            "INNER JOIN hotel h ON h.id = r.hotel_id " +
            "WHERE " +
            " (h.city=:city OR :city IS NULL) " +
            "AND (r.daily_price <= :maxPrice OR :maxPrice IS NULL) " +
            "AND (r.daily_price >= :minPrice OR :minPrice IS NULL) " +
            "AND r.availability IS TRUE",
            nativeQuery = true)
    List<Room> findAllAvailableByCriteriaWithoutDates(@Param("minPrice") Integer minPrice,
                                                      @Param("maxPrice") Integer maxPrice,
                                                      @Param("city") String city);

    @Query(value = " SELECT r.*  FROM ROOM r " +
            "LEFT JOIN reservation re ON :roomId=re.room_id " +
            "AND re.end_time >= :startDate " +
            "AND re.start_time <= :endDate " +
            "AND re.cancelled IS FALSE " +
            "WHERE re.id IS NULL " +
            "AND r.availability IS TRUE",
            nativeQuery = true)
    List<Room> findAllAvailableByAllCriteriaAndRoomId(@Param("roomId") Long roomId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);
}
