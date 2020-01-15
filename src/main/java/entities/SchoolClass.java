/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author jojus1101
 */
@Entity
@NamedQuery(name = "SchoolClass.deleteAllRows", query = "DELETE from SchoolClass")
public class SchoolClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semester;
    private int maxNumberStudent;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Teacher> teachers = new HashSet();
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Course course;

    public SchoolClass(String semester, int maxNumberStudent, Course course) {
        this.semester = semester;
        this.maxNumberStudent = maxNumberStudent;
        this.course = course;
    }

    public SchoolClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getMaxNumberStudent() {
        return maxNumberStudent;
    }

    public void setMaxNumberStudent(int maxNumberStudent) {
        this.maxNumberStudent = maxNumberStudent;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.addSchoolClass(this);
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.addSchoolClass(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.semester);
        hash = 71 * hash + Objects.hashCode(this.course);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SchoolClass other = (SchoolClass) obj;
        if (!Objects.equals(this.semester, other.semester)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

}