package com.example.backend_university.repository;

import com.example.backend_university.models.Form;
import com.example.backend_university.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FormDao extends JpaRepository<Form, Long> {

    Optional<Form> findById(Long id);
    Optional<Form> findByNameFile(String file_name);
    List<Form> findAllByUser(User user);
}
