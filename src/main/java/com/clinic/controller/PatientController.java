package com.clinic.controller;

import org.springframework.web.bind.annotation.*;
import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import java.util.*;

@RestController
@RequestMapping({"/patient", "/patients"})
public class PatientController {
    private final PatientRepository r;

    public PatientController(PatientRepository r) {
        this.r = r;
    }

    @GetMapping
    public List<Patient> all() {
        return r.findAll();
    }

    @GetMapping("/{id}")
    public Patient one(@PathVariable Long id) {
        return r.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @PostMapping
    public Patient add(@RequestBody Patient x) {
        return r.save(x);
    }

    @PutMapping("/{id}")
    public Patient put(@PathVariable Long id, @RequestBody Patient x) {
        x.id = id;
        return r.save(x);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id) {
        r.deleteById(id);
    }
}