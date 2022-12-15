package com.example.backend_university.controllers;

import com.example.backend_university.exceptions.FormFileIsNotSupported;
import com.example.backend_university.exceptions.FormFileLengthExceprion;
import com.example.backend_university.request_response.FormRequest;
import com.example.backend_university.request_response.MessageResponse;
import com.example.backend_university.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class FormController {

    private FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send_form(@ModelAttribute FormRequest request) {
        try {
            return ResponseEntity.ok(formService.save_form(request));
        } catch (FormFileLengthExceprion | IOException | FormFileIsNotSupported e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get")
    public ResponseEntity<?> get_forms(){
        try{
            return ResponseEntity.ok(formService.getForms());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
