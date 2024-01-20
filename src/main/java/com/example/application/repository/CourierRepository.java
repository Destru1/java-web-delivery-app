package com.example.application.repository;

import com.example.application.data.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier,Long> {
}
