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
    @Column(name = "nameFile")
    private String nameFile;
    @Column(name = "file")
    @Lob
    private byte[]  file;
    @Column(name = "type_file")
    private String type_file;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Form(String first_name, String last_name, String email, String full_text,
                String file_name, byte[] file, String type_file, User user) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.full_text = full_text;
        this.nameFile = file_name;
        this.type_file = type_file;
        this.file = file;
        this.user = user;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getType_file() {
        return type_file;
    }

    public void setType_file(String type_file) {
        this.type_file = type_file;
    }
}
