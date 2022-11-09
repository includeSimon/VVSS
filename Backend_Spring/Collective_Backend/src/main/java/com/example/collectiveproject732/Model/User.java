package com.example.collectiveproject732.Model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class User {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "firstname")
    public String firstName;

    @NotNull
    @Column(name = "username")
    public String userName;

    @NotNull
    @Column(name = "lastname")
    public String lastName;
    //todo make this stuff work idk
    public String password;
    @NotNull
    @Column(name = "email")
    public String email;

    @NotNull
    @Column(name = "isAdmin")
    public boolean isAdmin;

    @OneToMany(mappedBy = "user")
    public List<UserTask> userTasks;
}
