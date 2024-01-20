package com.example.application.service;

import com.example.application.data.Delivery;
import com.example.application.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;


    public DeliveryService(DeliveryRepository deliveryRepository) {

        this.deliveryRepository = deliveryRepository;

    }

    public List<Delivery> findAllDeliveries(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return deliveryRepository.findAll();
        } else {
            return deliveryRepository.search(filterText);
        }
    }

    public long countDeliveries() {
        return deliveryRepository.count();
    }

    public void deleteDelivery(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    public void saveDelivery(Delivery delivery) {
        if (delivery == null) {
            System.err.println("Delivery is null");
            return;
        }
        deliveryRepository.save(delivery);
    }
}
