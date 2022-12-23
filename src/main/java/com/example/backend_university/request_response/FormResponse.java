package com.example.backend_university.request_response;

public class FormResponse {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String full_text;
    private byte[] File;
    private String typeFile;

    public FormResponse(Long id, String first_name, String last_name, String email, String full_text) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.full_text = full_text;
    }

    public FormResponse(String typeFile) {
        this.typeFile = typeFile;
    }

    public FormResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public byte[] getFile() {
        return File;
    }

    public void setFile(byte[] file) {
        File = file;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }
}
