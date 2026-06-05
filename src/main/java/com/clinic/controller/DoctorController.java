package com.clinic.controller;

import com.clinic.model.Doctor;
import com.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins="http://localhost:5173")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Doctor add(@RequestBody Doctor doctor) {
        return service.save(doctor);
    }

    @PutMapping("/{id}/leave")
    public Doctor toggleLeave(@PathVariable Long id) {
        return service.toggleLeave(id);
    }
    @PutMapping("/{id}")
    public Doctor update(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return service.update(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
