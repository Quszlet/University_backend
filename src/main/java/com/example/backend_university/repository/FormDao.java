package com.example.backend_university.repository;

import com.example.backend_university.models.Form;
import com.example.backend_university.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormDao extends JpaRepository<Form, Long> {
    List<Form> findAllByUser(User user);
}
