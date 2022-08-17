package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Philip
 */
@Entity
public class Faculty {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "faculty")
    private List<Department> departments;

    public Faculty(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty() {

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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
    
    
}
