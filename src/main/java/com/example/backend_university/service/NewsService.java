package com.example.backend_university.service;

import com.example.backend_university.exceptions.FileIsNull;
import com.example.backend_university.exceptions.FormFileIsNotSupported;
import com.example.backend_university.models.Form;
import com.example.backend_university.models.News;
import com.example.backend_university.models.User;
import com.example.backend_university.repository.NewsDao;
import com.example.backend_university.repository.UserDao;
import com.example.backend_university.request_response.FormResponse;
import com.example.backend_university.request_response.MessageResponse;
import com.example.backend_university.request_response.NewsRequest;
import com.example.backend_university.request_response.NewsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class NewsService {

    private NewsDao newsDao;

    private UserDao userDao;
    private final String FOLDER_PATH = "D:\\Java_project\\Frontend-main\\images\\";

    public NewsService(NewsDao newsDao, UserDao userDao) {
        this.newsDao = newsDao;
        this.userDao = userDao;
    }

    public ResponseEntity<?> saveNews(NewsRequest newsRequest) throws FormFileIsNotSupported {
        MultipartFile image = newsRequest.getImage();
        String filePath = "";

        if (image.getContentType() == null){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponse("Отствует файл"));
        }

        if (image.getContentType().equalsIgnoreCase("image/jpg") ||
                image.getContentType().equalsIgnoreCase("image/png") ||
                image.getContentType().equalsIgnoreCase("image/jpeg")) {
            String fileName = image.getOriginalFilename();
            filePath = FOLDER_PATH + UUID.randomUUID() + fileName;
            try {
                image.transferTo(new File(filePath));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new MessageResponse("Произошла ошибка сохранения файла"));
            }
        } else {
            throw new FormFileIsNotSupported("Формат файла не поддерживается");
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        newsDao.save(new News(newsRequest.getTitle(), newsRequest.getText(), formatter.format(calendar.getTime()),
                filePath));
        return ResponseEntity.ok(new MessageResponse("Новость создана"));
    }

    public ResponseEntity<?> getNews(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDao.findById(userDetails.getId()).get();
        List<News> news= newsDao.findAll();
        List<NewsResponse> AllNews = new ArrayList<>();
        for (News obj : news) {
            AllNews.add(new NewsResponse(obj.getId(), obj.getTitle(), obj.getText(),
                    obj.getCreation_data(), obj.getPath_image(), user.getRole()));
        }
        return ResponseEntity.ok(AllNews);
    }

    public ResponseEntity<?> updateNews(Long id, NewsRequest newsRequest) throws FormFileIsNotSupported, FileIsNull {
        MultipartFile image = newsRequest.getImage();
        String filePath = "";
        News news = newsDao.findById(id).get();
        news.setText(newsRequest.getText());
        news.setTitle(newsRequest.getTitle());
        String fileName = image.getOriginalFilename();
        if (image.getContentType() != null && !fileName.equals("")){
            if (image.getContentType().equalsIgnoreCase("image/jpg") ||
                    image.getContentType().equalsIgnoreCase("image/png") ||
                    image.getContentType().equalsIgnoreCase("image/jpeg")) {
                filePath = FOLDER_PATH + UUID.randomUUID() + fileName;
                try {
                    image.transferTo(new File(filePath));
                    news.setPath_image(filePath);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                            .body(new MessageResponse("Произошла ошибка сохранения файла"));
                }
            } else {
                throw new FormFileIsNotSupported("Формат файла не поддерживается");
            }
        }
        newsDao.save(news);
        return ResponseEntity.ok(new MessageResponse("Новость была отредактированна"));
    }

    public ResponseEntity<?> deleteNews(Long id){
        newsDao.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Новость была удалена"));
    }
}
