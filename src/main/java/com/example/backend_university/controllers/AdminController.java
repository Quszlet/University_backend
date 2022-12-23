package com.example.backend_university.controllers;

import com.example.backend_university.request_response.UpdateUserRequest;
import com.example.backend_university.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteUser(id));
    }

    @PutMapping("/raise/{id}/{role}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> raiseUser(@PathVariable Long id, @PathVariable String role) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.raiseUser(id, role));
    }

    @PutMapping("/change/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> userUpdate(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.update_user(id, request));
    }
}
