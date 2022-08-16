package com.dbs.payment.backend.models;

import javax.persistence.*;

@Entity
@Table(name ="roles")
public class Role {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ERole rolename;

    public Role() {

    }

    public Role(Integer id, ERole role) {
        this.id = id;
        this.rolename = role;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRolename() {
        return rolename;
    }

    public void setRolename(ERole rolename) {
        this.rolename = rolename;
    }
}
