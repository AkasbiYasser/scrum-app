// src/main/java/com/example/scrumbackend/models/Employee.java
package com.example.scrumbackend.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Employee extends User {
    private String firstName;
    private String lastName;
    private String email;
    private String status;

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
