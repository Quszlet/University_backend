package com.example.backend_university.models;

import javax.persistence.*;

@Entity
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "full_text")
    private String full_text;
    @Column(name = "file_first_path")
    private String file_first_path;
    @Column(name = "file_second_path")
    private String file_second_path;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Form(String first_name, String last_name, String email, String full_text,
                String file_first_path, String file_second_path) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.full_text = full_text;
        this.file_first_path = file_first_path;
        this.file_second_path = file_second_path;
    }

    public Form(Long id, String first_name, String last_name, String email,
                String full_text, String file_first_path, String file_second_path) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.full_text = full_text;
        this.file_first_path = file_first_path;
        this.file_second_path = file_second_path;
    }

    public Form() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
