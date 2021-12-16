package com.andrbezr2016.springtask.model;

import javax.validation.constraints.*;

public class User {

    @NotBlank(message = "This field is required")
    @Pattern(regexp = "^([a-zA-Z]*)$", message = "Last name must consist of letters of the Latin alphabet")
    private String lastName;

    @NotBlank(message = "This field is required")
    @Pattern(regexp = "^([a-zA-Z]*)$", message = "First name must consist of letters of the Latin alphabet")
    private String firstName;

    @NotBlank(message = "This field is required")
    @Pattern(regexp = "^([a-zA-Z]*)$", message = "Patronymic must consist of letters of the Latin alphabet")
    private String patronymic;

    @Positive(message = "Age cannot be negative")
    private int age;

    @NotBlank(message = "This field is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "This field is required")
    @Pattern(regexp = "^[\\S]*$", message = "There should be no spaces")
    private String organization;

    @Positive(message = "Salary cannot be negative")
    private double salary;

    public User() {
    }

    public User(String lastName, String firstName, String patronymic, int age, String email, String organization, double salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.age = age;
        this.email = email;
        this.organization = organization;
        this.salary = salary;
    }

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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(lastName)
                .append(" ").append(firstName)
                .append(" ").append(patronymic)
                .append(" ").append(age)
                .append(" ").append(email)
                .append(" ").append(organization)
                .append(" ").append(salary);
        return stringBuilder.toString();
    }

}
