package com.example.backend_university.controllers;

import com.example.backend_university.exceptions.FormBlankInput;
import com.example.backend_university.exceptions.FormFileIsNotSupported;
import com.example.backend_university.exceptions.FormFileLengthExceprion;
import com.example.backend_university.request_response.FormRequest;
import com.example.backend_university.request_response.MessageResponse;
import com.example.backend_university.request_response.UpdateFormRequest;
import com.example.backend_university.request_response.UpdateUserRequest;
import com.example.backend_university.service.FormService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> send_form(@ModelAttribute @Valid FormRequest request) throws FormBlankInput {
        try {
            return formService.save_form(request);
        } catch (FormFileLengthExceprion | IOException | FormFileIsNotSupported e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFormFile(@PathVariable Long id){
        return formService.download_file(id);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<?> FormUpdate(@PathVariable Long id, @RequestBody @Valid UpdateFormRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(formService.update_form(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(formService.deleteForm(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getFormOne(@PathVariable Long id){
        try{
            return ResponseEntity.ok(formService.getOneForm(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get_forms(){
        try{
            return ResponseEntity.ok(formService.getFormsUser());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> get_formsAll(){
        try{
            return ResponseEntity.ok(formService.getAllForms());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
