package com.dbs.payment.backend.models;

import javax.persistence.*;

@Entity
@Table
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loggerId")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customers customerId;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employeeId;


    private String screenName;

    private String action;

    private String ipaddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    public User getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(User employeeId) {
        this.employeeId = employeeId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
