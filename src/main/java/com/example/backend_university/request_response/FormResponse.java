package com.example.backend_university.request_response;

public class FormResponse {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String full_text;
    private String file_first_path;
    private String file_second_path;

    public FormResponse(Long id, String first_name, String last_name,
                        String email, String full_text, String file_first_path, String file_second_path) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.full_text = full_text;
        this.file_first_path = file_first_path;
        this.file_second_path = file_second_path;
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

    public String getFile_first_path() {
        return file_first_path;
    }

    public void setFile_first_path(String file_first_path) {
        this.file_first_path = file_first_path;
    }

    public String getFile_second_path() {
        return file_second_path;
    }

    public void setFile_second_path(String file_second_path) {
        this.file_second_path = file_second_path;
    }
}
