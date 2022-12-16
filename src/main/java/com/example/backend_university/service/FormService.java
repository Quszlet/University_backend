package com.example.backend_university.service;

import com.example.backend_university.exceptions.FormFileIsNotSupported;
import com.example.backend_university.exceptions.FormFileLengthExceprion;
import com.example.backend_university.models.Form;
import com.example.backend_university.models.User;
import com.example.backend_university.repository.FormDao;
import com.example.backend_university.repository.UserDao;
import com.example.backend_university.request_response.FormRequest;
import com.example.backend_university.request_response.FormResponse;
import com.example.backend_university.request_response.MessageResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FormService {

    private FormDao formDao;
    private UserDao userDao;
    private final String FOLDER_PATH = "D:\\Java_project\\Frontend-main\\files\\";

    public FormService(FormDao formDao, UserDao userDao) {
        this.formDao = formDao;
        this.userDao = userDao;
    }

    public MessageResponse save_form(FormRequest request) throws FormFileLengthExceprion, IOException, FormFileIsNotSupported {
        MultipartFile[] files = request.getFiles();
        String[] filePath = {"", ""};

        if (files.length > 2) {
            throw new FormFileLengthExceprion("Количество файлов больше двух");
        }
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            if (fileName.equals("")){
                break;
            }
            if (files[i].getContentType().equalsIgnoreCase("image/jpg") ||
                    files[i].getContentType().equalsIgnoreCase("image/png") ||
                    files[i].getContentType().equalsIgnoreCase("image/jpeg") ||
                    files[i].getContentType().equalsIgnoreCase("application/pdf") ||
                    files[i].getContentType().equalsIgnoreCase("application/vnd.openxmlformats" +
                            "-officedocument.wordprocessingml.document")) {
                filePath[i] = FOLDER_PATH + UUID.randomUUID() + fileName;
                files[i].transferTo(new File(filePath[i]));
            } else {
                throw new FormFileIsNotSupported("У некоторых файлов не допустимый формат");
            }
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDao.findById(userDetails.getId()).get();
        Form form = new Form(request.getFirst_name(), request.getLast_name(), request.getEmail(),
                request.getFull_text(), filePath[0], filePath[1]);
        form.setUser(user);
        formDao.save(form);
        return new MessageResponse("Заявка сохранена");
    }

    public List<FormResponse> getFormsUser(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDao.findById(userDetails.getId()).get();
        List<Form> forms= formDao.findAllByUser(user);
        List<FormResponse> AllForms = new ArrayList<>();
        for (Form form : forms) {
            AllForms.add(new FormResponse(form.getId(), form.getFirst_name(), form.getLast_name(), form.getEmail(),
                    form.getFull_text(), form.getFile_first_path(), form.getFile_second_path()));
        }
        return AllForms;
    }

    public List<FormResponse> getAllForms(){
        List<Form> forms= formDao.findAll();
        List<FormResponse> AllForms = new ArrayList<>();
        for (Form form : forms) {
            AllForms.add(new FormResponse(form.getId(), form.getFirst_name(), form.getLast_name(), form.getEmail(),
                    form.getFull_text(), form.getFile_first_path(), form.getFile_second_path()));
        }
        return AllForms;
    }
}
