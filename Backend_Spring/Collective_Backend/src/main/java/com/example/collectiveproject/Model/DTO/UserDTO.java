package com.example.collectiveproject.Model.DTO;

import com.example.collectiveproject.Model.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {

    public Long id;
    public String firstName;
    public String userName;
    public String lastName;
    public String email;
    public boolean isAdmin;

}
