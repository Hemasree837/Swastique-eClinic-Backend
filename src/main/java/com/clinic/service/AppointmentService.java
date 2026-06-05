package com.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    public List<Appointment> getAll() {
        return repo.findAll();
    }

    public Appointment book(Appointment a) {
        a.setStatus("PENDING");
        return repo.save(a);
    }
    public Appointment updateStatus(Long id, String status) {
        Appointment appointment = repo.findById(id).orElseThrow();
        appointment.setStatus(status);
        return repo.save(appointment);
    }
}