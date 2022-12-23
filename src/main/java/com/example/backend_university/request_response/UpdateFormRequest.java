package com.example.backend_university.request_response;

import javax.validation.constraints.NotBlank;

public class UpdateFormRequest {
    @NotBlank(message = "Имя не должно быть пустое")
    private String first_name;
    @NotBlank(message = "Фамилия не должна быть пустая")
    private String last_name;
    @NotBlank(message = "Email не должен быть пустой")
    private String email;
    @NotBlank(message = "Сообщение не должно быть пустым")
    private String full_text;

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

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
}
