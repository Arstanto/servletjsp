package com.mitrais.jee.model;

import java.util.Date;

public class Students {
    protected int id;
    protected String name;
    protected String grade;
    protected String studentId;
    protected double balance;
    protected Date created;
    protected Date modified;

    public Students(int id) {
        this.id = id;
    }

    public Students(int id, String name, String grade, double balance, String studentId, Date created) {
        this(name, grade, balance, studentId, created);
        this.id = id;
        this.created = created;
        this.modified = created;
    }

    public Students(String name, String grade, double balance, String studentId, Date created) {
        this.name = name;
        this.grade = grade;
        this.studentId = studentId;
        this.balance = balance;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
