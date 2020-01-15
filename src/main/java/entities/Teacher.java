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
import javax.persistence.NamedQuery;

/**
 *
 * @author jojus1101
 */
@Entity
@NamedQuery(name = "Teacher.deleteAllRows", query = "DELETE from Teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @ManyToMany(mappedBy = "teachers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SchoolClass> schoolClasses = new HashSet();

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public void addSchoolClass(SchoolClass schoolclass) {
        this.schoolClasses.add(schoolclass);
    }

    public void removeSchoolClass(SchoolClass schoolclass) {
        this.schoolClasses.remove(schoolclass);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Teacher other = (Teacher) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}