package com.example.collectiveproject732.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Builder(
        toBuilder = true,
        access = AccessLevel.PUBLIC
)
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String userName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean isAdmin;

    @NotNull
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<UserTask> userTasks;

    public User() {

    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserTasks(List<UserTask> userTasks) {
        this.userTasks = userTasks;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof User user)) return false;
        return Objects.equals(this.getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
