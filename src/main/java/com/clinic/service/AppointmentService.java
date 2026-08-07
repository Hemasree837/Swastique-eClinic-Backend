package com.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.enums.Status;
import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    public List<Appointment> getAll() {
        return repo.findAll();
    }

    public Appointment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment book(Appointment appointment) {
        appointment.setStatus(Status.PENDING);
        return repo.save(appointment);
    }

    public Appointment updateStatus(Long id, Status status) {
        Appointment appointment = getById(id);
        appointment.setStatus(status);
        return repo.save(appointment);
    }

    public Appointment cancel(Long id) {
        Appointment appointment = getById(id);
        appointment.setStatus(Status.CANCELLED);
        return repo.save(appointment);
    }

    public Appointment update(Long id, Appointment updatedAppointment) {
        Appointment existing = getById(id);

        existing.setDoctor(updatedAppointment.getDoctor());
        existing.setPatient(updatedAppointment.getPatient());
        existing.setAppointmentTime(updatedAppointment.getAppointmentTime());
        existing.setReason(updatedAppointment.getReason());

        if (updatedAppointment.getStatus() != null) {
            existing.setStatus(updatedAppointment.getStatus());
        }

        return repo.save(existing);
    }

    public void delete(Long id) {
        Appointment appointment = getById(id);
        repo.delete(appointment);
    }
}