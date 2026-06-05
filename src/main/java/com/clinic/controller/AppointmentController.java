package com.clinic.controller;

import com.clinic.model.Appointment;
import com.clinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins="http://localhost:5173")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    public Appointment book(@RequestBody Appointment a) {
        return service.book(a);
    }

    @GetMapping
    public List<Appointment> getAll() {
        return service.getAll();
    }
    @PutMapping("/{id}/approve")
    public Appointment approve(@PathVariable Long id) {
        return service.updateStatus(id, "APPROVED");
    }

    @PutMapping("/{id}/reject")
    public Appointment reject(@PathVariable Long id) {
        return service.updateStatus(id, "REJECTED");
    }
}