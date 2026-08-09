package com.clinic.config;

import com.clinic.enums.Role;
import com.clinic.model.Doctor;
import com.clinic.model.User;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed default system users if missing
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("patient").isEmpty()) {
            User patient = new User();
            patient.setUsername("patient");
            patient.setPassword(passwordEncoder.encode("patient123"));
            patient.setRole(Role.PATIENT);
            userRepository.save(patient);
        }

        if (userRepository.findByUsername("reporter").isEmpty()) {
            User reporter = new User();
            reporter.setUsername("reporter");
            reporter.setPassword(passwordEncoder.encode("reporter123"));
            reporter.setRole(Role.REPORTER);
            userRepository.save(reporter);
        }

        // Seed 11 expert doctors if database is empty
        if (doctorRepository.count() == 0) {
            saveDoctor("Dr. K. HEMASREE", "General Medicine / OPD Lead", "9876543210", 8, "9:00 AM, 11:30 AM, 3:00 PM, 5:30 PM");
            saveDoctor("Dr. G. JITHENDRA KUMAR", "Family Medicine Specialist", "9876543211", 7, "10:00 AM, 12:30 PM, 4:00 PM, 6:30 PM");
            saveDoctor("Dr. ARJUN REDDY", "Cardiology / Cardiologist", "9876543212", 12, "10:00 AM, 2:00 PM, 6:00 PM");
            saveDoctor("Dr. PRIYANKA NAIR", "Dermatology / Dermatologist", "9876543213", 7, "11:00 AM, 3:30 PM, 6:30 PM");
            saveDoctor("Dr. RAHUL VARMA", "Orthopedics / Orthopedic Surgeon", "9876543214", 11, "9:30 AM, 1:00 PM, 4:30 PM");
            saveDoctor("Dr. MEGHANA IYER", "Gynecology & Obstetrics", "9876543215", 9, "10:30 AM, 2:30 PM, 5:00 PM");
            saveDoctor("Dr. VIKRAM SINGH", "ENT / Ear, Nose & Throat Specialist", "9876543216", 8, "9:00 AM, 12:00 PM, 3:30 PM");
            saveDoctor("Dr. NISHA BHAT", "Pediatrics / Pediatrician", "9876543217", 6, "11:00 AM, 2:00 PM, 5:30 PM");
            saveDoctor("Dr. ADITYA MENON", "Radiology / Radiologist", "9876543218", 10, "9:00 AM, 1:30 PM, 4:00 PM");
            saveDoctor("Dr. SNEHA KULKARNI", "Ophthalmology / Eye Specialist", "9876543219", 7, "10:00 AM, 1:00 PM, 6:00 PM");
            saveDoctor("Dr. SIDDHARTH JOSE", "Psychiatry & Neurology", "9876543220", 6, "11:30 AM, 3:00 PM, 7:00 PM");
        }
    }

    private void saveDoctor(String name, String spec, String phone, int exp, String slots) {
        Doctor doc = new Doctor();
        doc.setName(name);
        doc.setSpecialization(spec);
        doc.setPhone(phone);
        doc.setExperience(exp);
        doc.setAvailableSlots(slots);
        doc.setOnLeave(false);
        doctorRepository.save(doc);
    }
}
