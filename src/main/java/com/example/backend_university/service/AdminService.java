package com.example.backend_university.service;

import com.example.backend_university.repository.UserDao;
import com.example.backend_university.request_response.MessageResponse;
import com.example.backend_university.request_response.UpdateUserRequest;
import com.example.backend_university.request_response.UserInfoResponse;
import com.example.backend_university.models.ERole;
import com.example.backend_university.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {
    private final UserDao userDao;

    public AdminService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserInfoResponse> getAllUsers() {
        List<User> users = userDao.findAll();
        List<UserInfoResponse> usersInfo = new ArrayList<>();
        for (User person : users) {
            usersInfo.add(new UserInfoResponse(person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(),
                    person.getRole()));
        }
        return usersInfo;
    }

    public MessageResponse deleteUser(Long id) {
        userDao.deleteById(id);
        return new MessageResponse(id.toString());
    }

    public MessageResponse update_user(Long id, UpdateUserRequest body){
        User user = userDao.findById(id).get();
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setEmail(body.getEmail());
        userDao.save(user);
        return new MessageResponse("Данные пользователя с id = " + id.toString() + " изменены!");
    }

    public MessageResponse raiseUser(Long id, String role) {
        if (Arrays.stream(ERole.values()).anyMatch(eRole -> eRole.name().equals(role))) {
            User user = userDao.findById(id).get();
            user.setRole(role);
            userDao.save(user);
            return new MessageResponse("Пользователю с id=" + id + " Присвоена роль " + role);
        }
        throw new IllegalArgumentException("Такой роли не существует");
    }

}
