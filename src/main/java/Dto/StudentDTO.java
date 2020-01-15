/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import entities.SignedUp;
import entities.Student;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jojus1101
 */
public class StudentDTO implements Serializable {

    private long id;
    private String name;
    private String email;
    private Set<SignedUpDTO> signeduplist = new HashSet();

    public StudentDTO(Student student) {

        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();

        for (SignedUp signedup : student.getSigneduplist()) {
            this.signeduplist.add(new SignedUpDTO(signedup));
        }

    }

    public StudentDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<SignedUpDTO> getSigneduplist() {
        return signeduplist;
    }

}