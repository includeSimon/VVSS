package com.example.collectiveproject.Model;

import com.example.collectiveproject.Model.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

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

    @Column(name = "user_name", unique = true)
    @NotNull
    public String userName;

    @NotNull
    public String lastName;

    @NotNull
    public String password;

    @NotNull
    public String email;

    @NotNull
    public boolean isAdmin;

    public String token;

    @OneToMany(mappedBy = "user")
    public List<UserTask> userTasks;

    public UserDTO convertToDTO() {
        return UserDTO.builder()
                .email(this.getEmail())
                .firstName(this.getFirstName())
                .id(this.getId())
                .isAdmin(this.isAdmin)
                .lastName(this.getLastName())
                .userName(getUserName())
                .build();
    }
}
