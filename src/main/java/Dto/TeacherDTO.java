/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import entities.Teacher;
import java.io.Serializable;

/**
 *
 * @author jojus1101
 */
public class TeacherDTO implements Serializable {

    private long id;
    private String name;

    public TeacherDTO(Teacher teacher) {

        this.id = teacher.getId();
        this.name = teacher.getName();
    }

    public TeacherDTO(String name) {
        this.name = name;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}