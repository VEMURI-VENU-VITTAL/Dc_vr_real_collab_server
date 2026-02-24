package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class User {
    @Id
    private String id;
    private String userName;
    private String password;


    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
