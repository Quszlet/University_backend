package com.example.backend_university.repository;

import com.example.backend_university.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FormDao extends JpaRepository<Form, Long> {

}
