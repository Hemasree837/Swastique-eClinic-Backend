package com.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.model.Doctor;
import com.clinic.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Doctor getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor save(Doctor doctor) {
        return repo.save(doctor);
    }

    public Doctor toggleLeave(Long id) {
        Doctor doctor = getById(id);

        doctor.setOnLeave(!doctor.isOnLeave());

        return repo.save(doctor);
    }

    public Doctor update(Long id, Doctor doctor) {

        Doctor existing = getById(id);

        existing.setName(doctor.getName());
        existing.setSpecialization(doctor.getSpecialization());
        existing.setPhone(doctor.getPhone());
        existing.setExperience(doctor.getExperience());
        existing.setAvailableSlots(doctor.getAvailableSlots());
        existing.setImageUrl(doctor.getImageUrl());
        existing.setOnLeave(doctor.isOnLeave());

        return repo.save(existing);
    }

    public void delete(Long id) {
        Doctor doctor = getById(id);
        repo.delete(doctor);
    }
}