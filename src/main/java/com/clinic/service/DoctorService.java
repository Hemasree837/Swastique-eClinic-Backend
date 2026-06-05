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

    public Doctor save(Doctor doctor) {
        return repo.save(doctor);
    }

    public Doctor toggleLeave(Long id) {
        Doctor doc = repo.findById(id).orElseThrow();

        doc.setOnLeave(!doc.isOnLeave());

        return repo.save(doc);
    }

    public Doctor update(Long id, Doctor doctor) {

        Doctor existing = repo.findById(id).orElseThrow();

        existing.setName(doctor.getName());
        existing.setSpecialization(doctor.getSpecialization());
        existing.setExperience(doctor.getExperience());
        existing.setAvailableSlots(doctor.getAvailableSlots());
        existing.setImageUrl(doctor.getImageUrl());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}