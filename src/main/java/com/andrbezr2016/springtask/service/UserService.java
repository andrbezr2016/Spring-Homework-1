package com.andrbezr2016.springtask.service;

import com.andrbezr2016.springtask.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private final File data;

    public UserService() {
        this.data = new File("users.txt");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line = reader.readLine();
            String[] cells;
            while (line != null) {
                cells = line.split(" ");
                users.add(new User(cells[0], cells[1], cells[2], Integer.parseInt(cells[3]), cells[4], cells[5], Double.parseDouble(cells[6])));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data, true))) {
            users.add(user);
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> findUser(User user) {
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

    public void fileUpload(MultipartFile file) {
        if (file.isEmpty()) return;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = reader.readLine();
            String[] cells;
            while (line != null) {
                cells = line.split(" ");
                User user = new User(cells[0], cells[1], cells[2], Integer.parseInt(cells[3]), cells[4], cells[5], Double.parseDouble(cells[6]));
                users.add(user);
                addUser(user);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
