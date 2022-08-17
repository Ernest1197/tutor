package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author Philip
 */
@Entity
public class Student {
    @Id
    private String studentId;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String emailAddress;

    @OneToMany(mappedBy = "student")
    private List<Registration> registrations;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Version
    private long version = 1;

    public Student(String studentId, String name, Gender gender, Date dateOfBirth,
                   String phoneNumber, String emailAddress,Department department) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.department = department;
    }

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public Department getDepartment() {  return department; }

    public void setDepartment(Department department) {  this.department = department; }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
