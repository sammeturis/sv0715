package com.acme.rental.controller;

import com.acme.rental.entity.Tool;
import com.acme.rental.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return toolService.findToolById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Additional endpoints for creating, updating, and deleting tools can be added here
}

