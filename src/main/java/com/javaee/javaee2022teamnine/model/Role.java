package com.javaee.javaee2022teamnine.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    private int id;

    /*@Column(name = "role")
    @OneToOne(mappedBy = "role")
    private User user;*/

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/


}
