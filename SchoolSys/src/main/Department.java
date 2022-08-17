package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Philip
 */
@Entity
public class Department {
    @Id
    private String id;
    private String name;

    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    @OneToMany(mappedBy = "department")
    private List<Student> students;

    public Department(String id, String name, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
    }

    public Department() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Course> getCourses() { return courses; }

    public void setCourses(List<Course> courses) { this.courses = courses; }

    public List<Student> getStudents() { return students; }

    public void setStudents(List<Student> students) { this.students = students; }
}
