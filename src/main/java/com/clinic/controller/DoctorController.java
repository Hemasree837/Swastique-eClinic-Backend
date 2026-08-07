package com.clinic.controller;

import org.springframework.web.bind.annotation.*;
import com.clinic.model.Doctor;
import com.clinic.repository.DoctorRepository;
import java.util.*;

@RestController
@RequestMapping({"/doctor", "/doctors"})
public class DoctorController {
    private final DoctorRepository r;

    public DoctorController(DoctorRepository r) {
        this.r = r;
    }

    @GetMapping
    public List<Doctor> all() {
        return r.findAll();
    }

    @GetMapping("/{id}")
    public Doctor one(@PathVariable Long id) {
        return r.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @PostMapping
    public Doctor add(@RequestBody Doctor x) {
        return r.save(x);
    }

    @PutMapping("/{id}")
    public Doctor put(@PathVariable Long id, @RequestBody Doctor x) {
        x.id = id;
        return r.save(x);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id) {
        r.deleteById(id);
    }
}