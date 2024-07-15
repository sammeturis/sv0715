package com.acme.rental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.rental.entity.Tool;
import com.acme.rental.repository.ToolRepository;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public Optional<Tool> findToolById(Long id) {
        return toolRepository.findById(id);
    }
}
