package com.andrbezr2016.springtask.service;

import com.andrbezr2016.springtask.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final static List<User> users = new ArrayList<>();
    private final File data;
    private final ObjectMapper objectMapper;

    public UserService() {
        data = new File("users.json");
        objectMapper = new ObjectMapper();
        try {
            if (data.exists()) {
                // Проблема если файл пуст или неверно
                users.addAll(objectMapper.readValue(data, new TypeReference<ArrayList<User>>() {}));
            } else {
                data.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        try {
            users.add(user);
            objectMapper.writeValue(data, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> findUser(Collection<User> users, User user) {
        // Можно весто пустой строки валидацию. (только буквы например)
        final boolean isFirstName = !user.getFirstName().equals("");
        final boolean isLastName = !user.getLastName().equals("");
        List<User> foundedUsers = new ArrayList<>();
        if (isFirstName && isLastName) {
            for (User currUser : users) {
                if (currUser.getLastName().equals(user.getLastName()) && currUser.getFirstName().equals(user.getFirstName())) {
                    foundedUsers.add(currUser);
                }
            }
        } else if (isFirstName) {
            for (User currUser : users) {
                if (currUser.getFirstName().equals(user.getFirstName())) {
                    foundedUsers.add(currUser);
                }
            }
        } else if (isLastName) {
            for (User currUser : users) {
                if (currUser.getLastName().equals(user.getLastName())) {
                    foundedUsers.add(currUser);
                }
            }
        }
        return foundedUsers;
    }
}
