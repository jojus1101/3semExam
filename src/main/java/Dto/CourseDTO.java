/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import entities.Course;
import entities.SchoolClass;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jojus1101
 */
public class CourseDTO implements Serializable {

    private long id;
    private String courseName;
    private String description;
    private Set<SchoolClassDTO> schoolclasses = new HashSet();

    public CourseDTO(Course course) {

        this.courseName = course.getCourseName();
        this.description = course.getDescription();

        for (SchoolClass schoolclass : course.getSchoolclasses()) {
            this.schoolclasses.add(new SchoolClassDTO(schoolclass));
        }
    }

    public CourseDTO(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public Set<SchoolClassDTO> getSchoolclasses() {
        return schoolclasses;
    }

}