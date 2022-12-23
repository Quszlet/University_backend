package com.example.backend_university.controllers;

import com.example.backend_university.exceptions.FileIsNull;
import com.example.backend_university.exceptions.FormFileIsNotSupported;
import com.example.backend_university.request_response.NewsRequest;
import com.example.backend_university.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @PostMapping("/send")
    public ResponseEntity<?> send_news(@ModelAttribute @Valid NewsRequest newsRequest) throws FormFileIsNotSupported {
        return newsService.saveNews(newsRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get_news(){
        return newsService.getNews();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update_news(@PathVariable Long id, @ModelAttribute @Valid NewsRequest newsRequest) throws FormFileIsNotSupported, FileIsNull {
        return newsService.updateNews(id, newsRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete_news(@PathVariable Long id){
        return newsService.deleteNews(id);
    }

}
