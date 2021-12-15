package com.andrbezr2016.springtask.model;

import javax.validation.constraints.*;

public class User {

    @NotBlank(message = "Поле не заполнено")
    @Pattern(regexp = "^([a-zA-Z]*)|([а-яёА-ЯЁ]*)$", message = "Фамилия может состоять только из букв русского, либо латинского алфавита")
    private String lastName;

    @NotBlank(message = "Поле не заполнено")
    @Pattern(regexp = "^([a-zA-Z]*)|([а-яёА-ЯЁ]*)$", message = "Имя может состоять только из букв русского, либо латинского алфавита")
    private String firstName;

    @NotBlank(message = "Поле не заполнено")
    @Pattern(regexp = "^([a-zA-Z]*)|([а-яёА-ЯЁ]*)$", message = "Отчество может только состоять из букв русского, либо латинского алфавита")
    private String patronymic;

    @Positive(message = "Возраст должен быть положительным")
    private int age;

    @NotBlank(message = "Поле не заполнено")
    @Email(message = "Неверный адрес эл. почты")
    private String email;

    @NotBlank(message = "Поле не заполнено")
    private String organization;

    @Positive(message = "Зарплата должна быть положительной")
    private double salary;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
