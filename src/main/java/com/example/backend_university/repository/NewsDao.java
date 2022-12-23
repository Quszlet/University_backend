package com.example.backend_university.repository;

import com.example.backend_university.models.Form;
import com.example.backend_university.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsDao extends JpaRepository<News, Long> {
    Optional<News> findById(Long id);
}
