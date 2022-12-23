package com.example.backend_university.models;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "text", length = 2000)
    private String text;
    @Column(name = "creation_data")
    private String creation_data;
    @Column(name = "path_image")
    private String path_image;

    public News(String title, String text, String creation_data, String  file) {
        this.title = title;
        this.text = text;
        this.creation_data = creation_data;
        this.path_image = file;
    }

    public News(Long id, String title, String text, String creation_data, String path_image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creation_data = creation_data;
        this.path_image = path_image;
    }

    public News() {
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

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }
}
