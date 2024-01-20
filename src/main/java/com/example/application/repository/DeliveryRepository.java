package com.example.application.repository;

import com.example.application.data.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("select d from Delivery d " +
            "where lower(d.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(d.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Delivery> search(@Param("searchTerm") String searchTerm);
}
