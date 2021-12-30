package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "student_code", length = 15)
    private String studentCode;

    private String fullname;
    private Date dob;
    private String address;
    private int gender;
    private String email;
    private String username;
    private String password;
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    @JsonIgnore
    private Collection<Test> test;

    public Collection<Test> getTest() {
        return test;
    }

    public void setTest(Collection<Test> test) {
        this.test = test;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}