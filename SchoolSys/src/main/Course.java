package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Philip
 */
@Entity
public class Course {
    @Id
    private String code;
    private String name;
    private Integer credits;

    @OneToMany(mappedBy = "course")
    private List<Registration> registrations;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Version
    private long version = 1;

    public Course(String code, String name, Integer credits, Department department) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.department = department;
    }

    public Course() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
