//package com.dbs.payment.backend.models;
//
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "employee")
//public class Employee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "employeeId")
//    private Long id;
//
//
//    @Column(name = "employeeName")
//    private String username;
//
//
//    @Column(name = "employeePassword")
//    private String password;
//
//    public Employee() {
//    }
//
//    public Employee(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
