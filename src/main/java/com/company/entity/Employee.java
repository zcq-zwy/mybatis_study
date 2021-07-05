package com.company.entity;

import java.util.Date;

/**
 * employee
 */
public class Employee {
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * email
     */
    private String email;

    /**
     * phone
     */
    private String phone;

    /**
     * date
     */
    private Date date;

    /**
     * status
     */
    private StatusEnum status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}