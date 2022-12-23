package com.example.backend_university.request_response;

public class NewsResponse {
    private Long id;
    private String title;
    private String text;
    private String creation_data;
    private String image_path;
    private String role;

    public NewsResponse(Long id, String title, String text, String creation_data, String image_path,String role) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creation_data = creation_data;
        this.role = role;
        this.image_path = image_path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreation_data() {
        return creation_data;
    }

    public void setCreation_data(String creation_data) {
        this.creation_data = creation_data;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
