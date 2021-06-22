package com.codegym.model;

import javax.validation.constraints.*;

public class User {


    @NotEmpty(message = "Required field")
    @Size(min = 5, max = 45, message = "Between 5 and 45 characters")
    private String firstName;

    @NotEmpty(message = "Required field")
    @Size(min = 5, max = 45, message = "Between 5 and 45 characters")
    private String lastName;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Must contains 10 numbers and start with 0")
    private String phone;

    @Min(value = 18, message = "Must be greater than or equal 18")
    private Long age;

    @Email(message = "Must be an email")
    private String email;

    public User() {
    }

    public User(@NotEmpty @Size(min = 5, max = 45) String firstName, @NotEmpty @Size(min = 5, max = 45) String lastName, @Pattern(regexp = "^0[0-9]{9}$") String phone, @Min(18) Long age, @Email String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
