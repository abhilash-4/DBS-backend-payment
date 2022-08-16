package com.dbs.payment.backend.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "userName")
    private String username;


    @Column(name = "password")
    private String password;

    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customerId;


    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User(String username, String password,String phone,Customers customerId) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.customerId = customerId;

    }

    public User(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }
}
