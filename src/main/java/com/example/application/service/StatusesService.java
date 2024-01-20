package com.example.application.service;

import com.example.application.data.Status;
import com.example.application.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusesService {
    private final StatusRepository statusRepository;

    public StatusesService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAllStatuses() {
        return statusRepository.findAll();
    }
}
