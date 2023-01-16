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
import com.example.backend_university.request_response.UpdateFormRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
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

    public ResponseEntity<?> save_form(FormRequest request) throws FormFileLengthExceprion, IOException, FormFileIsNotSupported {
        MultipartFile files = request.getFile();
        String fileName = files.getOriginalFilename();
        byte[] file_bytes = null;
        String newName = "";
        if (!fileName.equals("")){
            if (files.getContentType().equalsIgnoreCase("image/jpg") ||
                    files.getContentType().equalsIgnoreCase("image/png") ||
                    files.getContentType().equalsIgnoreCase("image/jpeg") ||
                    files.getContentType().equalsIgnoreCase("application/pdf") ||
                    files.getContentType().equalsIgnoreCase("application/vnd.openxmlformats" +
                            "-officedocument.wordprocessingml.document")) {
                newName = UUID.randomUUID() + fileName;
                String filePath = FOLDER_PATH + newName;
                files.transferTo(new File(filePath));
                file_bytes = FileUtils.readFileToByteArray(new File(filePath));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("У файла не допустимый формат"));
            }
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDao.findById(userDetails.getId()).get();
        Form form = new Form(request.getFirst_name(), request.getLast_name(), request.getEmail(),
                request.getFull_text(), newName, file_bytes, files.getContentType(), user);
        formDao.save(form);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/form/download/")
                .path(newName).toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    public ResponseEntity<?> download_file(Long id){
        Form form = formDao.findById(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + form.getNameFile() + "\"")
                .body(form.getFile());
    }

    public MessageResponse update_form(Long id, UpdateFormRequest request){
        Form form = formDao.findById(id).get();
        form.setFirst_name(request.getFirst_name());
        form.setLast_name(request.getLast_name());
        form.setEmail(request.getEmail());
        form.setFull_text(request.getFull_text());
        formDao.save(form);
        return new MessageResponse("Данные заявки с id = " + id.toString() + " изменены!");
    }

    public MessageResponse deleteForm(Long id) {
        formDao.deleteById(id);
        return new MessageResponse(id.toString());
    }

    public FormResponse getOneForm(Long id){
        Form form = formDao.findById(id).get();
        return new FormResponse(form.getType_file());
    }

    public List<FormResponse> getFormsUser(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userDao.findById(userDetails.getId()).get();
        List<Form> forms= formDao.findAllByUser(user);
        List<FormResponse> AllForms = new ArrayList<>();
        for (Form form : forms) {
            AllForms.add(new FormResponse(form.getId(), form.getFirst_name(), form.getLast_name(), form.getEmail(),
                    form.getFull_text()));
        }
        return AllForms;
    }

    public List<FormResponse> getAllForms(){
        List<Form> forms= formDao.findAll();
        List<FormResponse> AllForms = new ArrayList<>();
        for (Form form : forms) {
            AllForms.add(new FormResponse(form.getId(), form.getFirst_name(), form.getLast_name(), form.getEmail(),
                    form.getFull_text()));
        }
        return AllForms;
    }
}
