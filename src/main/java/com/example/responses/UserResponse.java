package com.example.responses;

import com.example.enums.Status;
import com.example.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private User user;
    private Status status;
    private String error;
}
