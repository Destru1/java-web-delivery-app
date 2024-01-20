package com.example.application.data.generator;

import com.example.application.data.Courier;
import com.example.application.data.Delivery;
import com.example.application.data.Status;
import com.example.application.repository.CourierRepository;
import com.example.application.repository.DeliveryRepository;
import com.example.application.repository.StatusRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringComponent
public class DataGenerator {
    @Bean
    public CommandLineRunner loadData(DeliveryRepository deliveryRepository, CourierRepository courierRepository, StatusRepository statusRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (courierRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;
            logger.info("Loading demo:");
            ExampleDataGenerator<Courier> courierGenerator = new ExampleDataGenerator<>(Courier.class, LocalDateTime.now());
            courierGenerator.setData(Courier::setFullName, DataType.FULL_NAME);
            List<Courier> courierList = courierRepository.saveAll(courierGenerator.create(5, seed));


            List<Status> statuses = statusRepository
                    .saveAll(Stream.of("Sent", "In office", "Delivered", "Unknown")
                            .map(Status::new).collect(Collectors.toList()));

            logger.info("Creating deliveries");

            ExampleDataGenerator<Delivery> deliveryGenerator = new ExampleDataGenerator<>(Delivery.class,LocalDateTime.now());
            deliveryGenerator.setData(Delivery::setFirstName,DataType.FIRST_NAME);
            deliveryGenerator.setData(Delivery::setLastName,DataType.LAST_NAME);
            deliveryGenerator.setData(Delivery::setAddress,DataType.ADDRESS);
            deliveryGenerator.setData(Delivery::setPhoneNumber,DataType.PHONE_NUMBER);

            Random r = new Random(seed);

            List<Delivery> deliveries = deliveryGenerator.create(20,seed).stream().peek(delivery -> {
                delivery.setCourier(courierList.get(r.nextInt(courierList.size())));
                delivery.setStatus(statuses.get(r.nextInt(statuses.size())));
            }).collect(Collectors.toList());

            deliveryRepository.saveAll(deliveries);

            logger.info("Seed successfull");
        };


    }
}
