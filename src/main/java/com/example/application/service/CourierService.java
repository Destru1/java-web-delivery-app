package com.example.application.service;

import com.example.application.data.Courier;
import com.example.application.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }
    public List<Courier> findAllCouriers(){
        return courierRepository.findAll();
    }
}
