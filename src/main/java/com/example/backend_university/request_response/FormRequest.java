package com.example.backend_university.request_response;

import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



public class FormRequest {
    @NotBlank(message = "Это поле должно быть заполнено")
    private String first_name;
    @NotBlank(message = "Это поле должно быть заполнено")
    private String last_name;
    @NotBlank(message = "Это поле должно быть заполнено")
    @Email(message = "Неправильная почта")
    private String email;
    @NotBlank(message = "Это поле должно быть заполнено")
    private String full_text;
    private MultipartFile file;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile files) {
        this.file = files;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
}
