package com.example.collectiveproject.Model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Table(name = "users")
@Entity
@Getter
@Setter
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String firstName;

    @NotNull
    public String userName;

    @NotNull
    public String lastName;

    //todo make this stuff work idk
    public String password;
    @NotNull
    public String email;

    @NotNull
    public boolean isAdmin;

    @OneToMany(mappedBy = "user")
    public List<UserTask> userTasks;
}
