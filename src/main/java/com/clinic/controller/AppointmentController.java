package com.clinic.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clinic.model.Appointment;
import com.clinic.service.AppointmentService;

@RestController
@RequestMapping({"/appointment", "/appointments"})
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> all() {
        return appointmentService.getAll();
    }

    @GetMapping("/{id}")
    public Appointment one(@PathVariable Long id) {
        return appointmentService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment add(@RequestBody Appointment appointment) {
        return appointmentService.book(appointment);
    }

    @PutMapping("/{id}")
    public Appointment update(
            @PathVariable Long id,
            @RequestBody Appointment appointment
    ) {
        return appointmentService.update(id, appointment);
    }

    @PutMapping("/{id}/status")
    public Appointment updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return appointmentService.updateStatus(
                id,
                com.clinic.enums.Status.valueOf(status.toUpperCase())
        );
    }

    @PutMapping("/{id}/cancel")
    public Appointment cancel(@PathVariable Long id) {
        return appointmentService.cancel(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}