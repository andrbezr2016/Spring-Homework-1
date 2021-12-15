package com.andrbezr2016.springtask.service;

import com.andrbezr2016.springtask.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final static List<User> USERS = new ArrayList<>();

    private final File data;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public UserService(Validator validator) {
        this.data = new File("users.json");
        this.objectMapper = new ObjectMapper();
        this.validator = validator;
        try {
            if (data.exists()) {
                USERS.addAll(objectMapper.readValue(data, new TypeReference<ArrayList<User>>() {}));
            } else {
                data.createNewFile();
                objectMapper.writeValue(data, new ArrayList<User>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return USERS;
    }

    public void addUser(User user) {
        // Вернуть в контроллер
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        try {
            USERS.add(user);
            objectMapper.writeValue(data, USERS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> findUser(Collection<User> users, User user) {
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
